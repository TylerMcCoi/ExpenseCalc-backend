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

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class MoneyChangeBetween extends MoneyChangeTotal {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public MoneyChangeBetween(Account account, List<MoneyChange> moneyChanges, LocalDateTime startDate, LocalDateTime endDate, MonetaryType type){
        super(account, moneyChanges, type);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public MoneyChangeBetween(Account account, Profile profile, List<MoneyChange> moneyChanges, LocalDateTime startDate, LocalDateTime endDate, MonetaryType type){
        super(account, profile, moneyChanges, type);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
