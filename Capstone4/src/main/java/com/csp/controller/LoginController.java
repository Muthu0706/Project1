package com.csp.controller;

import com.csp.bean.Login;
import com.csp.bean.Response;
import com.csp.service.EmailSendService;
import com.csp.service.LoginService;
import com.csp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private EmailSendService sendService;

    @PostMapping("/Logins")
    public void performInsert(@RequestBody Login login) {
        loginService.saveOrUpdateLogin(login);
    }

    @PutMapping("/Logins")
    public void performUpdate(@RequestBody Login login) {
        loginService.saveOrUpdateLogin(login);
    }

    @DeleteMapping("/Logins/{emailId}")
    public void performDelete(@PathVariable String emailId) {
        loginService.deleteLoginByEmailId(emailId);
    }

    @GetMapping("/Logins")
    public List<Login> viewAllLogins() {
        return loginService.getAllLogins();
    }

   //Method
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> verifyLogin(@RequestBody Login loginRequest) {
        
        if (loginService.isLoginDisabled(loginRequest.getUserEmailId())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login disabled. Try again after 5 minutes.");
            return ResponseEntity.badRequest().body(response);
        }

        String email = loginService.verifyLogin(loginRequest.getUserEmailId(), loginRequest.getPassword());
        if (email != null) {
           
            loginService.resetLoginAttempts(loginRequest.getUserEmailId());
            String userType = loginService.getUserTypeByEmail(email);
            String token = jwtUtil.generateToken(email, userType); 
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("userType", userType);
            response.put("email", email);
            sendService.sendOtpService(email);
            return ResponseEntity.ok(response);
        } else {
           
            loginService.updateFailedLoginAttempts(loginRequest.getUserEmailId());
            Map<String, String> response = new HashMap<>();
            int attempts = loginService.getLoginAttempts(loginRequest.getUserEmailId());
            
            System.out.println(attempts);
            if(attempts<3) {
            	response.put("message", "Login failed. Invalid credentials.");
            }
            else if(attempts ==3) {
            	response.put("message", "Login failed. 2 attempts remaining.");
            }
            else if(attempts ==4) {
            	response.put("message", "Login failed. 1 attempts remaining.");
            }
            else if(attempts ==5){
             	response.put("message", "Login failed. 1 attempts remaining.");
            }
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    
    // new Method
    
    
//    @PostMapping("/login")
//    public ResponseEntity<Response> verifyLogin(@RequestBody Login loginRequest) {
//        
//        if (loginService.isLoginDisabled(loginRequest.getUserEmailId())) {
//            Response response=new Response();
//            response.setMessage("Login disabled. Try again after 5 minutes.");
//            return ResponseEntity.ok(response);
//        }
//
//        String email = loginService.verifyLogin(loginRequest.getUserEmailId(), loginRequest.getPassword());
//        if (email != null) {
//           
//            loginService.resetLoginAttempts(loginRequest.getUserEmailId());
//            String userType = loginService.getUserTypeByEmail(email);
//            String token = jwtUtil.generateToken(email, userType); 
//            
//            Response response=new Response();
//            
//            response.setToken(token);
//            response.setUserType(userType);
//            response.setEmail(email);
//            response.setMessage("success");
//            
//            
//            sendService.sendOtpService(email);
//            return ResponseEntity.ok(response);
//        } else {
//           
//            loginService.updateFailedLoginAttempts(loginRequest.getUserEmailId());
//           Response response=new Response();
//            int attempts = loginService.getLoginAttempts(loginRequest.getUserEmailId());
//            
//            System.out.println(attempts);
//            if(attempts<3) {
//            	response.setMessage(" Login failed. Invalid credentials.");
//            }
//            else if(attempts ==3) {
//            	
//            	response.setMessage(" Login failed. 2 attempts remaining.");
//            
//            }
//            else if(attempts ==4) {
//            	response.setMessage(" Login failed. 1 attempts remaining.");
//            	
//            	
//            }
////            else {
////             	response.put("message", "Login failed. 1 attempts remaining.");
////            }
//            return ResponseEntity.ok(response);
//        }
//    }
}