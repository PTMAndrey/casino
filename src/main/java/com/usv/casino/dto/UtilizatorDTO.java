package com.usv.casino.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilizatorDTO {
    private String nume;

    private String prenume;

    private String CNP;

    private String adresa;

    private String email;

    private String parola;

    private String alias;

    private String dataInregistrare;

    private Long balanta;

    private String invitat;

    private String codReferal;
}
