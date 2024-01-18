package com.usv.casino.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usv.casino.entity.Utilizator;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilizatorDTO {
    private String nume;

    private String prenume;

    private String cnp;

    private String adresa;

    private String email;

    private String parola;

    private String alias;

    private String dataInregistrare;

    private Long balanta;
    private UUID idBonus;

    private String codulMeuReferal;

    private String idCodulMeuReferal;
}
