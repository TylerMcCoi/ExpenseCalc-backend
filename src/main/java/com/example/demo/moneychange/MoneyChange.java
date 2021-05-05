package com.example.demo.moneychange;

import com.example.demo.account.Account;
import com.example.demo.account.profile.Profile;
import com.example.demo.moneychange.category.Category;
import com.example.demo.moneychange.monetarytype.MonetaryType;
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
@Entity
@Table
public class MoneyChange {
    @Id
    @SequenceGenerator(
            name = "money_sequence",
            sequenceName = "money_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "money_sequence"
    )
    private Long moneyID;
    private Double amount;
    private String moneyChangeName;
    private Category category;
    private MonetaryType type;
    private LocalDateTime monetaryDate;
    @ManyToOne
    private Profile profile;
    @ManyToOne
    private Account account;
    /*@Transient
    private Integer age;*/

    public MoneyChange(Double amount, String moneyChangeName, Category category, MonetaryType type, LocalDateTime monetaryDate, Profile profile, Account account){
        this.amount = amount;
        this.moneyChangeName = moneyChangeName;
        this.category = category;
        this.type = type;
        this.monetaryDate = monetaryDate;
        this.profile = profile;
        this.account = account;
    }

}
