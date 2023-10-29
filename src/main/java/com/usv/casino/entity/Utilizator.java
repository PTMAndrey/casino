package com.usv.casino.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

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


}
