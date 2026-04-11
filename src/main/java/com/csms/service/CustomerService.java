package com.csms.service;

import com.csms.entities.ServiceE;
import com.csms.entities.Token;
import com.csms.llm.LLMService;
import com.csms.repository.ServiceRepository;
import com.csms.repository.TokenRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;


@Service
public class CustomerService {

    @Autowired
    private LLMService llmService;

    @Autowired
    HttpSession httpSession;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    TokenRepository tokenRepository;

    public boolean setLanguage(String language){
        httpSession.setAttribute("language",language);
        return true;
    }
    public String getWelcomeMessage(){
        String language = (String) httpSession.getAttribute("language");
        System.out.println(language);
        return llmService.getWelcomeMsg(language);
    }
    public List<ServiceE> getMatchedServices(String intention){
        List<ServiceE> allServices = serviceRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(allServices);
        System.out.println(jsonString);
        String language = (String) httpSession.getAttribute("language");
        return llmService.getMatchedServices(jsonString,intention,language);
    }

    public List<String> getRequiredDocs(int serviceId){
        List<String> documentList = serviceRepository.getDocs(serviceId);

        return llmService.translateDocs(documentList, (String) httpSession.getAttribute("language"));
    }

    public int generateToken(){
        int tokenNum = (int)(Math.random() * 10000);
        int serviceId = (int) httpSession.getAttribute("serviceId"); //need to uncomment when executing end to end.
        Token t = Token.builder().token_number(tokenNum).state("ACTIVE").service_id(serviceId).officer_id(0).build();
        Token t1 = tokenRepository.save(t);
        return tokenNum;
    }
}
