package com.usv.casino.entity;

import com.fasterxml.jackson.annotation.*;
import com.usv.casino.enums.EnumTipBonus;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idUtilizator"
)
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUtilizator;

    private String nume;

    private String prenume;

    @Column(unique=true)
    private String cnp;

    private String adresa;

    @Column(unique=true)
    private String email;

    private String parola;

    @Column(unique=true)
    private String alias;

    @JsonFormat(pattern = "$data.configuration.format", shape = JsonFormat.Shape.STRING)
    private String dataInregistrare;

    private Long balanta;

    private UUID idBonus;
    private EnumTipBonus numeBonus;

    @Column(unique = true)
    private String codulMeuReferal;
    private String idCodulMeuReferal;
    private Long numarReferiti;


    @ElementCollection
    private List<String> referiti = new ArrayList<>();

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
