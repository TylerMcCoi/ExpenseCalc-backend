package com.example.demo.net;

import com.example.demo.account.Account;
import com.example.demo.account.profile.Profile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

public class Net {
    private Double amount;
    private LocalDateTime expenseDate;
    private Account account;
    @Transient
    private Profile profile;

    public Net(Double amount, Profile profile, Account account){
        this.amount = amount;
        this.profile = profile;
        this.account = account;
    }
    public Net(Double amount, Account account){
        this.amount = amount;
        this.account = account;
    }
}
