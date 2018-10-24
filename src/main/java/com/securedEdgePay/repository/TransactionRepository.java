package com.securedEdgePay.repository;

import com.securedEdgePay.model.Status;
import com.securedEdgePay.model.Transaction;
import com.securedEdgePay.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findBySender(User user);
    public List<Transaction> findBySenderName(String sender);

    public List<Transaction> findByReceiverId(String receiverId);
    public Page<Transaction> findByReceiverId(String receiverId, Pageable pageable);
    public List<Transaction> findByReceiverName(String receiverName);

    public List<Transaction> findByDispatcherAgent(User dispatcherAgent);
    public Page<Transaction> findByDispatcherAgent(User dispatcherAgent, Pageable pageable);
    public Transaction findByTransactionId(String transactionId);
    public List<Transaction> findByStatus(Status status);
    public List<Transaction> findBySender(User user, Pageable pageable);
}
