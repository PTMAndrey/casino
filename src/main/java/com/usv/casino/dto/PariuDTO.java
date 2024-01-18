package com.usv.casino.dto;

import com.usv.casino.enums.EnumTipPariu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PariuDTO {
    private String dataPariu;

    private Long sumaPariata;

    private EnumTipPariu rezultatPariu;

    private UUID idUtilizator;

    private UUID idJoc;

}
