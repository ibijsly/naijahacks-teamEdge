package com.securedEdgePay.repository.oauth2;

import com.securedEdgePay.model.oauth2.OAuthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, Long> {
}
