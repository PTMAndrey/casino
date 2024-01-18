package com.usv.casino.controller;

import com.usv.casino.dto.PariuDTO;
import com.usv.casino.entity.Pariu;
import com.usv.casino.service.PariuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pariuri")
public class PariuController {

    private final PariuService pariuService;

    public PariuController(PariuService pariuService) {
        this.pariuService = pariuService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Pariu>> getAllPariuri() {
        return ResponseEntity.ok(pariuService.getAllPariuri());
    }

    @GetMapping("/getById")
    public ResponseEntity<Pariu> getPariuDupaId(@RequestParam UUID id) {
        return ResponseEntity.ok(pariuService.getPariuDupaId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Pariu> adaugaPariu(@RequestBody PariuDTO pariuDTO) {
        return ResponseEntity.ok(pariuService.adaugaPariu(pariuDTO));
    }
    
}
