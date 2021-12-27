package com.thermondo.notetakingapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thermondo.notetakingapp.model.AuthenticationRequest;
import com.thermondo.notetakingapp.model.AuthenticationResponse;
import com.thermondo.notetakingapp.model.UserContext;
import com.thermondo.notetakingapp.model.entities.Note;
import com.thermondo.notetakingapp.model.entities.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotebookAppIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAuthentication() throws JSONException {
         String uri = "http://localhost:"+randomServerPort+"/authenticate";
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("foo","foo");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AuthenticationRequest> request = new HttpEntity<>(authenticationRequest, headers);
        ResponseEntity<String> result =   this.restTemplate.postForEntity(uri, request, String.class);
        String authenticationResponse =  result.getBody();
        JSONObject jsonObject = new JSONObject(authenticationResponse);
        Assert.assertNotNull(result);
        uri = "http://localhost:"+randomServerPort+"/api/notes";
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Authorization",(String)jsonObject.get("jwt"));
        HttpEntity<?> httpEntity = new HttpEntity<>(null, headers1);
        ResponseEntity<String> notes =   this.restTemplate.exchange(uri, HttpMethod.GET, httpEntity,String.class);
    }


    @Test
    public void testGetNotesWithoutAuth() throws JSONException {
        String uri = "http://localhost:"+randomServerPort+"/authenticate";
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("foo","foo");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AuthenticationRequest> request = new HttpEntity<>(authenticationRequest, headers);
        ResponseEntity<String> result =   this.restTemplate.postForEntity(uri, request, String.class);
        String authenticationResponse =  result.getBody();
        JSONObject jsonObject = new JSONObject(authenticationResponse);
        Assert.assertNotNull(result);
        uri = "http://localhost:"+randomServerPort+"/api/notes";
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Authorization","Bearer " +(String)jsonObject.get("jwt"));
        HttpEntity<?> httpEntity = new HttpEntity<>(null, headers1);
        ResponseEntity<List> notes =   this.restTemplate.exchange(uri, HttpMethod.GET, httpEntity,List.class);
        Assert.assertNotNull(notes.getBody());
    }

    @Test
    public void testRegisterUser() throws JSONException {
        String uri = "http://localhost:"+randomServerPort+"/authenticate";
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("foo","foo");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AuthenticationRequest> request = new HttpEntity<>(authenticationRequest, headers);
        ResponseEntity<String> result =   this.restTemplate.postForEntity(uri, request, String.class);
        String authenticationResponse =  result.getBody();
        JSONObject jsonObject = new JSONObject(authenticationResponse);
        Assert.assertNotNull(result);
        uri = "http://localhost:"+randomServerPort+"/user/register";
        User user = new User(null,"abc123","123","a@abc.com");
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Authorization","Bearer "+(String)jsonObject.get("jwt"));
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers1);
        ResponseEntity<String> registerResponse =   this.restTemplate.postForEntity(uri, httpEntity, String.class);
    }

    @Test
    public void testLoginUser() throws JSONException, JsonProcessingException {
        String uri = "http://localhost:"+randomServerPort+"/authenticate";
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("foo","foo");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AuthenticationRequest> request = new HttpEntity<>(authenticationRequest, headers);
        ResponseEntity<String> result =   this.restTemplate.postForEntity(uri, request, String.class);
        String authenticationResponse =  result.getBody();
        JSONObject jsonObject = new JSONObject(authenticationResponse);
        Assert.assertNotNull(result);
        uri = "http://localhost:"+randomServerPort+"/user/register";
        User user = new User(null,"abc123","123","a@abc.com");
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Authorization","Bearer "+(String)jsonObject.get("jwt"));
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers1);
        ResponseEntity<String> registerResponse =   this.restTemplate.postForEntity(uri, httpEntity, String.class);
        Assert.assertNotNull(registerResponse.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        User userContext = objectMapper.readValue(registerResponse.getBody(),User.class);
        uri = "http://localhost:"+randomServerPort+"/user/login";
        httpEntity = new HttpEntity<>(userContext, headers1);
        ResponseEntity<String> loginResponse =   this.restTemplate.postForEntity(uri, httpEntity, String.class);
        Assert.assertNotNull(loginResponse.getBody());
    }
}
