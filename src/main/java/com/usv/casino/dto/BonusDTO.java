package com.usv.casino.dto;

import com.usv.casino.enums.EnumTipBonus;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BonusDTO {
    private EnumTipBonus numeBonus;

    private UUID idBeneficiu;

    private UUID idBonusUtilizatorB;

}
