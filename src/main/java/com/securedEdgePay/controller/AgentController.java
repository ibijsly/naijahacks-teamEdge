package com.securedEdgePay.controller;

import com.securedEdgePay.model.Agent;
import com.securedEdgePay.model.Bank;
import com.securedEdgePay.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/agent")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addAgent(Model model, Principal principal){
        return "addAgent";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addAgent(@RequestBody Map<String, Object> allRequestParams, Principal principal){

        Agent agent = new Agent();
        Bank bank = new Bank();

        if (allRequestParams.get("fname") != null && !allRequestParams.get("fname").toString().isEmpty())
            agent.setFirstname(allRequestParams.get("fname").toString());

        if (allRequestParams.get("lname") != null && !allRequestParams.get("lname").toString().isEmpty())
            agent.setLastname(allRequestParams.get("lname").toString());

        if (allRequestParams.get("email") != null && !allRequestParams.get("email").toString().isEmpty())
            agent.setEmail(allRequestParams.get("email").toString());

        if (allRequestParams.get("phone") != null && !allRequestParams.get("phone").toString().isEmpty())
            agent.setPhone(allRequestParams.get("phone").toString());

        if (allRequestParams.get("accountName") != null && !allRequestParams.get("accountName").toString().isEmpty())
            bank.setAccountName(allRequestParams.get("accountName").toString());

        if (allRequestParams.get("accountNumber") != null && !allRequestParams.get("accountNumber").toString().isEmpty())
            bank.setAccountNumber(allRequestParams.get("accountNumber").toString());

        if (allRequestParams.get("state") != null && !allRequestParams.get("state").toString().isEmpty())
            agent.setState(allRequestParams.get("state").toString());

        if (allRequestParams.get("lg") != null && !allRequestParams.get("lg").toString().isEmpty())
            agent.setLg(allRequestParams.get("lg").toString());

        if (allRequestParams.get("address") != null && !allRequestParams.get("address").toString().isEmpty())
            agent.setAddress(allRequestParams.get("address").toString());

        if (allRequestParams.get("bank") != null && !allRequestParams.get("bank").toString().isEmpty())
            bank.setBankCode(allRequestParams.get("bank").toString());

        switch (bank.getBankCode()) {
            case "058":
                bank.setBankName("GTBank");
                break;
            case "011":
                bank.setBankName("FBN");
                break;
            case "033":
                bank.setBankName("UBA");
                break;
            default:
                bank.setBankName("Undefined");

        }

        agent.setBank(bank);
        return agentService.addAgent(agent, principal);
    }

    public ResponseEntity sendMoney(){

        /*Algorithm
        *
        * Receive the NIN of the receiver
        * Verify the NIN thereby getting the name image and details of the Receiver (Phone number, etc)
        * Confirm this with the Sender
        * Fill in amount, Receiver Phone, sender phone, name, any other needed details and then click on send
        *
        * Debit agent's wallet first (if insufficient funds, return failed)
        * Credit the NIN
        * Send SMS to both the Sender and the Receiver
        * */


        return null;
    }
}
