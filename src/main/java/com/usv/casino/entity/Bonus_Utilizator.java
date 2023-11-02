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
public class Bonus_Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBonusUtilizator;
    private String activ;

    @OneToMany(
            targetEntity = Utilizator.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="idBonusUtilizatorU", referencedColumnName = "idBonusUtilizator")
    private List<Utilizator> utilizatori = new ArrayList<>();

    @OneToMany(
            targetEntity = Bonus.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="idBonusUtilizatorB", referencedColumnName = "idBonusUtilizator")
    private List<Bonus> bonusuri = new ArrayList<>();


}
