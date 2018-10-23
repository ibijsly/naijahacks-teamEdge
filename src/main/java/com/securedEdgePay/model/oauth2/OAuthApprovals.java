package com.securedEdgePay.model.oauth2;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "OAUTH_APPROVALS")
public class OAuthApprovals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERID")
    private String userId;

    @Column(name = "CLIENTID")
    private String clientId;

    @Column(name = "SCOPE")
    private String scope;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "EXPIRESAT")
    private Timestamp expiresAt;

    @Column(name = "LASTMODIFIEDAT")
    private Timestamp lastModifiedAt;

    public OAuthApprovals() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Timestamp getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Timestamp lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
