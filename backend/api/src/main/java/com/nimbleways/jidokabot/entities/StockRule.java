package com.nimbleways.jidokabot.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("stockRule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockRule extends Rule {
    @Column(name = "stock_rule_column_name")
    private String stockRuleColumnName = "columnX";
    @Column(name = "nb_cards")
    private double nbCards = 0;
    @Column(name = "owner")
    private String owner = "anyone";
}
