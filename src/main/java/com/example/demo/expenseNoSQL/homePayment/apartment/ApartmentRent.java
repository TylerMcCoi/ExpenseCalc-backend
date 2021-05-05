package com.example.demo.expenseNoSQL.homePayment.apartment;

import com.example.demo.expenseNoSQL.homePayment.HomePayment;

import java.time.LocalDate;

public class ApartmentRent extends HomePayment {
    private Integer unitNumber;
    private LocalDate leaseStart;
    private LocalDate leaseEnd;
    private Integer leaseMonths;
    private Integer leaseMonthsLeft;
}
