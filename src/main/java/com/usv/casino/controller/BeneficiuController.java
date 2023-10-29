package com.usv.casino.controller;

import com.usv.casino.entity.Beneficiu;
import com.usv.casino.service.BeneficiuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/beneficiu")
public class BeneficiuController {
    private final BeneficiuService beneficiuService;

    public BeneficiuController(BeneficiuService beneficiuService) {
        this.beneficiuService = beneficiuService;
    }
    @GetMapping
    public ResponseEntity<List<Beneficiu>> getUtilizatori(){
        return ResponseEntity.ok(beneficiuService.getBeneficiu());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Beneficiu> getUtilizatorDupaId(@PathVariable UUID id) {
        return ResponseEntity.ok(beneficiuService.getBeneficiuDupaId(id));
    }

    @PostMapping
    public ResponseEntity<Beneficiu> adaugaUtilizator(@RequestBody Beneficiu beneficiu) {
        return ResponseEntity.ok(beneficiuService.adaugaBeneficiu(beneficiu));
    }

}
