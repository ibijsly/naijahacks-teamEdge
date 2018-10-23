package com.securedEdgePay.model.oauth2;

import javax.persistence.*;

@Entity
@Table(name = "OAUTH_REFRESH_TOKEN")
public class OAuthRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOKEN_ID")
    private String tokenId;

    @Column(name = "TOKEN")
    private String token;

    @Lob
    @Column(name = "AUTHENTICATION")
    private String authenticationId;

    public OAuthRefreshToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
