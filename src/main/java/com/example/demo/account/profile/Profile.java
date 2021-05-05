package com.example.demo.account.profile;

import com.example.demo.account.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Profile {
    @SequenceGenerator(
            name = "profile_sequence",
            sequenceName = "profile_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_sequence"
    )
    private Long profileID;
    private String firstName;
    private String lastName;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Account account;

    public Profile(String firstName, String lastName, Account account){
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }
}