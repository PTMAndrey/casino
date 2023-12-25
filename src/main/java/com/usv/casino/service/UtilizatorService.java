package com.usv.casino.service;

import com.usv.casino.dto.UtilizatorDTO;
import com.usv.casino.entity.Utilizator;
import com.usv.casino.exceptions.CrudOperationException;
import com.usv.casino.repository.UtilizatorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UtilizatorService {

    public static final String MESAJ_DE_EROARE = "Nu exista utilizator";

    public final UtilizatorRepository utilizatorRepository;

    public UtilizatorService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    public List<Utilizator> getUtilizatori() {
        Iterable<Utilizator> iterableUtilizator = utilizatorRepository.findAll();
        List<Utilizator> utilizatori = new ArrayList<>();
        iterableUtilizator.forEach(utilizator ->
                utilizatori.add(Utilizator.builder()
                        .idUtilizator(utilizator.getIdUtilizator())
                        .nume(utilizator.getNume())
                        .prenume(utilizator.getPrenume())
                        .CNP(utilizator.getCNP())
                        .adresa(utilizator.getAdresa())
                        .email(utilizator.getEmail())
                        .parola(utilizator.getParola())
                        .alias(utilizator.getAlias())
                        .dataInregistrare(utilizator.getDataInregistrare())
                        .balanta(utilizator.getBalanta())
                        .invitat(utilizator.getInvitat())
                        .codReferal(utilizator.getCodReferal())
                        .idBonusUtilizatorU(utilizator.getIdBonusUtilizatorU())
                        .pariuri(utilizator.getPariuri())
                        .retrageri(utilizator.getRetrageri())
                        .depuneri(utilizator.getDepuneri())
                        .build()));
        return utilizatori;
    }

    public Utilizator getUtilizatorDupaId(UUID id) {
        return utilizatorRepository.findById(id).orElseThrow(() -> {
            throw new CrudOperationException(MESAJ_DE_EROARE);
        });
    }

    public Utilizator adaugaUtilizator(UtilizatorDTO utilizator) {

        Utilizator utilizator1 = Utilizator.builder()
                .nume(utilizator.getNume())
                .prenume(utilizator.getPrenume())
                .CNP(utilizator.getCNP())
                .adresa(utilizator.getAdresa())
                .email(utilizator.getEmail())
                .parola(utilizator.getParola())
                .alias(utilizator.getAlias())
                .dataInregistrare(utilizator.getDataInregistrare())
                .balanta(utilizator.getBalanta())
                .invitat(utilizator.getInvitat())
                .codReferal(utilizator.getCodReferal())
                .build();

        utilizatorRepository.save(utilizator1);
        return utilizator1;
    }

/*
    public List<Utilizator> getUtilizatori(){
        Iterable<Utilizator> iterableUtilizator =utilizatorRepository.findAll();
        List<Utilizator> utilizatori = new ArrayList<>();

        iterableUtilizator.forEach(utilizator ->
                utilizatori.add(Utilizator.builder()
                        .idUtilizator(utilizator.getIdUtilizator())
                        .nume(utilizator.getNume())
                        .prenume(utilizator.getPrenume())
                        .email(utilizator.getEmail())
                        .parola(utilizator.getParola())
                        .idAbonament(utilizator.getIdAbonament())
                        .nrMaxCategorii(utilizator.getNrMaxCategorii())
                        .nrMaxCarti(utilizator.getNrMaxCarti())
                        .nrCartiAdaugate(utilizator.getNrCartiAdaugate())
                        .nrCartiAdaugate(utilizator.getNrCartiAdaugate())
                        .build()));
        return utilizatori;
    }
    public Utilizator getUtilizatorDupaId(UUID id) {
        return utilizatorRepository.findById(id).orElseThrow(() -> {
            throw new CrudOperationException(MESAJ_DE_EROARE);
        });
    }

    public Utilizator adaugaUtilizator(Utilizator utilizator) {

        Utilizator utilizator1 = Utilizator.builder()
                .idUtilizator(utilizator.getIdUtilizator())
                .nume(utilizator.getNume())
                .prenume(utilizator.getPrenume())
                .email(utilizator.getEmail())
                .parola(utilizator.getParola())
                .idAbonament(utilizator.getIdAbonament())
                .nrMaxCategorii(utilizator.getNrMaxCategorii())
                .nrMaxCarti(utilizator.getNrMaxCarti())
                .nrCartiAdaugate(utilizator.getNrCartiAdaugate())
                .nrCartiAdaugate(utilizator.getNrCartiAdaugate())
                .build();
        utilizatorRepository.save(utilizator1);
        return utilizator1;
    }*/

}
