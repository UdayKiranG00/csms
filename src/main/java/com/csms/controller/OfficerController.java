package com.csms.controller;

import com.csms.entities.Officer;
import com.csms.entities.Token;
import com.csms.service.OfficerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/officer")
public class OfficerController {

    @Autowired
    OfficerService oS;

    @GetMapping("/register")
    public String register(@RequestParam("name") String name,@RequestParam("email") String email, @RequestParam("password") String password){
        Officer o1 = Officer.builder().name(name).email(email).password(password).build();
        oS.registerServ(o1);
        return "Officer Created Successfully";
    }

    @GetMapping("/authenticate")
    public String login(@RequestParam("username") String uname, @RequestParam("password") String password){
        if(oS.isValidUser(uname,password)){
            return "Authenticated";
        }
        return "Not a Valid User";
    }

    @GetMapping("/getToken")
    public Token getActiveToken(@RequestParam int offId){
        return oS.getActiveTkn(offId);
    }

    @GetMapping("/tokenComplete")
    public String tokenCompleted(@RequestParam int tokenId){
        if(oS.updateTokenCompRCancel(tokenId,"COMPLETE")){
            return "Updated Successfully";
        }
        return "no token record is present with tokenId:"+String.valueOf(tokenId);
    }

    @GetMapping("/tokenCancel")
    public String tokenCancelled(@RequestParam int tokenId){
        if(oS.updateTokenCompRCancel(tokenId,"CANCEL")){
            return "Updated Successfully";
        }
        return "no token record is present with tokenId:"+String.valueOf(tokenId);
    }

    @GetMapping("/updateProfile")
    public String updateProfile(@RequestParam String value,@RequestParam String valType){
        oS.updateProfile(value,valType);
        return "updated successfully";
    }

    @GetMapping("/endsession")
    public String endSession(HttpSession session){
        session.invalidate();
        return "Session Ended";
    }
}
