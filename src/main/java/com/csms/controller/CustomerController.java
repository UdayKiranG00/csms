package com.csms.controller;

import com.csms.entities.Language;
import com.csms.entities.ServiceE;
import com.csms.repository.LanguageRepository;
import com.csms.repository.ServiceRepository;
import com.csms.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    CustomerService customerService;
    @GetMapping("/")
    public List<Language> getAllLanguages(){
        return languageRepository.findAll();
    }

    @GetMapping("/lang/{language}")
    public String postLanguage(@PathVariable String language, HttpSession session){
        customerService.setLanguage(language);
        return customerService.getWelcomeMessage();
    }

    @GetMapping("/intent/{intention}")
    public List<ServiceE> postPurpose(@PathVariable String intention){
        return customerService.getMatchedServices(intention);
    }

    @GetMapping("/reqdocs/{serviceId}")
    public List<String> getReqDocs(@PathVariable int serviceId, HttpSession session){
        session.setAttribute("serviceId",serviceId);
        return customerService.getRequiredDocs(serviceId);
    }

    @GetMapping("/gettoken")
    public int getToken(){
        return customerService.generateToken();
    }

    @GetMapping("/endsession")
    public String endSession(HttpSession session){
        session.invalidate();
        return "Session Ended";
    }
}
