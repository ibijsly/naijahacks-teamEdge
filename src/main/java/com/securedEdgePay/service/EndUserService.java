package com.securedEdgePay.service;

import com.securedEdgePay.model.EndUser;
import com.securedEdgePay.model.ResponseModel;
import com.securedEdgePay.model.Transaction;
import com.securedEdgePay.repository.BankRepository;
import com.securedEdgePay.repository.EndUserRepository;
import com.securedEdgePay.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class EndUserService {
    private final EndUserRepository endUserRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final WalletService walletService;
    private final BankRepository bankRepository;
    private final TransactionService transactionService;

    public EndUserService(EndUserRepository endUserRepository, UserRepository userRepository, UserService userService, WalletService walletService, BankRepository bankRepository, TransactionService transactionService) {
        this.endUserRepository = endUserRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.walletService = walletService;
        this.bankRepository = bankRepository;
        this.transactionService = transactionService;
    }

    public ResponseEntity<ResponseModel> addEnduser(EndUser endUser, Principal principal){
        try {
            if (check(endUser))
                return new ResponseEntity<ResponseModel>( new ResponseModel("01", "User Already Exist", endUser), HttpStatus.CONFLICT);

//            endUser.setRegisteredBy(userService.getUserByUsername(principal.getName()).getId());
//            endUser.setRoleGroup(roleGroupService.getRoleGroup(endUser.getRoleGroupType()));
            endUser.setCreatedAt(new Date());
            endUser.setPassword(new BCryptPasswordEncoder().encode("welcome"));
            endUser.setEnabled(true);
            bankRepository.save(endUser.getBank());

            endUserRepository.save(endUser);
            walletService.createWallet(endUser);

            return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", endUser), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", endUser), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModel> getEndUserByUsername(String username){
        try {
            EndUser endUser= (EndUser) userRepository.findByUsername(username);

            if (endUser == null)
                return new ResponseEntity<ResponseModel>( new ResponseModel("02", "Not Found", endUser), HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", endUser), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModel> getUserTransactions(EndUser endUser){

        if (endUser != null){
            return transactionService.findBySender(endUser);
//            ResponseEntity<ResponseModel> responseModelResponseEntity = transactionService.findBySender(endUser);
//            ResponseModel responseModel = responseModelResponseEntity.getBody();
//
//            List<Transaction> transactions = (List<Transaction>) responseModel.getObject();
        }else
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", endUser), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public boolean check(EndUser endUser){

        return userRepository.findByUsername(endUser.getUsername()) != null && endUserRepository.findByNin(endUser.getNin()) != null;
    }
}
