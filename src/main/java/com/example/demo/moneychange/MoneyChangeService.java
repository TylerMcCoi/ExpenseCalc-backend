package com.example.demo.moneychange;
import com.example.demo.account.Account;
import com.example.demo.account.AccountService;
import com.example.demo.account.profile.Profile;
import com.example.demo.account.profile.ProfileService;
import com.example.demo.moneychange.category.Category;
import com.example.demo.moneychange.monetarytype.MonetaryType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MoneyChangeService {

    private final AccountService accountService;
    private final ProfileService profileService;
    private final MoneyChangeRepository moneyChangeRepository;

    public String newMoneyChange(MoneyChangeRequest request, Long profileID) {
        LocalDateTime dt = request.getLocalDateTime();
        if (dt == null){
            dt = LocalDateTime.now();
        }
        Category ct = request.getCategory();
        if (ct == null){
            ct = Category.DEFAULT_EXPENSE;
        }
        Account account = accountService.getCurrentAccount();
        Profile profile = profileService.getProfile(profileID).get();
        moneyChangeRepository.save(new MoneyChange(
                        request.getAmount(),
                        request.getMoneyChangeName(),
                        ct,
                        request.getType(),
                        dt,
                        profile,
                        account
                ));
        return "MoneyChange Added";

    }
    //MoneyChange
    public Optional<List<MoneyChange>> getMoneyChanges() {

        Account account = accountService.getCurrentAccount();
        return moneyChangeRepository.findMoneyChangeByAccount(account);
    }
    public Optional<List<MoneyChange>> getMoneyChangesByProfile(Long profileID) {

        Account account = accountService.getCurrentAccount();
        Profile profile = profileService.getProfile(profileID).get();
        return moneyChangeRepository.findMoneyChangeByProfile(account, profile);
    }

    //MoneyChange
    public Optional<List<MoneyChange>> getMoneyChanges(MonetaryType type) {

        Account account = accountService.getCurrentAccount();
        return moneyChangeRepository.findMoneyChangeByAccount(account);
    }
    public Optional<List<MoneyChange>> getMoneyChangesByProfile(Long profileID, MonetaryType type) {
        Account account = accountService.getCurrentAccount();
        Profile profile = profileService.getProfile(profileID).get();
        return moneyChangeRepository.findMoneyChangeByProfile(account, profile);
    }

    public MoneyChangeTotal getMoneyChangeTotal(MonetaryType type) {
        Account account = accountService.getCurrentAccount();
        List<MoneyChange> moneyChanges = moneyChangeRepository.findMoneyChangeByAccount(account, type).get();
        return new MoneyChangeTotal(account, moneyChanges, type);
    }
    public MoneyChangeTotal getMoneyChangeTotalByProfile(Long profileID, MonetaryType type) {
        Account account = accountService.getCurrentAccount();
        Profile profile = profileService.getProfile(profileID).get();
        List<MoneyChange> moneyChanges = moneyChangeRepository.findMoneyChangeByProfile(account,profile, type).get();
        return new MoneyChangeTotal(account, profile, moneyChanges, type);
    }
    public MoneyChangeBetween getMoneyChangeBetweenByProfile(Long profileID, LocalDateTime startDate, LocalDateTime endDate, MonetaryType type) {
        Account account = accountService.getCurrentAccount();
        Profile profile = profileService.getProfile(profileID).get();
        List<MoneyChange> moneyChanges = moneyChangeRepository.findMoneyChangeBetweenByProfile(account,profile,startDate,endDate, type).get();
        return new MoneyChangeBetween(account, profile, moneyChanges, startDate, endDate, type);
    }

    public MoneyChangeBetween getMoneyChangeBetween(LocalDateTime startDate, LocalDateTime endDate, MonetaryType type) {
        Account account = accountService.getCurrentAccount();
        List<MoneyChange> moneyChanges = moneyChangeRepository.findMoneyChangeBetweenByAccount(account,startDate,endDate, type).get();
        return new MoneyChangeBetween(account, moneyChanges, startDate, endDate, type);
    }

}
