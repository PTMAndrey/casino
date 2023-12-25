package com.usv.casino.controller;

import com.usv.casino.entity.Beneficiu;
import com.usv.casino.entity.Bonus;
import com.usv.casino.service.BonusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bonus")
public class BonusController {

    private final BonusService bonusService;

    public BonusController(BonusService bonusService) {
        this.bonusService = bonusService;
    }
    @GetMapping
    public ResponseEntity<List<Bonus>> getBonus(){
        return ResponseEntity.ok(bonusService.getBonus());
    }
    @GetMapping("/getByID")
    public ResponseEntity<Bonus> getBonusDupaId(@RequestParam UUID id) {
        return ResponseEntity.ok(bonusService.getBonusDupaId(id));
    }
    @PostMapping
    public ResponseEntity<Bonus> adaugaBonus(@RequestBody Bonus bonus, @RequestParam UUID idBeneficiu) {
        return ResponseEntity.ok(bonusService.adaugaBonus(bonus,idBeneficiu));
    }
}
