package com.usv.casino.service;

import com.usv.casino.entity.Beneficiu;
import com.usv.casino.entity.Bonus;
import com.usv.casino.exceptions.CrudOperationException;
import com.usv.casino.repository.BonusRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class BonusService {

    public static final String MESAJ_DE_EROARE = "Nu exista bonus";

    public final BonusRepository bonusRepository;

    public BonusService(BonusRepository bonusRepository) {
        this.bonusRepository = bonusRepository;
    }
    public List<Bonus> getBonus(){
        Iterable<Bonus> iterableBonus = bonusRepository.findAll();
        List<Bonus> bonuse= new ArrayList<>();

        iterableBonus.forEach(bonus ->
                bonuse.add(Bonus.builder()
                        .idBonus(bonus.getIdBonus())
                        .numeBonus(bonus.getNumeBonus())
                        .idBeneficiu(bonus.getIdBeneficiu())
                        .build()));
        return bonuse;
    }

    public Bonus getBonusDupaId(UUID id){
        return bonusRepository.findById(id).orElseThrow(() -> {
            throw new CrudOperationException(MESAJ_DE_EROARE);
        });

    }

    public Bonus adaugaBonus(Bonus bonus, UUID idBeneficiu){
        Bonus bonus1=Bonus.builder()
                .numeBonus(bonus.getNumeBonus())
                .idBeneficiu(idBeneficiu)
                .build();

        bonusRepository.save(bonus1);
        return bonus1;
    }


}
