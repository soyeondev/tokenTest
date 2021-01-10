package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.example.demo.domain.DemoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

@Service
public class DemoService {
	JsonNode jsonNode = null;
	ObjectMapper objMapper = null;

	String accessToken = "";
	String tokenType = "";
	JsonNode response1 = null;

	public DemoDto unirest(String code) {
		DemoDto demoDto = new DemoDto();
		
		CompletableFuture<HttpResponse<JsonNode>> response = Unirest.post("https://localhost:8080/")
				.header("Authorization", "Basic clientid_pwd")
				.field("grant_type","authorization_code")
				.field("code", code)
				.field("redirect_uri", "")
				.asJsonAsync(res -> {
					int code1 = res.getStatus();
					jsonNode = res.getBody();
				});
		
		demoDto.setAccessToken(jsonNode.getObject().get("access_token"));
		demoDto.setTokenType(jsonNode.getObject().get("token_type"));
		demoDto.setRefreshToken(jsonNode.getObject().get("refresh_token"));
		demoDto.setExpiresIn(jsonNode.getObject().get("expires_in"));
		demoDto.setScope(jsonNode.getObject().get("scope"));
		
		accessToken = (String)demoDto.getAccessToken();
		tokenType = (String)demoDto.getTokenType();

		HttpResponse<String> res = Unirest.get("")
				.header("Authorization", tokenType+" "+accessToken)
				.queryString("param1", "")
				.queryString("param2", "")
				.queryString("param3","")
				.asString();

		objMapper(res.getBody());

		return demoDto;
	}

	public void objMapper(String jsonString) {
		ObjectMapper objMapper = new ObjectMapper();
		try {
			APIInfoDto apiDataType = objMapper.readValue(jsonString, APIInfoDto.class);

			DataSetDto[] dataSetDto = apiDataType.getList();
			for(int i = 0; i < dataSetDto.length; i++) {
				DataDetailDto[] dataTypeDto = dataSetDto[i].getDataDetail();
				for(int j = 0; j < dataTypeDto.length; j++) {
					System.out.println(dataTypeDto[j].toString());
				}
			}
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
