package com.usv.casino.service;

import com.usv.casino.dto.DepunereDTO;
import com.usv.casino.entity.Beneficiu;
import com.usv.casino.entity.Bonus;
import com.usv.casino.entity.Depunere;
import com.usv.casino.entity.Utilizator;
import com.usv.casino.exceptions.CrudOperationException;
import com.usv.casino.repository.BeneficiuRepository;
import com.usv.casino.repository.BonusRepository;
import com.usv.casino.repository.DepunereRepository;
import com.usv.casino.repository.UtilizatorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DepunereService {

    public static final String MESAJ_DE_EROARE = "Nu există depunere";
    public static final String MESAJ_DE_EROARE_UTILIZATOR = "Nu există utilizator";
    public static final String MESAJ_DE_EROARE_BONUS = "Nu exista bonus";
    public static final String MESAJ_DE_EROARE_BENEFICIU = "Nu exista beneficiu";

    private final DepunereRepository depunereRepository;
    private final UtilizatorRepository utilizatorRepository;
    public final BonusRepository bonusRepository;
    public final BeneficiuRepository beneficiuRepository;

    public DepunereService(DepunereRepository depunereRepository, UtilizatorRepository utilizatorRepository, BonusRepository bonusRepository, BeneficiuRepository beneficiuRepository) {
        this.depunereRepository = depunereRepository;
        this.utilizatorRepository = utilizatorRepository;
        this.bonusRepository = bonusRepository;
        this.beneficiuRepository = beneficiuRepository;
    }

    public List<Depunere> getAllDepuneri() {
        List<Depunere> depuneri = new ArrayList<>();
        depunereRepository.findAll().forEach(depunere -> {
            Depunere depunereCopie = Depunere.builder()
                    .idDepunere(depunere.getIdDepunere())
                    .sumaDepusa(depunere.getSumaDepusa())
                    .dataDepunere(depunere.getDataDepunere())
                    .idUtilizator(depunere.getIdUtilizator())
                    .build();
            depuneri.add(depunereCopie);
        });
        return depuneri;
    }

    public Depunere getDepunereDupaId(UUID id) {
        return depunereRepository.findById(id)
                .orElseThrow(() -> new CrudOperationException(MESAJ_DE_EROARE));
    }

    public Depunere adaugaDepunere(DepunereDTO depunereDTO) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime currentDateTime = LocalDateTime.now();

    Utilizator utilizatorCareDepune = utilizatorRepository.findById(depunereDTO.getIdUtilizator()).orElseThrow(() ->
            new RuntimeException(MESAJ_DE_EROARE_UTILIZATOR));

    Utilizator utilizatorParinte = utilizatorRepository.findByCodulMeuReferal(utilizatorCareDepune.getIdCodulMeuReferal())
            .orElse(null);

    //ACTUALIZARE BALANTA PERSOANA CE FOLOSESTE REFERALUL PERSOANEI REFERENT
        Bonus bonus = bonusRepository.findById(utilizatorParinte.getIdBonus()).orElseThrow(() ->
                new CrudOperationException(MESAJ_DE_EROARE_BONUS));
        Beneficiu beneficiu = beneficiuRepository.findById(bonus.getIdBeneficiu()).orElseThrow(() ->
                new CrudOperationException(MESAJ_DE_EROARE_BENEFICIU));

    Long procent = (beneficiu.getProcentDepunere()*depunereDTO.getSumaDepusa())/100;

        Depunere depunere = Depunere.builder()
                .sumaDepusa(depunereDTO.getSumaDepusa())
                .dataDepunere(currentDateTime.format(formatter))
                .idUtilizator(depunereDTO.getIdUtilizator())
                .build();

        utilizatorCareDepune.setBalanta(utilizatorCareDepune.getBalanta()+procent+depunereDTO.getSumaDepusa());
        utilizatorRepository.save(utilizatorCareDepune);

        utilizatorParinte.setBalanta(utilizatorParinte.getBalanta()+procent);
        utilizatorRepository.save(utilizatorParinte);

        return depunereRepository.save(depunere);
    }
}
