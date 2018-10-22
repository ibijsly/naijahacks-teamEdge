package com.securedEdgePay.controller;

import com.securedEdgePay.model.ResponseModel;
import com.securedEdgePay.model.Transaction;
import com.securedEdgePay.model.User;
import com.securedEdgePay.service.AgentService;
import com.securedEdgePay.service.TransactionService;
import com.securedEdgePay.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final AgentService agentService;
    private final UserService userService;

    public TransactionController(TransactionService transactionService, AgentService agentService, UserService userService) {
        this.transactionService = transactionService;
        this.agentService = agentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addTransaction(@RequestBody Map<String, Object> details, Principal principal){

        String userMail = details.get("logMail") != null && !details.get("logMail").toString().isEmpty() ? details.get("logMail").toString() : "";
        User user = userService.getUserByUsername(userMail);

        if (user == null)
            return new ResponseEntity(new ResponseModel("91", "Unauthorized", user), HttpStatus.UNAUTHORIZED);

        Transaction transaction = new Transaction();
//        transaction.setTransactionId("EDP" + System.currentTimeMillis() / 1000);
        transaction.setAmount(Double.parseDouble(details.get("amount").toString()));
        transaction.setSenderName(details.get("senderName").toString());
        transaction.setSenderPhone(details.get("senderPhone").toString());
        transaction.setReceiverIdType(details.get("idType").toString());
        transaction.setReceiverId(details.get("id").toString());
        transaction.setReceiverPhone(details.get("receiverPhone").toString());
        transaction.setReceiverName(details.get("receiver").toString());
        transaction.setTransactionInitiationDate(new Date());
        transaction.setSender(user);

        return transactionService.addTransaction(transaction, principal);
    }

    @RequestMapping(value = "/fetch/id", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getById(@RequestParam String transactionId, Principal principal){
        return transactionService.findByTransactionId(transactionId);
    }

    @RequestMapping(value = "/fetch/nin", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getByNIN(@RequestParam String nin, Principal principal){
        return transactionService.findByReceiverId(nin);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity update(@RequestParam String transactionId, Principal principal){
        return transactionService.updateTransaction(transactionId, principal);
    }

    @RequestMapping(value = "/agent/fetch", method = RequestMethod.GET)
    public Map getAllAgentTransaction(Principal principal, @RequestParam Map<String, String> allRequestParams){

        int draw = Integer.parseInt(allRequestParams.get("draw"));
        int start = Integer.parseInt(allRequestParams.get("start"));
        int length = Integer.parseInt(allRequestParams.get("length"));

        User user = userService.getUserByUsername(principal.getName());
        return transactionService.findByDispatcherAgentD(user, start/length, length, draw);
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String sendMoney(Model model){

        return "deposit";
    }

    @RequestMapping(value = "/receive", method = RequestMethod.GET)
    public String receiveMoney(Model model){

        return "receive";
    }
}
