package com.securedEdgePay.repository;

import com.securedEdgePay.model.User;
import com.securedEdgePay.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    public Wallet findByUser(User user);
}
