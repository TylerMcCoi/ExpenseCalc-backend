package com.example.demo.expenseNoSQL.storeVisit.receipt;

import com.example.demo.expenseNoSQL.storeVisit.StoreVisit;

import java.time.LocalDate;
import java.util.List;

public class Receipt extends StoreVisit {
    private LocalDate receiptDate;
    private Double total;
    private List<ReceiptItem> receiptItems;
}
