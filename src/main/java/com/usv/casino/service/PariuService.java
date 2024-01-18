package com.usv.casino.service;

import com.usv.casino.dto.PariuDTO;
import com.usv.casino.entity.Joc;
import com.usv.casino.entity.Pariu;
import com.usv.casino.entity.Utilizator;
import com.usv.casino.enums.EnumTipPariu;
import com.usv.casino.exceptions.CrudOperationException;
import com.usv.casino.repository.JocRepository;
import com.usv.casino.repository.PariuRepository;
import com.usv.casino.repository.UtilizatorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PariuService {

    public static final String MESAJ_DE_EROARE = "Nu există pariu";
    public static final String MESAJ_DE_EROARE_JOC = "Nu există joc";
    public static final String MESAJ_DE_EROARE_UTILIZATOR = "Nu există UTILIZATOR";
    public static final String MESAJ_DE_EROARE_PARIU_PREA_MARE= "Suma este prea mare";


    private final PariuRepository pariuRepository;
    private final JocRepository jocRepository;
    private final UtilizatorRepository utilizatorRepository;

    public PariuService(PariuRepository pariuRepository, JocRepository jocRepository, UtilizatorRepository utilizatorRepository) {
        this.pariuRepository = pariuRepository;
        this.jocRepository = jocRepository;
        this.utilizatorRepository = utilizatorRepository;
    }

    public List<Pariu> getAllPariuri() {
        List<Pariu> pariuri = new ArrayList<>();
        pariuRepository.findAll().forEach(pariu -> {
            Pariu pariuCopie = Pariu.builder()
                    .idPariu(pariu.getIdPariu())
                    .dataPariu(pariu.getDataPariu())
                    .sumaPariata(pariu.getSumaPariata())
                    .rezultatPariu(pariu.getRezultatPariu())
                    .idUtilizator(pariu.getIdUtilizator())
                    .idJoc(pariu.getIdJoc())
                    .build();
            pariuri.add(pariuCopie);
        });
        return pariuri;
    }

    public Pariu getPariuDupaId(UUID id) {
        return pariuRepository.findById(id)
                .orElseThrow(() -> new CrudOperationException(MESAJ_DE_EROARE_JOC));
    }

    public Pariu adaugaPariu(PariuDTO pariuDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime currentDateTime = LocalDateTime.now();

        Joc jocExistent = jocRepository.findById(pariuDTO.getIdJoc())
                .orElseThrow(() -> new CrudOperationException(MESAJ_DE_EROARE));

        Utilizator utilizatorCareJoaca = utilizatorRepository.findById(pariuDTO.getIdUtilizator()).orElseThrow(() ->
                new RuntimeException(MESAJ_DE_EROARE_UTILIZATOR));

        if(utilizatorCareJoaca.getBalanta()<pariuDTO.getSumaPariata()){
            throw new RuntimeException(MESAJ_DE_EROARE_PARIU_PREA_MARE);
        }

        Pariu pariu = Pariu.builder()
                .dataPariu(String.valueOf(currentDateTime))
                .sumaPariata(pariuDTO.getSumaPariata())
                .rezultatPariu(pariuDTO.getRezultatPariu())
                .idUtilizator(utilizatorCareJoaca.getIdUtilizator())
                .idJoc(jocExistent.getIdJoc())
                .build();

        utilizatorCareJoaca.setBalanta(
                balantaActualizata(utilizatorCareJoaca.getBalanta(),pariu.getRezultatPariu(), pariu.getSumaPariata())
        );
        utilizatorRepository.save(utilizatorCareJoaca);

        return pariuRepository.save(pariu);
    }

    private Long balantaActualizata(Long balantaActuala, EnumTipPariu rezultatPariu, Long sumaPariata){
        if(rezultatPariu.equals(EnumTipPariu.WIN_NEGRU)){
            return balantaActuala+sumaPariata*2;
        }else if(rezultatPariu.equals(EnumTipPariu.WIN_ROSU)){
            return balantaActuala+sumaPariata*2;
        }else if(rezultatPariu.equals(EnumTipPariu.WIN_VERDE)){
            return balantaActuala+sumaPariata*14;
        }else if(rezultatPariu.equals(EnumTipPariu.LOOSE_NEGRU)){
            return balantaActuala-sumaPariata;
        }else if(rezultatPariu.equals(EnumTipPariu.LOOSE_ROSU)){
            return balantaActuala-sumaPariata;
        }else if(rezultatPariu.equals(EnumTipPariu.LOOSE_VERDE)){
            return balantaActuala-sumaPariata;
        }
        return balantaActuala;
    }
}
