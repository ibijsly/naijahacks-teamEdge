package com.securedEdgePay.service;

import com.securedEdgePay.model.EndUser;
import com.securedEdgePay.model.ResponseModel;
import com.securedEdgePay.repository.EndUserRepository;
import com.securedEdgePay.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;

@Service
public class EndUserService {
    private final EndUserRepository endUserRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public EndUserService(EndUserRepository endUserRepository, UserRepository userRepository, UserService userService) {
        this.endUserRepository = endUserRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public ResponseEntity<ResponseModel> addAgent(EndUser endUser, Principal principal){
        try {
            if (check(endUser))
                return new ResponseEntity<ResponseModel>( new ResponseModel("01", "User Already Exist", endUser), HttpStatus.CONFLICT);

//            endUser.setRegisteredBy(userService.getUserByUsername(principal.getName()).getId());
//            endUser.setRoleGroup(roleGroupService.getRoleGroup(endUser.getRoleGroupType()));
            endUser.setCreatedAt(new Date());
            endUserRepository.save(endUser);
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
        return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", endUser), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public boolean check(EndUser endUser){

        return userRepository.findByUsername(endUser.getUsername()) != null;
    }
}
