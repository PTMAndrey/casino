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
public class Depunere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idDepunere;

    // idUtilizator

    private Integer sumaDepusa;
}
