package com.newspaper.aiservice.service;

import com.cloudinary.Cloudinary;
import com.google.cloud.texttospeech.v1.*;
import com.newspaper.aiservice.dto.request.ArticleProcessRequest;
import com.newspaper.aiservice.dto.response.SummarizationResponse;
import com.newspaper.aiservice.dto.response.TextToSpeechResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AiService {
    @Autowired
    private Cloudinary cloudinary;

    private final ChatClient chatClient;
    public AiService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public SummarizationResponse summarize(ArticleProcessRequest request) {
        SystemMessage systemMessage = new SystemMessage("""
                Hãy tóm tắt bài báo sau thành 4-5 câu bằng tiếng Việt,
                tập trung vào những thông tin chính và quan trọng nhất:
                """);

        UserMessage userMessage = new UserMessage(request.getContent());
        Prompt prompt = new Prompt(systemMessage, userMessage);


        String result = chatClient
                .prompt(prompt)
                .call()
                .content();

        return new SummarizationResponse(result);
    }

    public TextToSpeechResponse convertTextToSpeech(ArticleProcessRequest request) {
        try {

            try (TextToSpeechClient client = TextToSpeechClient.create()) {

                SynthesisInput input = SynthesisInput.newBuilder()
                        .setText(request.getContent())
                        .build();

                VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                        .setLanguageCode("vi-VN")
                        .setName("vi-VN-Standard-A")
                        .setSsmlGender(SsmlVoiceGender.FEMALE)
                        .build();

                AudioConfig audioConfig = AudioConfig.newBuilder()
                        .setAudioEncoding(AudioEncoding.MP3)
                        .setSpeakingRate(1.0)
                        .setPitch(0.0)
                        .setVolumeGainDb(0.0)
                        .build();

                SynthesizeSpeechResponse response = client.synthesizeSpeech(input, voice, audioConfig);
                log.info("Speech synthesized successfully");

                String fileName = "tts_" + System.currentTimeMillis();
                File tempFile = File.createTempFile(fileName, ".mp3");

                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(response.getAudioContent().toByteArray());
                }
                log.info("Temporary audio file created: {}", tempFile.getPath());

                Map<String, Object> uploadParams = new HashMap<>();
                uploadParams.put("resource_type", "video");
                uploadParams.put("public_id", "tts-audio/" + fileName);
                uploadParams.put("format", "mp3");

                Map<String, Object> uploadResult = cloudinary.uploader().upload(tempFile, uploadParams);
                String secureUrl = (String) uploadResult.get("secure_url");

                log.info("Audio uploaded to Cloudinary: {}", secureUrl);

                if (tempFile.exists()) {
                    tempFile.delete();
                    log.info("Temporary file deleted");
                }

                return TextToSpeechResponse.builder()
                        .audioUrl(secureUrl)
                        .build();

            }
        } catch (Exception e) {
            log.error("Error during TTS conversion: {}", e.getMessage(), e);
            throw new RuntimeException("Text-to-speech conversion failed: " + e.getMessage());
        }
    }
}
