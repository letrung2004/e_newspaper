package com.newspaper.aiservice.service;

import com.newspaper.aiservice.dto.response.ArticleEmbeddingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EmbeddingService {


    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\S+@\\S+\\.\\S+");
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[^\\w\\s]");
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");

    // cat bai bao ra thanh cac cau nho theo dau cau
    List<String> splitContentBySentences(String content) {
        List<String> sentences = new ArrayList<>();
        String[] splits = content.split("(?<=[.!?])\\s+");
        for (String s : splits) {
            s = s.trim();
            if (!s.isEmpty()) {
                sentences.add(s);
            }
        }
        return sentences;
    }

    //overlap
    public List<String> createChunksWithOverlap(List<String> sentences, int chunkSize, int overlap) {
        if (chunkSize <= 0) throw new IllegalArgumentException("chunkSize must be > 0");
        if (overlap < 0) throw new IllegalArgumentException("overlap must be >= 0");
        if (overlap >= chunkSize) throw new IllegalArgumentException("overlap must be less than chunkSize");

        List<String> chunks = new ArrayList<>();
        int start = 0;
        int n = sentences.size();

        while (start < n) {
            int end = Math.min(start + chunkSize, n);

            StringBuilder sb = new StringBuilder();
            for (int i = start; i < end; i++) {
                sb.append(sentences.get(i));
                if (i != end - 1) {
                    sb.append(" ");
                }
            }

            chunks.add(sb.toString());

            start += (chunkSize - overlap);
        }

        return chunks;
    }

    // cat cau va overlap tao cac chunks
    public List<String> splitAndCreateChunks(String content) {
        List<String> sentences = splitContentBySentences(content);
        return createChunksWithOverlap(sentences, 3, 1);
    }

    // lam sach chunk
    private String cleanText(String text) {
        text = text.toLowerCase();
        text = EMAIL_PATTERN.matcher(text).replaceAll("email");
        text = PUNCTUATION_PATTERN.matcher(text).replaceAll("");
        text = WHITESPACE_PATTERN.matcher(text).replaceAll(" ");
        return text.trim();
    }

    //embedding cac chunks va luu xuong database
    public ArticleEmbeddingResponse createArticleEmbedding(String articleId, String content) {
        return null;
    }


}
