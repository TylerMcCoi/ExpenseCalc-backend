package com.example.demo.moneychange;

import com.example.demo.moneychange.category.Category;
import com.example.demo.moneychange.monetarytype.MonetaryType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MoneyChangeRequest {
    private Double amount;
    private String moneyChangeName;
    private Category category;
    private MonetaryType type;
    private LocalDateTime localDateTime;
}
