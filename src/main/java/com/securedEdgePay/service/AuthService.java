package com.securedEdgePay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.securedEdgePay.dto.AuthResponse;
import com.securedEdgePay.dto.ResponseDTO;
import com.securedEdgePay.model.User;
import com.securedEdgePay.repository.UserRepository;
import com.securedEdgePay.repository.oauth2.OAuthAccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OAuthAccessTokenRepository oAuthAccessTokenRepository;

    public ResponseEntity<Object> login(String username, String password) {

        try {

            Map<String, Object> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            map.put("grant_type", "password");

            ObjectMapper objectMapper = new ObjectMapper();

            HttpResponse<JsonNode> response = Unirest.post("http://localhost:9000/oauth/token")
                    .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString())
                    .basicAuth("clientId", "12345")
                    .fields(map)
                    .asJson();

            System.out.println("\n\n\n" + response.toString() + "\n\n\n");

            if(response.getStatus() == HttpStatus.OK.value()){
                System.out.println("\n\n\nAUTH SUCCESSFUL \n\n\n");
                return new ResponseEntity<>(new ResponseDTO("00", "SUCCESSFUL", objectMapper.readValue(response.getBody().toString(), AuthResponse.class)), HttpStatus.OK);
            }

            return new ResponseEntity<>(new ResponseDTO("03", "WRONG CREDENTIALS"), HttpStatus.UNAUTHORIZED);
        }
        catch (InvalidGrantException ex){
            ex.printStackTrace();
            return new ResponseEntity<Object>(new ResponseDTO("04", ex.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        catch (UnirestException ex){
            ex.printStackTrace();
            return new ResponseEntity<Object>(new ResponseDTO("04", ex.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        catch (IOException ex){
            ex.printStackTrace();
            return new ResponseEntity<Object>(new ResponseDTO("04", ex.getMessage()), HttpStatus.UNAUTHORIZED);
        }

    }


    // TODO: COMPLETE IMPLEMENTATION AND USE
    private boolean deletePreviousOauthToken(String username, String password){

        User user = userRepository.findByUsername(username);
        if(user != null){
            oAuthAccessTokenRepository.delete(oAuthAccessTokenRepository.findByUsername(username));
        }

        return true;
    }


}
