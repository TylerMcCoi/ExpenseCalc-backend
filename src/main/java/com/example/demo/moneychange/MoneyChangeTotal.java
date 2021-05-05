package com.example.demo.moneychange;

import com.example.demo.account.Account;
import com.example.demo.account.profile.Profile;
import com.example.demo.moneychange.monetarytype.MonetaryType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class MoneyChangeTotal {
    private Account account;
    private Optional<Profile> profile;
    private LocalDateTime requestTime;
    private Double total;
    private List<MoneyChange> moneyChanges;
    private MonetaryType monetaryType;
    public MoneyChangeTotal(Account account, List<MoneyChange> moneyChange, MonetaryType type){
        this.account = account;
        this.moneyChanges = moneyChanges;
        this.total = CalcTotal(moneyChanges);
        this.requestTime = LocalDateTime.now();
        this.monetaryType = type;
    }
    public MoneyChangeTotal(Account account, Profile profile, List<MoneyChange> moneyChanges, MonetaryType type){
        this.account = account;
        this.moneyChanges = moneyChanges;
        this.total = CalcTotal(moneyChanges);
        this.requestTime = LocalDateTime.now();
        this.monetaryType = type;
    }
    public Double CalcTotal(List<MoneyChange> moneyChanges){
        Double total = 0.00;
        for(MoneyChange moneyChange : moneyChanges){
            total += moneyChange.getAmount();
        }
        return total;
    }
}
