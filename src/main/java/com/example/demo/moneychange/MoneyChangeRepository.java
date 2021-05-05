package com.example.demo.moneychange;

import com.example.demo.account.Account;
import com.example.demo.account.profile.Profile;
import com.example.demo.moneychange.monetarytype.MonetaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MoneyChangeRepository extends JpaRepository<MoneyChange, Long> {

    //MoneyChange
    @Query("SELECT s FROM MoneyChange s WHERE s.account = ?1")
    Optional<List<MoneyChange>> findMoneyChangeByAccount(Account account);

    @Query("SELECT s FROM MoneyChange s WHERE s.account = ?1 and s.profile =?2")
    Optional<List<MoneyChange>> findMoneyChangeByProfile(Account account, Profile profile);

    @Query("SELECT s FROM MoneyChange s WHERE s.account = ?1 and s.monetaryDate >= ?2 and s.monetaryDate <= ?3")
    Optional<List<MoneyChange>> findMoneyChangeBetweenByAccount(Account account, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT s FROM MoneyChange s WHERE s.account = ?1 and s.profile =?2 and s.monetaryDate >= ?3 and s.monetaryDate <= ?4")
    Optional<List<MoneyChange>> findMoneyChangeBetweenByProfile(Account account, Profile profile, LocalDateTime startDate, LocalDateTime endDate);
    //Expenses Only
    @Query("SELECT s FROM MoneyChange s WHERE s.account = ?1 and s.type = s.type ")
    Optional<List<MoneyChange>> findMoneyChangeByAccount(Account account, MonetaryType type);

    @Query("SELECT s FROM MoneyChange s WHERE s.account = ?1 and s.profile =?2 and s.type = s.type")
    Optional<List<MoneyChange>> findMoneyChangeByProfile(Account account, Profile profile, MonetaryType type);

    @Query("SELECT s FROM MoneyChange s WHERE s.account = ?1 and s.monetaryDate >= ?2 and s.monetaryDate <= ?3 and s.type = s.type")
    Optional<List<MoneyChange>> findMoneyChangeBetweenByAccount(Account account, LocalDateTime startDate, LocalDateTime endDate, MonetaryType type);

    @Query("SELECT s FROM MoneyChange s WHERE s.account = ?1 and s.profile =?2 and s.monetaryDate >= ?3 and s.monetaryDate <= ?4 and s.type = s.type")
    Optional<List<MoneyChange>> findMoneyChangeBetweenByProfile(Account account, Profile profile, LocalDateTime startDate, LocalDateTime endDate, MonetaryType type);

}
