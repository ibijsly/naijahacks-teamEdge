package com.securedEdgePay.controller;

import com.securedEdgePay.model.EndUser;
import com.securedEdgePay.model.Transaction;
import com.securedEdgePay.model.User;
import com.securedEdgePay.service.EndUserService;
import com.securedEdgePay.service.TransactionService;
import com.securedEdgePay.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.NumberFormat;
import java.util.List;

@Controller
public class DashboardController {

    private final UserService userService;
    private final TransactionService transactionService;
    private final EndUserService endUserService;

    public DashboardController(UserService userService, TransactionService transactionService, EndUserService endUserService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.endUserService = endUserService;
    }

    @RequestMapping({"/dashboard", "/home"})
    public String landingPage(Principal principal){
        String role = userService.getUserByUsername(principal.getName()).getRoleGroup().getName();

        if (role.equalsIgnoreCase("AGENT"))
            return "redirect:/agentdashboard";

        if (role.equalsIgnoreCase("ENDUSER"))
            return "redirect:/userdashboard";

        return "dashboard";
    }

    @RequestMapping("/agentdashboard")
    public String agentDashboard(Model model, Principal principal){
        User user = userService.getUserByUsername(principal.getName());
        List<Transaction> transactions = (List<Transaction>) transactionService.findByDispatcherAgent(user).getBody().getObject();
        List<Transaction> transactions1 = (List<Transaction>) transactionService.findBySender(user).getBody().getObject();
        double sum = 0;
        for (Transaction transaction: transactions)
            sum += transaction.getAmount();
        for (Transaction transaction: transactions1)
            sum += transaction.getAmount();

        model.addAttribute("balance", user.getWallet().getBalance());
        model.addAttribute("sValue", 100);
        model.addAttribute("tVolume", transactions.size() + transactions1.size());
        model.addAttribute("tValue", sum);
        return "agentDashboard";
    }

    @RequestMapping("/userdashboard")
    public String endUserDashboard(Model model, Principal principal){
        User user = userService.getUserByUsername(principal.getName());
        EndUser endUser = (EndUser) endUserService.getEndUserByUsername(principal.getName()).getBody().getObject();
        List<Transaction> unclaimedTransactions = (List<Transaction>) transactionService.unclaimedFunds(user).getBody().getObject();
        List<Transaction> recentTransactions = (List<Transaction>) transactionService.recentTransactions(user).getBody().getObject();
        List<Transaction> sentTransactions = (List<Transaction>) transactionService.findBySender(user).getBody().getObject();
        List<Transaction> receivedTransactions = (List<Transaction>) transactionService.findByReceiverId(endUser.getNin()).getBody().getObject();

        long tnxVol = sentTransactions.size() + receivedTransactions.size();
        double sent = 0, received = 0;

        double sum = 0;
        for (Transaction transaction: sentTransactions)
            sent += transaction.getAmount();

        for (Transaction transaction: receivedTransactions)
            received += transaction.getAmount();

        for (Transaction transaction: unclaimedTransactions)
            sum += transaction.getAmount();

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setGroupingUsed(true);
        nf.setMinimumFractionDigits(2);

        model.addAttribute("balance", nf.format(user.getWallet().getBalance()));
        model.addAttribute("unclaimed", unclaimedTransactions);
        model.addAttribute("recent", recentTransactions);
        model.addAttribute("tVolume", tnxVol);
        model.addAttribute("tValue", nf.format((sent + received)));
        model.addAttribute("sentAmount", nf.format(sent));
        model.addAttribute("receivedAmount", nf.format(received));
        model.addAttribute("unclaimedSum", nf.format(sum));
        model.addAttribute("user", user);
        return "userDashboard";
    }



}
