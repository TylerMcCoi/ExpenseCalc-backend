package com.example.demo.net;

import com.example.demo.account.Account;
import com.example.demo.moneychange.MoneyChangeTotal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class NetAccount {
    private Account account;
    private Double amount;
    private MoneyChangeTotal expenseTotal;
    private MoneyChangeTotal incomeTotal;
    private Net accountNet;
    private List<Net> profileNets;

    public NetAccount(Account account, Double amount, MoneyChangeTotal expenseTotal, MoneyChangeTotal incomeTotal,
                      Net accountNet, List<Net> profileNets){
        this.account = account;
        this.amount = amount;
        this.expenseTotal = expenseTotal;
        this.incomeTotal = incomeTotal;
        this.accountNet = accountNet;
        this.profileNets = profileNets;
    }
}

