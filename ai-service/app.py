from flask import Flask, request, jsonify
from flask_cors import CORS
from rag_chat import Chatbot

app = Flask(__name__)
CORS(app)

# Tạo chatbot với API key
bot = Chatbot(api_key="AIzaSyAGXyz4q6xk3QaRTo4dIjesz-QZlhSI-oY")  # Thay bằng key thật


@app.route('/api/ask', methods=['POST'])
def ask():
    try:
        data = request.get_json()
        content = data.get('content')
        question = data.get('question')

        if not content or not question:
            return jsonify({"error": "Missing 'content' or 'question'"}), 400

        bot.set_data(content)
        answer = bot.make_request(question)

        return jsonify({
            "answer": answer
        })

    except Exception as e:
        return jsonify({"error": str(e)}), 500


if __name__ == '__main__':
    app.run(debug=True)
