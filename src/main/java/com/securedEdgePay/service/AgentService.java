package com.securedEdgePay.service;


import com.securedEdgePay.model.Agent;
import com.securedEdgePay.model.ResponseModel;
import com.securedEdgePay.repository.AgentRepository;
import com.securedEdgePay.repository.BankRepository;
import com.securedEdgePay.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;

@Service
public class AgentService {
    private final UserRepository userRepository;
    private final AgentRepository agentRepository;
    private final WalletService walletService;
    private final BankRepository bankRepository;

    public AgentService(UserRepository userRepository, AgentRepository agentRepository, WalletService walletService, BankRepository bankRepository) {
        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
        this.walletService = walletService;
        this.bankRepository = bankRepository;
    }

    @Transactional
    public ResponseEntity<ResponseModel> addAgent(Agent agent, Principal principal){
        try {
            if (check(agent))
                return new ResponseEntity<ResponseModel>( new ResponseModel("01", "User Already Exist", agent), HttpStatus.CONFLICT);

//            agent.setRegisteredBy(userService.getUserByUsername(principal.getName()).getId());
//            endUser.setRoleGroup(roleGroupService.getRoleGroup(endUser.getRoleGroupType()));
            agent.setUsername(agent.getEmail());
            agent.setCreatedAt(new Date());
            agent.setPassword(new BCryptPasswordEncoder().encode("welcome"));
            agent.setEnabled(true);
            bankRepository.save(agent.getBank());
            agentRepository.save(agent);
            walletService.createWallet(agent);


            LoggerFactory.getLogger(AgentService.class).info("Agent Created Successfully");

            return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", agent), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", agent), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModel> getAgentByUsername(String username){
        try {
            Agent agent = (Agent) userRepository.findByUsername(username);

            if (agent == null)
                return new ResponseEntity<ResponseModel>( new ResponseModel("02", "Not Found", agent), HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<ResponseModel>( new ResponseModel("00", "Success", agent), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModel> getAgentTransactions(Agent agent){
        return new ResponseEntity<ResponseModel>( new ResponseModel("99", "Error Occurred", agent), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public boolean check(Agent agent){

        return userRepository.findByUsername(agent.getUsername()) != null;
    }
}
