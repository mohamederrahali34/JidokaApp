package com.nimbleways.jidokabot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("usTimeRule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsTimeRule extends Rule {

    @Column(name = "time_rule_column_name")
    private String timeRuleColumnName = "columnX";
    @Column(name = "duration")
    private double duration = 0;
    @Column(name = "unit")
    private String unit = "h";

}
