package com.nimbleways.jidokabot.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "boards")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Board implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @OneToMany(targetEntity = Rule.class, mappedBy = "board", fetch = FetchType.LAZY)
    private List<Rule> rules = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "owner")
    @JsonIgnoreProperties({"boards"})
    private User owner;


    public Board(final String id,final User owner) {
        this.id = id;
        this.owner = owner;
    }

    public Board(final String id) {
        this.id = id;
    }
}
