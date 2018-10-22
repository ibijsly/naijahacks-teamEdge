package com.securedEdgePay.repository;

import com.securedEdgePay.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    public Status findByValue(String value);
}
