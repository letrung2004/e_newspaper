import re
import time
import numpy as np
from nltk.tokenize import sent_tokenize
from sklearn.metrics.pairwise import cosine_similarity
from sentence_transformers import SentenceTransformer
import google.generativeai as genai
import nltk

nltk.download('punkt')
nltk.download('punkt_tab')
nltk.download('stopwords')
nltk.download('wordnet')
nltk.download('averaged_perceptron_tagger')


class Chatbot:
    def __init__(self, api_key):
        self.data = []
        self.embeddings = []
        self.sentence_transformer_model = SentenceTransformer('all-mpnet-base-v2')

        # Cấu hình Gemini
        genai.configure(api_key=api_key)
        self.gemini_model = genai.GenerativeModel("gemini-1.5-flash")

    def set_data(self, text):
        """
        Cài đặt dữ liệu (bài báo gốc) từ văn bản thô và chia đoạn theo câu.
        """
        self.data = []

        # Cắt theo câu (sử dụng NLTK)
        sentences = sent_tokenize(text)

        for idx, sentence in enumerate(sentences):
            cleaned = self.clean(sentence)
            self.data.append({
                "original": sentence.strip(),
                "cleaned": cleaned
            })

            # In debug từng đoạn
            print(f"Segment {idx}:")
            print("Original:", self.data[-1]["original"])
            print("Cleaned :", self.data[-1]["cleaned"])
            print("-" * 50)

        # Tính embedding
        self.embeddings = self.sentence_transformer_model.encode(
            [item["cleaned"] for item in self.data]
        )

    def clean(self, text):
        """
        Làm sạch văn bản trước khi embedding.
        """
        text = text.lower()
        text = re.sub(r'\S+@\S+\.\S+', 'email', text)  # ẩn email
        text = re.sub(r'[^\w\s]', '', text)  # bỏ dấu câu
        return re.sub(r'\s+', ' ', text).strip()

    def search_query(self, query, top_k=3):
        """
        Tìm đoạn văn bản liên quan đến câu hỏi.
        """
        query_cleaned = self.clean(query)
        query_embedding = self.sentence_transformer_model.encode([query_cleaned])
        similarities = cosine_similarity(query_embedding, self.embeddings)
        top_indices = np.argsort(similarities[0])[::-1][:top_k]
        contexts = [self.data[idx]["original"] for idx in top_indices]
        scores = [similarities[0][idx] for idx in top_indices]
        return contexts, scores

    def generate_answer_with_gemini(self, context, question):
        """
        Trả lời bằng Gemini dựa trên context.
        """
        prompt = (
            f"You are a highly knowledgeable and detail-oriented assistant. "
            f"Start by addressing the question clearly. "
            f"Then, use the context provided to elaborate.\n\n"
            f"Question:\n{question}\n\n"
            f"Context:\n{context}\n\n"
            f"Answer:"
        )
        try:
            response = self.gemini_model.generate_content(prompt)
            return response.text
        except Exception as e:
            return f"Error: {e}"

    def make_request(self, question):
        """
        Tìm context phù hợp và tạo câu trả lời cho câu hỏi.
        """
        start = time.time()
        results, scores = self.search_query(question)
        context = "\n".join([f"(Score: {score:.4f}) {item}" for item, score in zip(results, scores)])
        answer = self.generate_answer_with_gemini(context, question)
        end = time.time()

        print(f"Context : \n{context}")
        print(f"Chatbot : {answer.rstrip()}")
        print(f"Running time: {end - start:.2f} seconds")

        return answer
