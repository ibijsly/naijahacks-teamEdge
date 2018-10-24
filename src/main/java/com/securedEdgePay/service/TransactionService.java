package com.securedEdgePay.service;

import com.securedEdgePay.model.ResponseModel;
import com.securedEdgePay.model.Transaction;
import com.securedEdgePay.model.User;
import com.securedEdgePay.repository.StatusRepository;
import com.securedEdgePay.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final StatusRepository statusRepository;
    private final WalletService walletService;

    public TransactionService(TransactionRepository transactionRepository, UserService userService, StatusRepository statusRepository, WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.statusRepository = statusRepository;
        this.walletService = walletService;
    }

    public ResponseEntity<ResponseModel> addTransaction(Transaction transaction){
        try{
            transaction.setTransactionId("EP" + System.currentTimeMillis());

            if (transaction.getSender().hasRole("ROLE_AGENT")){
                //Agent wallet should be debited
                if (!walletService.debitWallet(transaction.getSender(), transaction.getAmount() + calculateConvenienceFee(transaction))){
                    return new ResponseEntity<ResponseModel>( new ResponseModel("92", "Insufficient Wallet Fund", transaction.getSender()), HttpStatus.OK);
                }
            }else if (transaction.getSender().hasRole("ROLE_ENDUSER")){
                if (!walletService.debitWallet(transaction.getSender(), transaction.getAmount() + calculateConvenienceFee(transaction))){
                    return new ResponseEntity<ResponseModel>( new ResponseModel("92", "Insufficient Wallet Fund", transaction.getSender()), HttpStatus.OK);
                }
                transaction.setSenderName(transaction.getSender().getFullName());
            }else{
                return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Unauthorized", transaction.getSender()), HttpStatus.UNAUTHORIZED);
            }
            transaction.setStatus(statusRepository.findByValue("Sent"));

            transactionRepository.save(transaction);

            return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", transaction), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", transaction), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModel> addOnlineTransaction(Transaction transaction){
        try{

            transaction.setStatus(statusRepository.findByValue("Sent"));
            transactionRepository.save(transaction);

            return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", transaction), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", transaction), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModel> findBySender(User user){
        try {
            List<Transaction> transactions = transactionRepository.findBySender(user);

            if (transactions.size() < 1)
                return new ResponseEntity<ResponseModel>( new ResponseModel("02", "No Transactions Found", transactions), HttpStatus.NOT_FOUND);

            return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", transactions), HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModel> findByDispatcherAgent(User user){
        try {
            List<Transaction> transactions = transactionRepository.findByDispatcherAgent(user);

            if (transactions.size() < 1)
                return new ResponseEntity<ResponseModel>( new ResponseModel("02", "No Transactions Found", transactions), HttpStatus.NOT_FOUND);

            return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", transactions), HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> findByDispatcherAgentD(User user, int startPage, int pageSize, int draw){

        Pageable pageable = new PageRequest(startPage, pageSize, new Sort(Sort.Direction.DESC, "transactionInitiationDate"));
        Page<Transaction> result = null;
        result = transactionRepository.findByDispatcherAgent(user, pageable);

        Map<String, Object> response = new HashMap<>();

        response.put("data", result.getContent());
        response.put("draw", draw);
        response.put("recordsTotal", result.getTotalElements());
        response.put("recordsFiltered", result.getTotalElements());

        return response;
    }

    public ResponseEntity<ResponseModel> findByReceiverId(String id){
        try {
            List<Transaction> transactions = transactionRepository.findByReceiverId(id);

            if (transactions.size() < 1)
                return new ResponseEntity<ResponseModel>( new ResponseModel("02", "No Transactions Found", transactions), HttpStatus.NOT_FOUND);

            return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", transactions), HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> findByReceiverId(String id, int startPage, int pageSize, int draw){
        Pageable pageable = new PageRequest(startPage, pageSize, new Sort(Sort.Direction.DESC, "transactionInitiationDate"));
        Page<Transaction> result = null;
        result = transactionRepository.findByReceiverId(id, pageable);

        Map<String, Object> response = new HashMap<>();

        for (Transaction tranx:result.getContent()) {
            System.out.println(tranx.getAmount());
        }

        response.put("data", result.getContent());
        response.put("draw", draw);
        response.put("recordsTotal", result.getTotalElements());
        response.put("recordsFiltered", result.getTotalElements());

        return response;
    }

    public ResponseEntity<ResponseModel> findByTransactionId(String tnxId){
        try {
            Transaction transaction = transactionRepository.findByTransactionId(tnxId);

            if (transaction == null)
                return new ResponseEntity<ResponseModel>( new ResponseModel("02", "No Transactions Found", transaction), HttpStatus.NOT_FOUND);

            return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", transaction), HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public double calculateConvenienceFee(Transaction transaction){
        return 50;

//        double rate = 5d / 100;
//        return rate * transaction.getAmount();

    }

    public ResponseEntity updateTransaction(String transactionId, Principal principal){
        Transaction transaction = transactionRepository.findByTransactionId(transactionId);
        User user = userService.getUserByUsername(principal.getName());

        if (user.hasRole("ROLE_AGENT")){
            /*This is the agent cashout location
            * The value should be credited to the agent's wallet*/

            if (walletService.creditWallet(user, transaction.getAmount() - calculateConvenienceFee(transaction))){
                /*Update the transaction*/
                transaction.setStatus(statusRepository.findByValue("Received"));
                transaction.setDispatcherAgent(user);
                transaction.setValueReceivedDate(new Date());
                transaction.setFee(calculateConvenienceFee(transaction));
                transactionRepository.save(transaction);

                /*
                *Send SMS to both the sender and the receiver
                * */

                return new ResponseEntity(new ResponseModel("00", "Transaction Value", transaction), HttpStatus.OK);
            }else{
                return new ResponseEntity(new ResponseModel("99", "Failed", transaction), HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }else if (user.hasRole("ROLE_ENDUSER")){
            /*This is the Receiver Accepting the money into his wallet
             * The value should be credited to the endUser's wallet*/

            if (walletService.creditWallet(user, transaction.getAmount())){
                /*Update the transaction*/
                transaction.setStatus(statusRepository.findByValue("Received"));
                transaction.setDispatcherAgent(user);
                transaction.setValueReceivedDate(new Date());
                transactionRepository.save(transaction);

                /*
                 *Send SMS to both the sender and the receiver
                 * */

                return new ResponseEntity(new ResponseModel("00", "Transaction Value Credited to your wallet", transaction), HttpStatus.OK);
            }else{
                return new ResponseEntity(new ResponseModel("99", "Failed", transaction), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity(new ResponseModel("92", "Unauthorized", null), HttpStatus.UNAUTHORIZED);
        }

    }

    public ResponseEntity<ResponseModel> unclaimedFunds(User user){

        List<Transaction> transactions = transactionRepository.findByStatus(statusRepository.findByValue("Sent"));
        return new ResponseEntity<ResponseModel>(new ResponseModel("00", "Success", transactions), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> recentTransactions(User user){
        try {
            List<Transaction> transactions = transactionRepository.findBySender(user, new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "id")));
            return new ResponseEntity<ResponseModel>(new ResponseModel("00", "Success", transactions), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ResponseModel>(new ResponseModel("99", "Failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
