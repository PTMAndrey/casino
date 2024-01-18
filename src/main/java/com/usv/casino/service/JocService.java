package com.usv.casino.service;

import com.usv.casino.dto.JocDTO;
import com.usv.casino.entity.Joc;
import com.usv.casino.exceptions.CrudOperationException;
import com.usv.casino.repository.JocRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JocService {

    public static final String MESAJ_DE_EROARE = "Nu există joc";

    private final JocRepository jocRepository;

    public JocService(JocRepository jocRepository) {
        this.jocRepository = jocRepository;
    }

    public List<Joc> getAllJocuri() {
        List<Joc> jocuri = new ArrayList<>();
        jocRepository.findAll().forEach(joc -> {
            Joc jocCopie = Joc.builder()
                    .idJoc(joc.getIdJoc())
                    .numeJoc(joc.getNumeJoc())
                    .pariuriPerJoc(joc.getPariuriPerJoc())
                    .build();
            jocuri.add(jocCopie);
        });
        return jocuri;
    }

    public Joc getJocDupaId(UUID id) {
        return jocRepository.findById(id)
                .orElseThrow(() -> new CrudOperationException(MESAJ_DE_EROARE));
    }

    public Joc adaugaJoc(JocDTO jocDTO) {
        Joc joc = Joc.builder()
                .numeJoc(jocDTO.getNumeJoc())
                .build();
        return jocRepository.save(joc);
    }

    public Joc actualizeazaJoc(UUID id, JocDTO jocDTO) {
        Joc jocExistent = jocRepository.findById(id)
                .orElseThrow(() -> new CrudOperationException(MESAJ_DE_EROARE));

        jocExistent.setNumeJoc(jocDTO.getNumeJoc());

        return jocRepository.save(jocExistent);
    }

    public void stergeJoc(UUID id) {
        Joc jocExistent = jocRepository.findById(id)
                .orElseThrow(() -> new CrudOperationException(MESAJ_DE_EROARE));

        jocRepository.delete(jocExistent);
    }
}
