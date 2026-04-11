package com.csms.llm;

import com.csms.entities.ServiceE;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LLMService {
    private String msg = "Hello, Welcome";
    private ChatLanguageModel chatLanguageModel;
    private LlmUtils llmUtils;
    public LLMService(@Value("${gemini.api.key}") String apiKey){

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("gemini.api.key must be set in application.properties");
        }

        ResponseFormat jsonFormat = ResponseFormat.builder()
                .type(ResponseFormatType.JSON)
                .build();

        chatLanguageModel = GoogleAiGeminiChatModel.builder()
                .modelName("gemini-2.5-flash")
                .apiKey(apiKey)
                .responseFormat(jsonFormat)
                .build();
        llmUtils = AiServices.create(LlmUtils.class,chatLanguageModel);

    }
    public String getWelcomeMsg(String language){

        return llmUtils.translate(msg,language);
    }

    public List<ServiceE> getMatchedServices(String services,String intent,String language){
        return llmUtils.getMatchedServices(services,intent,language).getServices();
    }

    public List<String> translateDocs(List<String> documentList, String language){
        String docs = String.join(",",documentList);
        return llmUtils.translateList(docs,language).getDocs();
    }
}
