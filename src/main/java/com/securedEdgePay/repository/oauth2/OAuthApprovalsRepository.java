package com.securedEdgePay.repository.oauth2;

import com.securedEdgePay.model.oauth2.OAuthApprovals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthApprovalsRepository extends JpaRepository<OAuthApprovals, Long> {
}
