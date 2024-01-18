package com.usv.casino.service;

import com.usv.casino.dto.RetragereDTO;
import com.usv.casino.entity.Retragere;
import com.usv.casino.entity.Utilizator;
import com.usv.casino.exceptions.CrudOperationException;
import com.usv.casino.repository.RetragereRepository;
import com.usv.casino.repository.UtilizatorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RetragereService {

    public static final String MESAJ_DE_EROARE = "Nu există retragere";
    public static final String MESAJ_DE_EROARE_UTILIZATOR = "Nu există utilizator";
    public static final String MESAJ_DE_EROARE_RETRAGERE = "Suma este prea mare";

    private final RetragereRepository retragereRepository;
    private final UtilizatorRepository utilizatorRepository;

    public RetragereService(RetragereRepository retragereRepository, UtilizatorRepository utilizatorRepository) {
        this.retragereRepository = retragereRepository;
        this.utilizatorRepository = utilizatorRepository;
    }

    public List<Retragere> getAllRetrageri() {
        List<Retragere> retrageri = new ArrayList<>();
        retragereRepository.findAll().forEach(retragere -> {
            Retragere retragereCopie = Retragere.builder()
                    .idRetragere(retragere.getIdRetragere())
                    .sumaRetrasa(retragere.getSumaRetrasa())
                    .dataRetragere(retragere.getDataRetragere())
                    .idUtilizator(retragere.getIdUtilizator())
                    .build();
            retrageri.add(retragereCopie);
        });
        return retrageri;
    }

    public Retragere getRetragereDupaId(UUID id) {
        return retragereRepository.findById(id)
                .orElseThrow(() -> new CrudOperationException(MESAJ_DE_EROARE));
    }

    public Retragere adaugaRetragere(RetragereDTO retragereDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime currentDateTime = LocalDateTime.now();

        Utilizator utilizatorCareJoaca = utilizatorRepository.findById(retragereDTO.getIdUtilizator()).orElseThrow(() ->
                new RuntimeException(MESAJ_DE_EROARE_UTILIZATOR));

        if(utilizatorCareJoaca.getBalanta()<retragereDTO.getSumaRetrasa()){
            throw new RuntimeException(MESAJ_DE_EROARE_RETRAGERE);
        }

        Retragere retragere = Retragere.builder()
                .sumaRetrasa(retragereDTO.getSumaRetrasa())
                .idUtilizator(retragereDTO.getIdUtilizator())
                .dataRetragere(currentDateTime.format(formatter))
                .build();

        utilizatorCareJoaca.setBalanta(utilizatorCareJoaca.getBalanta()-retragereDTO.getSumaRetrasa());

        return retragereRepository.save(retragere);
    }
}
