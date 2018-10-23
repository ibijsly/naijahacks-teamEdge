package com.securedEdgePay.model.oauth2;

import javax.persistence.*;

@Entity
@Table(name = "OAUTH_ACCESS_TOKEN")
public class OAuthAccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOKEN_ID")
    private String tokenId;

    @Lob
    @Column(name = "TOKEN", columnDefinition = "LONGTEXT")
    private String token;

    @Column(name = "AUTHENTICATION_ID")
    private String authenticationId;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "CLIENT_ID")
    private String clientId;

    @Lob
    @Column(name = "AUTHENTICATION")
    private String authentication;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    public OAuthAccessToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
