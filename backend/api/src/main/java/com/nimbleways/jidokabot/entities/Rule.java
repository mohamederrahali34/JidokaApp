package com.nimbleways.jidokabot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rules")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_rule")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "message")
    private String message;
    @Column(name = "channel")
    private String channel;
    @Column(name = "workspace")
    private String workspace;
    @Column(name = "type")
    private String type;
    @Column(name = "active", nullable = false)
    private boolean active = false;
    @ManyToOne
    @JoinColumn(name = "board")
    @JsonIgnoreProperties({"rules"})
    private Board board;
}
