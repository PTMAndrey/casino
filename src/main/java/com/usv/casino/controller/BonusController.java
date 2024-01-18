package com.usv.casino.controller;

import com.usv.casino.dto.BeneficiuDTO;
import com.usv.casino.dto.BonusDTO;
import com.usv.casino.entity.Beneficiu;
import com.usv.casino.entity.Bonus;
import com.usv.casino.service.BonusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/bonus")
public class BonusController {

    private final BonusService bonusService;

    public BonusController(BonusService bonusService) {
        this.bonusService = bonusService;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Bonus>> getBonus(){
        return ResponseEntity.ok(bonusService.getBonus());
    }
    @GetMapping("/getByID")
    public ResponseEntity<Bonus> getBonusDupaId(@RequestParam UUID id) {
        return ResponseEntity.ok(bonusService.getBonusDupaId(id));
    }
    @PostMapping("/add")
    public ResponseEntity<Bonus> adaugaBonus(@RequestBody BonusDTO bonus) {
        return ResponseEntity.ok(bonusService.adaugaBonus(bonus));
    }

    @PutMapping("/update")
    public ResponseEntity<Bonus> actualizareBonus(@RequestParam UUID id, @RequestParam Optional<UUID> idBeneficiu, @RequestBody BonusDTO beneficiu)
    {
        if(idBeneficiu.isPresent())
            return ResponseEntity.ok(bonusService.actualizareBonus(id, idBeneficiu.get(), beneficiu));
        else
            return ResponseEntity.ok(bonusService.actualizareBonus(id, beneficiu.getIdBeneficiu(), beneficiu));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBonus(@RequestParam UUID id){
        bonusService.deleteBonus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
