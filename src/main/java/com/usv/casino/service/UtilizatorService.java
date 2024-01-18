package com.usv.casino.service;

import com.usv.casino.dto.UtilizatorDTO;
import com.usv.casino.entity.Beneficiu;
import com.usv.casino.entity.Bonus;
import com.usv.casino.entity.Utilizator;
import com.usv.casino.enums.EnumTipBonus;
import com.usv.casino.exceptions.CrudOperationException;
import com.usv.casino.repository.BeneficiuRepository;
import com.usv.casino.repository.BonusRepository;
import com.usv.casino.repository.UtilizatorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UtilizatorService {

    public static final String MESAJ_DE_EROARE = "Nu exista utilizator";
    public static final String MESAJ_DE_EROARE_BONUS = "Nu exista bonus";
    public static final String MESAJ_DE_EROARE_BENEFICIU = "Nu exista beneficiu";

    public final UtilizatorRepository utilizatorRepository;
    public final BonusRepository bonusRepository;
    public final BeneficiuRepository beneficiuRepository;

    public UtilizatorService(UtilizatorRepository utilizatorRepository, BonusRepository bonusRepository, BeneficiuRepository beneficiuRepository) {
        this.utilizatorRepository = utilizatorRepository;
        this.bonusRepository = bonusRepository;
        this.beneficiuRepository = beneficiuRepository;
    }

    public List<Utilizator> getUtilizatori() {
        Iterable<Utilizator> iterableUtilizatori = utilizatorRepository.findAll();
        List<Utilizator> listaUtilizatori = new ArrayList<>();

        iterableUtilizatori.forEach(utilizator -> listaUtilizatori.add(Utilizator.builder()
                .idUtilizator(utilizator.getIdUtilizator())
                .nume(utilizator.getNume())
                .prenume(utilizator.getPrenume())
                .cnp(utilizator.getCnp())
                .adresa(utilizator.getAdresa())
                .email(utilizator.getEmail())
                .parola(utilizator.getParola())
                .alias(utilizator.getAlias())
                .dataInregistrare(utilizator.getDataInregistrare())
                .balanta(utilizator.getBalanta())
                .idBonus(utilizator.getIdBonus())
                .numeBonus(bonusRepository.findById(utilizator.getIdBonus()).orElseThrow(() ->
                                new CrudOperationException(MESAJ_DE_EROARE)).getNumeBonus())
                .codulMeuReferal(utilizator.getCodulMeuReferal())
                .idCodulMeuReferal(utilizator.getIdCodulMeuReferal())
                .numarReferiti(utilizator.getNumarReferiti())
                .referiti(utilizator.getReferiti())
                .pariuri(utilizator.getPariuri())
                .depuneri(utilizator.getDepuneri())
                .retrageri(utilizator.getRetrageri())
                .build()));
        return listaUtilizatori;
    }

    public Utilizator getUtilizatorDupaId(UUID id) {
        return utilizatorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Utilizatorul cu acest ID nu a fost găsit."));
    }

    public Utilizator adaugaUtilizator(UtilizatorDTO utilizatorDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Obținerea ID-ului bonusului pentru tipul DEFAULT
        UUID idBonusDefault = findIdByTipBonus(EnumTipBonus.DEFAULT);
        if (idBonusDefault==null)
        {
            throw  new RuntimeException("Bonusul DEFAULT nu este existent.");
        }


        // Crearea noului utilizator
        Utilizator utilizator = Utilizator.builder()
                .nume(utilizatorDTO.getNume())
                .prenume(utilizatorDTO.getPrenume())
                .cnp(utilizatorDTO.getCnp())
                .adresa(utilizatorDTO.getAdresa())
                .email(utilizatorDTO.getEmail())
                .parola(utilizatorDTO.getParola())
                .alias(utilizatorDTO.getAlias())
                .dataInregistrare(currentDateTime.format(formatter))
                .balanta(utilizatorDTO.getBalanta())
                .idBonus(idBonusDefault)
                .codulMeuReferal(utilizatorDTO.getCodulMeuReferal())
                .idCodulMeuReferal(utilizatorDTO.getIdCodulMeuReferal())
                .build();

        if(utilizatorDTO.getIdCodulMeuReferal()!=null)
        // Verificarea și actualizarea utilizatorului referent
        {
            Utilizator referent = utilizatorRepository.findByCodulMeuReferal(utilizatorDTO.getIdCodulMeuReferal())
                    .orElse(null); // sau gestionați cum considerați necesar

            if (referent != null) {

                // Actualizare număr de referiți
                long numarReferiti = referent.getNumarReferiti() == null ? 0 : referent.getNumarReferiti();
                referent.setNumarReferiti(numarReferiti + 1);

                // Determinarea și actualizarea bonusului pentru utilizatorul referent
                EnumTipBonus tipBonus = determinaTipBonus(referent.getNumarReferiti());
                UUID idBonusNou = findIdByTipBonus(tipBonus);
                referent.setIdBonus(idBonusNou);

                utilizatorRepository.save(referent);

                //ACTUALIZARE BALANTA PERSOANA CE FOLOSESTE REFERALUL PERSOANEI REFERENT
                Bonus bonusReferent = bonusRepository.findById(referent.getIdBonus()).orElseThrow(() ->
                        new CrudOperationException(MESAJ_DE_EROARE_BONUS));
                Beneficiu beneficiuReferent = beneficiuRepository.findById(bonusReferent.getIdBeneficiu()).orElseThrow(() ->
                        new CrudOperationException(MESAJ_DE_EROARE_BENEFICIU));
                utilizator.setBalanta(utilizator.getBalanta()+beneficiuReferent.getBani());
                referent.setBalanta(referent.getBalanta()+beneficiuReferent.getBani());

                //adauga noul utilizator la referent
                if (referent.getReferiti() == null) {
                    referent.setReferiti(new ArrayList<>());
                }

                if(referent.getReferiti()!=null)
                    referent.getReferiti().add(utilizator.getNume()+" "+utilizator.getPrenume());
                else
                    referent.getReferiti().add(","+utilizator.getNume()+" "+utilizator.getPrenume());



                utilizatorRepository.save(referent); // Actualizarea utilizatorului referent
            }
        }


        // Salvarea noului utilizator
        utilizatorRepository.save(utilizator);
        return utilizator;
    }

    public UUID findIdByTipBonus(EnumTipBonus tipBonus) {
        return bonusRepository.findByNumeBonus(tipBonus)
                .map(Bonus::getIdBonus) // Extrage ID-ul bonusului
                .orElse(null); // sau gestionați cazul în care bonusul nu este găsit
    }

    private EnumTipBonus determinaTipBonus(long numarReferiti) {
        if (numarReferiti > 25) {
            return EnumTipBonus.REFERAL26;
        } else if (numarReferiti > 10) {
            return EnumTipBonus.REFERAL25;
        } else if (numarReferiti > 5) {
            return EnumTipBonus.REFERAL10;
        } else if (numarReferiti > 3) {
            return EnumTipBonus.REFERAL5;
        } else if (numarReferiti >= 1) {
            return EnumTipBonus.REFERAL3;
        }
        return EnumTipBonus.DEFAULT;
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
