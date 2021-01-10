package com.example.demo.domain;


public class DemoDto {
	private Object accessToken;
	private Object tokenType;
	private Object refreshToken;
	private Object expiresIn;
	private Object scope;
	
	public Object getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(Object accessToken) {
		this.accessToken = accessToken;
	}
	public Object getTokenType() {
		return tokenType;
	}
	public void setTokenType(Object tokenType) {
		this.tokenType = tokenType;
	}
	public Object getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(Object refreshToken) {
		this.refreshToken = refreshToken;
	}
	public Object getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Object expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Object getScope() {
		return scope;
	}
	public void setScope(Object scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "DemoDto [accessToken=" + accessToken + ", tokenType=" + tokenType + ", refreshToken=" + refreshToken
				+ ", expiresIn=" + expiresIn + ", scope=" + scope + "]";
	}
	

	
}
