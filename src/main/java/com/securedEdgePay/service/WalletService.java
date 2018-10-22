package com.securedEdgePay.service;

import com.securedEdgePay.model.User;
import com.securedEdgePay.model.Wallet;
import com.securedEdgePay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(User user){
        try {
            Wallet wallet = new Wallet(user);

            walletRepository.save(wallet);
            user.setWallet(wallet);
            return wallet;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public Wallet findByUser(User user){
        return walletRepository.findByUser(user);

    }

    public boolean creditWallet(User user, double amount){
        try {
            Wallet wallet = findByUser(user);

            if (null == wallet)
                wallet = createWallet(user);

            wallet.setBalance(wallet.getBalance() + amount);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean debitWallet(User user, double amount){
        try{
            Wallet wallet = findByUser(user);

            if (null == wallet)
                return false;

            if (wallet.getBalance() < amount)
                return false;

            wallet.setBalance(wallet.getBalance() - amount);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
