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
public class Joc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idJoc;

    private Integer numeJoc;
}
