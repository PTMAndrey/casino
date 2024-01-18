package com.usv.casino.entity;

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
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBonus;

    private EnumTipBonus numeBonus;

    private UUID idBeneficiu;

    @Transient
    private Beneficiu beneficiu;

    @OneToMany(
            targetEntity = Utilizator.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="idBonus", referencedColumnName = "idBonus")
    private List<Utilizator> utilizatori= new ArrayList<>();


}
