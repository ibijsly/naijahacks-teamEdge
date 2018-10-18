package com.securedEdgePay.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private double amount;
    private String senderName;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;

    private String receiverIdType;
    private String receiverId;
    private String receiverName;

    private Date transactionInitiationDate;
    private Date ValueReceivedDate;

    /*This is the agent the receiver collected from*/
    @ManyToOne
    @JoinColumn(name = "dispatcher_agent", referencedColumnName = "id")
    private User dispatcherAgent;

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id")
    private Status status;

    private double fee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getReceiverIdType() {
        return receiverIdType;
    }

    public void setReceiverIdType(String receiverIdType) {
        this.receiverIdType = receiverIdType;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Date getTransactionInitiationDate() {
        return transactionInitiationDate;
    }

    public void setTransactionInitiationDate(Date transactionInitiationDate) {
        this.transactionInitiationDate = transactionInitiationDate;
    }

    public Date getValueReceivedDate() {
        return ValueReceivedDate;
    }

    public void setValueReceivedDate(Date valueReceivedDate) {
        ValueReceivedDate = valueReceivedDate;
    }

    public User getDispatcherAgent() {
        return dispatcherAgent;
    }

    public void setDispatcherAgent(User dispatcherAgent) {
        this.dispatcherAgent = dispatcherAgent;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
