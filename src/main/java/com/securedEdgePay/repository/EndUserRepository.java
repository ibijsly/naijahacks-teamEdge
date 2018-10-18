package com.securedEdgePay.repository;

import com.securedEdgePay.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndUserRepository extends JpaRepository<EndUser, Long> {
}
