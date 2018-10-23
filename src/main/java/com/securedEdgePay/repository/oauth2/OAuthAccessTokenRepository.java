package com.securedEdgePay.repository.oauth2;

import com.securedEdgePay.model.oauth2.OAuthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthAccessTokenRepository extends JpaRepository<OAuthAccessToken, Long> {
    OAuthAccessToken findByUsername(String username);
}
