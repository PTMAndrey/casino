package com.usv.casino.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usv.casino.enums.EnumTipPariu;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Pariu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPariu;

    @JsonFormat(pattern = "$data.configuration.format", shape = JsonFormat.Shape.STRING)
    private String dataPariu;

    private Long sumaPariata;

    private EnumTipPariu rezultatPariu;

    private UUID idUtilizator;

    private UUID idJoc;
}
