package com.securedEdgePay.utility;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HttpRequestUtil {

    public static HttpResponse<JsonNode> makePostRequest(String url, Map<String, Object> params, Map<String, String> headers,
                                                         Map<String, String> authCredentials) throws UnirestException {

        Gson gson = new Gson();

        HttpRequestWithBody request = Unirest.post(url);

        if(headers != null)
            request = request.headers(headers);
        if(authCredentials != null){
            if(authCredentials.containsKey("username") && authCredentials.containsKey("password")){
                request = request.basicAuth(authCredentials.get("username"), authCredentials.get("password"));
            }
        }

        RequestBodyEntity finalRequest = request.body(gson.toJson(params));

        HttpResponse<JsonNode> response = finalRequest.asJson();

        return response;
    }

    public static HttpResponse<JsonNode> makePostRequest(String url, Object object, Map<String, String> headers,
                                                         Map<String, String> authCredentials) throws UnirestException{

        Gson gson = new Gson();

        HttpRequestWithBody request = Unirest.post(url);

        if(headers != null)
            request = request.headers(headers);
        if(authCredentials != null){
            if(authCredentials.containsKey("username") && authCredentials.containsKey("password")){
                request = request.basicAuth(authCredentials.get("username"), authCredentials.get("password"));
            }
        }

        RequestBodyEntity finalRequest = request.body(gson.toJson(object));

        HttpResponse<JsonNode> response = finalRequest.asJson();

        return response;
    }

    public static HttpResponse<JsonNode> makeGetRequest(String url, Map<String, String> headers,
                                                        Map<String, String> authCredentials) throws UnirestException{

        GetRequest request = Unirest.get(url);

        if(headers != null)
            request = request.headers(headers);
        if(authCredentials != null){
            if(authCredentials.containsKey("username") && authCredentials.containsKey("password")){
                request = request.basicAuth(authCredentials.get("username"), authCredentials.get("password"));
            }
        }

        HttpResponse<JsonNode> response = request.asJson();

        return response;
    }
}
