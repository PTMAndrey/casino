package com.usv.casino.service;

import com.usv.casino.dto.BonusDTO;
import com.usv.casino.entity.Bonus;
import com.usv.casino.exceptions.CrudOperationException;
import com.usv.casino.repository.BeneficiuRepository;
import com.usv.casino.repository.BonusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BonusService {

    public static final String MESAJ_DE_EROARE = "Nu exista bonus";
    public static final String MESAJ_DE_EROARE_BENEFICIU = "Nu exista beneficiu";

    public final BonusRepository bonusRepository;
    public final BeneficiuRepository beneficiuRepository;

    public BonusService(BonusRepository bonusRepository, BeneficiuRepository beneficiuRepository) {
        this.bonusRepository = bonusRepository;
        this.beneficiuRepository = beneficiuRepository;
    }
    public List<Bonus> getBonus(){
        Iterable<Bonus> iterableBonus = bonusRepository.findAll();
        List<Bonus> bonuse= new ArrayList<>();



        iterableBonus.forEach(bonus ->
                bonuse.add(Bonus.builder()
                        .idBonus(bonus.getIdBonus())
                        .numeBonus(bonus.getNumeBonus())
                        .idBeneficiu(bonus.getIdBeneficiu())
                        .beneficiu(
                                beneficiuRepository.findById(bonus.getIdBeneficiu()).orElseThrow(() ->
                                        new CrudOperationException(MESAJ_DE_EROARE_BENEFICIU))
                        )
                        .build()));
        return bonuse;
    }

    public Bonus getBonusDupaId(UUID id){
        Bonus bonus = bonusRepository.findById(id).orElseThrow(() -> {
            throw new CrudOperationException(MESAJ_DE_EROARE);
        });
        bonus.setBeneficiu(beneficiuRepository.findById(bonus.getIdBeneficiu()).orElseThrow(() ->
                new CrudOperationException(MESAJ_DE_EROARE_BENEFICIU)));

        return bonus;

    }

    public Bonus adaugaBonus(BonusDTO bonus){
        Bonus bonus1=Bonus.builder()
                .numeBonus(bonus.getNumeBonus())
                .idBeneficiu(bonus.getIdBeneficiu())
                .build();

        bonusRepository.save(bonus1);
        return bonus1;
    }

    public Bonus actualizareBonus(UUID id, UUID idBeneficiu, BonusDTO bonus){
        Bonus bonusExistent = bonusRepository.findById(id).orElseThrow(() ->
                new CrudOperationException(MESAJ_DE_EROARE));

        bonusExistent.setNumeBonus(bonus.getNumeBonus());
        bonusExistent.setIdBeneficiu(idBeneficiu);
        return bonusRepository.save(bonusExistent);
    }

    public void deleteBonus(UUID id){
        Bonus bonus = bonusRepository.findById(id).orElseThrow(() -> {
            throw new CrudOperationException(MESAJ_DE_EROARE);
        });
        bonusRepository.delete(bonus);
    }

}
