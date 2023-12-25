package com.usv.casino.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBonus;

    private String numeBonus;

    private UUID idBeneficiu;

    private UUID idBonusUtilizatorB;

}
