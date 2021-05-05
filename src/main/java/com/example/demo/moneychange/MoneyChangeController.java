package com.example.demo.moneychange;

import com.example.demo.moneychange.monetarytype.MonetaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/money")
public class MoneyChangeController {
    private final MoneyChangeService moneyChangeService;
    private final String time = "T00:00:00";

    @Autowired
    public MoneyChangeController(MoneyChangeService moneyChangeService) {
        this.moneyChangeService = moneyChangeService;
    }
    //MoneyChanges
    @GetMapping
    public Optional<List<MoneyChange>> getMoneyChanges() {
        return moneyChangeService.getMoneyChanges();
    }

    @GetMapping("{profileID}")
    public Optional<List<MoneyChange>> getMoneyChangesByProfile(@PathVariable Long profileID) {
        return moneyChangeService.getMoneyChangesByProfile(profileID);
    }

    //Expenses Only
    @GetMapping("expense")
    public Optional<List<MoneyChange>> getExpenses() {
        return moneyChangeService.getMoneyChanges(MonetaryType.EXPENSE);
    }

    @GetMapping("expense/{profileID}")
    public Optional<List<MoneyChange>> getExpensesByProfile(@PathVariable Long profileID) {
        return moneyChangeService.getMoneyChangesByProfile(profileID, MonetaryType.EXPENSE);
    }
    @GetMapping("expense/total")
    public MoneyChangeTotal getExpenseTotal() {
        return moneyChangeService.getMoneyChangeTotal(MonetaryType.EXPENSE);
    }
    @GetMapping("expense/total/{profileID}")
    public MoneyChangeTotal getExpenseTotalByProfile(@PathVariable Long profileID) {
        return moneyChangeService.getMoneyChangeTotalByProfile(profileID, MonetaryType.EXPENSE);
    }
    @GetMapping("expense/total/{profileID}/between/{start}/{end}")
    public MoneyChangeBetween getExpenseBetweenByProfile(@PathVariable Long profileID,
                                                     @PathVariable String start, @PathVariable String end) {
        LocalDateTime startDate = LocalDateTime.parse(start + time);
        LocalDateTime endDate = LocalDateTime.parse(end + time);
        return moneyChangeService.getMoneyChangeBetweenByProfile(profileID,startDate,endDate, MonetaryType.EXPENSE);
    }
    @GetMapping("expense/total/between/{start}/{end}")
    public MoneyChangeBetween getExpenseBetween(@PathVariable String start, @PathVariable String end) {
        LocalDateTime startDate = LocalDateTime.parse(start + time);
        LocalDateTime endDate = LocalDateTime.parse(end + time);
        return moneyChangeService.getMoneyChangeBetween(startDate,endDate, MonetaryType.EXPENSE);
    }
    //Incomes only
    @GetMapping("income")
    public Optional<List<MoneyChange>> getIncomes() {
        return moneyChangeService.getMoneyChanges(MonetaryType.INCOME);
    }

    @GetMapping("income/{profileID}")
    public Optional<List<MoneyChange>> getIncomesByProfile(@PathVariable Long profileID) {
        return moneyChangeService.getMoneyChangesByProfile(profileID, MonetaryType.INCOME);
    }
    @GetMapping("income/total")
    public MoneyChangeTotal getIncomeTotal() {
        return moneyChangeService.getMoneyChangeTotal(MonetaryType.INCOME);
    }
    @GetMapping("income/total/{profileID}")
    public MoneyChangeTotal getIncomeTotalByProfile(@PathVariable Long profileID) {
        return moneyChangeService.getMoneyChangeTotalByProfile(profileID, MonetaryType.INCOME);
    }
    @GetMapping("income/total/{profileID}/between/{start}/{end}")
    public MoneyChangeBetween getIncomeBetweenByProfile(@PathVariable Long profileID,
                                                   @PathVariable String start, @PathVariable String end) {
        LocalDateTime startDate = LocalDateTime.parse(start + time);
        LocalDateTime endDate = LocalDateTime.parse(end + time);
        return moneyChangeService.getMoneyChangeBetweenByProfile(profileID,startDate,endDate, MonetaryType.INCOME);
    }
    @GetMapping("income/total/between/{start}/{end}")
    public MoneyChangeBetween getIncomeBetween(@PathVariable String start, @PathVariable String end) {
        LocalDateTime startDate = LocalDateTime.parse(start + time);
        LocalDateTime endDate = LocalDateTime.parse(end + time);
        return moneyChangeService.getMoneyChangeBetween(startDate,endDate,MonetaryType.INCOME);
    }

    @PostMapping("{profileID}")
    public String newMoneyChange(@RequestBody MoneyChangeRequest request, @PathVariable Long profileID){
        return moneyChangeService.newMoneyChange(request, profileID);
    }
}

