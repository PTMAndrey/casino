package com.usv.casino.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUtilizator;

    private String nume;

    private String prenume;

    @Column(unique=true)
    private String CNP;

    private String adresa;

    @Column(unique=true)
    private String email;

    @Column(unique=true)
    private String alias;

    @JsonFormat(pattern = "$data.configuration.format", shape = JsonFormat.Shape.STRING)
    private String dataInregistrare;

    private Long balanta;

    private String invitat;

    @Column(unique=true)
    private String codReferal;
    private UUID idBonusUtilizatorU;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCard", referencedColumnName = "idUtilizator")
    private Card card;

    @OneToMany(
            targetEntity = Pariu.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="idUtilizator", referencedColumnName = "idUtilizator")
    private List<Pariu> pariuri = new ArrayList<>();

    @OneToMany(
            targetEntity = Depunere.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="idUtilizator", referencedColumnName = "idUtilizator")
    private List<Depunere> depuneri = new ArrayList<>();

    @OneToMany(
            targetEntity = Retragere.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="idUtilizator", referencedColumnName = "idUtilizator")
    private List<Retragere> retrageri = new ArrayList<>();

}
