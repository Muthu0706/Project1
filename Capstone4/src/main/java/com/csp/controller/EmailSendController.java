package com.csp.controller;

import com.csp.bean.Response;
import com.csp.service.EmailSendService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200/")

public class EmailSendController {

    @Autowired
    private EmailSendService sendService;

    @PostMapping("/sendOtp/{email}")
    public ResponseEntity<String> sendOtpToMail(@PathVariable("email") String email) {
        sendService.sendOtpService(email);
        return ResponseEntity.ok("OTP sent successfully to " + email);
    }

//    @PostMapping("/verifyOTP")
//    public ResponseEntity<String> verifyOTP(@RequestBody Map<String, String> requestParams) {
//        String email = requestParams.get("email");
//        String otp = requestParams.get("otp");
//        
//        if (email == null || otp == null) {
//            return ResponseEntity.badRequest().body("Both email and OTP are required.");
//        }
//
//        if (sendService.verifyOTP(email, otp)) {
//            return ResponseEntity.ok("OTP verified successfully for " + email);
//        } else {
//            return ResponseEntity.badRequest().body("Invalid OTP.");
//        }
//    }
    
    
    @PostMapping("/verifyOTP")
    public ResponseEntity<Response> verifyOTP(@RequestBody Map<String, String> requestParams) {
        String email = requestParams.get("email");
        String otp = requestParams.get("otp");
        
        if (email == null || otp == null) {
        	
        	Response res=new Response("Both email and OTP are required.");
            return ResponseEntity.ok(res);
        }

        if (sendService.verifyOTP(email, otp)) {
        	
        	Response res=new Response("Valid OTP");
            return ResponseEntity.ok(res);
        } else {
        	Response res=new Response("Invalid OTP.");
        	
            return ResponseEntity.ok(res);
        }
    }
}