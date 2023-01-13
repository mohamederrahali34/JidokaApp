package com.nimbleways.jidokabot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "token")
    private String token;
    @OneToMany( mappedBy = "owner", fetch = FetchType.LAZY)
    List<Board> boards = new ArrayList<>();
}
