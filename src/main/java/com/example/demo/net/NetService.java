package com.example.demo.net;

import com.example.demo.account.AccountService;
import com.example.demo.account.profile.Profile;
import com.example.demo.account.profile.ProfileService;
import com.example.demo.moneychange.MoneyChange;
import com.example.demo.moneychange.MoneyChangeService;
import com.example.demo.moneychange.MoneyChangeTotal;

import com.example.demo.moneychange.monetarytype.MonetaryType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NetService {

    private final MoneyChangeService moneyChangeService;
    private final AccountService accountService;
    private final ProfileService profileService;

    public Net getNetforAccount(){
        Double expenseTotal = moneyChangeService.getMoneyChangeTotal(MonetaryType.EXPENSE).getTotal();
        Double incomeTotal = moneyChangeService.getMoneyChangeTotal(MonetaryType.INCOME).getTotal();
        Double netAmount = incomeTotal - expenseTotal;
        Net net = new Net(
                netAmount,
                accountService.getCurrentAccount()
        );
        return net;
    }
    public Net getNetforProfile(Long profileID){
        Double expenseTotal = moneyChangeService.getMoneyChangeTotalByProfile(profileID, MonetaryType.EXPENSE).getTotal();
        Double incomeTotal = moneyChangeService.getMoneyChangeTotalByProfile(profileID, MonetaryType.INCOME).getTotal();
        Double netAmount = incomeTotal - expenseTotal;
        Net net = new Net(
                netAmount,
                profileService.getProfile(profileID).get(),
                accountService.getCurrentAccount()
        );
        return net;
    }
    public NetAccount getTotalNetforAccount(){
        MoneyChangeTotal expenseTotal = moneyChangeService.getMoneyChangeTotal(MonetaryType.EXPENSE);
        MoneyChangeTotal incomeTotal = moneyChangeService.getMoneyChangeTotal(MonetaryType.INCOME);

        Double expenseAmount = expenseTotal.getTotal();
        Double incomeAmount = incomeTotal.getTotal();
        Double netAmount = incomeAmount - expenseAmount;

        Net accountNet = getNetforAccount();

        List<Profile> profiles = profileService.getProfiles().get();
        List<Net> profileNets = new ArrayList<>();

        for(Profile profile : profiles){
            Net currNet = getNetforProfile(profile.getProfileID());
            profileNets.add(currNet);
        }
        NetAccount netAccount = new NetAccount(
                accountService.getCurrentAccount(),
                netAmount,
                expenseTotal,
                incomeTotal,
                accountNet,
                profileNets
        );
        return netAccount;
    }
}
