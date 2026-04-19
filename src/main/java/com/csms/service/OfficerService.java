package com.csms.service;

import com.csms.entities.Officer;
import com.csms.entities.Token;
import com.csms.repository.OfficerRepository;
import com.csms.repository.TokenRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficerService {

    @Autowired
    OfficerRepository oR;
    @Autowired
    TokenRepository tR;
    @Autowired
    HttpSession httpSession;

    public boolean registerServ(Officer o1){
        oR.save(o1);
        return true;
    }

    public boolean isValidUser(String uname, String password){
        Officer o = oR.findByUnameAndPassword(uname,password);
        if(o.getId()!=0){
            httpSession.setAttribute("offId",o.getId());
            return true;
        }
        return false;
    }

    public Token getActiveTkn(int offId){
        Token token = oR.findTokenByServices(offId);
        token.setOfficer_id(offId);
        tR.save(token);
        return token;
    }

    public boolean updateTokenCompRCancel(int tokenId,String type){
        Optional<Token> token;
        token = tR.findById(tokenId);
        if(token.isPresent()){
            Token t1 = token.get();
            if(type.equals("COMPLETE")){
                t1.setState("COMPLETED");
            }else {
                t1.setState("CANCELLED");
            }
            tR.save(t1);
            return true;
        }else{
            return false;
        }
    }

    public void updateProfile(String value, String valType) {
        Optional<Officer> o = oR.findById((Integer) httpSession.getAttribute("offId"));
        Officer o1 = o.get();
        if(valType.equals("UNAME")){
            o1.setName(value);
        }else if(valType.equals("EMAIL")){
            o1.setEmail(value);
        }else{
            o1.setPassword(value);
        }
        oR.save(o1);
    }

}
