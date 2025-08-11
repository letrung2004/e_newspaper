package com.newspaper.aiservice.service;

import com.newspaper.aiservice.dto.response.ArticleEmbeddingResponse;
import com.newspaper.aiservice.entity.ArticleEmbedding;
import com.newspaper.aiservice.repository.ArticleEmbeddingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmbeddingService {

    private final EmbeddingModel embeddingModel;
    private final ArticleEmbeddingRepository articleEmbeddingRepository;


    private static final Pattern EMAIL_PATTERN = Pattern.compile("\\S+@\\S+\\.\\S+");
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[^\\w\\s]");
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");

    public EmbeddingService(@Qualifier("textEmbedding") EmbeddingModel embeddingModel, ArticleEmbeddingRepository articleEmbeddingRepository) {
        this.embeddingModel = embeddingModel;
        this.articleEmbeddingRepository = articleEmbeddingRepository;
    }

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

    public List<Float> createEmbedding(String text) {
        try {
            String cleanedText = cleanText(text);

            // embed() method returns float[]
            float[] embedding = embeddingModel.embed(cleanedText);

            // Convert float[] to List<Float>
            List<Float> embeddingList = new ArrayList<>();
            for (float value : embedding) {
                embeddingList.add(value);
            }

            return embeddingList;

        } catch (Exception e) {
            log.error("Error creating embedding for text: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create embedding: " + e.getMessage());
        }
    }
    //embedding cac chunks va luu xuong database
    public ArticleEmbeddingResponse createArticleEmbedding(String articleId, String content) {
        try {
            // Check if embedding already exists
            if (articleEmbeddingRepository.existsByArticleId(articleId)) {
                log.info("Embedding already exists for article: {}", articleId);
                ArticleEmbedding existing = articleEmbeddingRepository.findByArticleId(articleId).orElseThrow();
                return ArticleEmbeddingResponse.builder()
                        .articleId(articleId)
                        .chunksCount(existing.getChunks().size())
                        .build();
            }

            // Split content into chunks
            List<String> chunks = splitAndCreateChunks(content);
            log.info("Created {} chunks for article: {}", chunks.size(), articleId);

            // Create embeddings for each chunk
            List<ArticleEmbedding.Chunk> embeddedChunks = new ArrayList<>();

            for (int i = 0; i < chunks.size(); i++) {
                String chunkContent = chunks.get(i);
                List<Float> embeddingVector = createEmbedding(chunkContent);

                ArticleEmbedding.Chunk chunk = ArticleEmbedding.Chunk.builder()
                        .chunkIndex(i)
                        .chunkContent(chunkContent)
                        .embeddingVector(embeddingVector)
                        .build();

                embeddedChunks.add(chunk);
                log.debug("Created embedding for chunk {} of article {}", i, articleId);
            }

            // Save to database
            ArticleEmbedding articleEmbedding = ArticleEmbedding.builder()
                    .articleId(articleId)
                    .chunks(embeddedChunks)
                    .build();

            articleEmbeddingRepository.save(articleEmbedding);
            log.info("Saved embeddings for article: {} with {} chunks", articleId, embeddedChunks.size());

            return ArticleEmbeddingResponse.builder()
                    .articleId(articleId)
                    .chunksCount(embeddedChunks.size())
                    .build();

        } catch (Exception e) {
            log.error("Error creating embeddings for article {}: {}", articleId, e.getMessage(), e);
            throw new RuntimeException("Failed to create article embeddings: " + e.getMessage());
        }
    }


}
