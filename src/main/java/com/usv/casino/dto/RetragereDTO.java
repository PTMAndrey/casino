package com.usv.casino.dto;

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
public class RetragereDTO {
    private Integer sumaRetrasa;
    private UUID idUtilizator;
}
