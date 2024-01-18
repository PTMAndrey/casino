package com.usv.casino.controller;
import com.usv.casino.dto.UtilizatorDTO;
import com.usv.casino.entity.Utilizator;
import com.usv.casino.service.UtilizatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/utilizator")
public class UtilizatorController {

    private final UtilizatorService utilizatorService;

    public UtilizatorController(UtilizatorService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Utilizator>> getUtilizatori() {
        return ResponseEntity.ok(utilizatorService.getUtilizatori());
    }

    @GetMapping("/getByID")
    public ResponseEntity<Utilizator> getUtilizatorDupaId(@RequestParam UUID id) {
        return ResponseEntity.ok(utilizatorService.getUtilizatorDupaId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Utilizator> adaugaUtilizator(@RequestBody UtilizatorDTO utilizatorDTO) {
        return ResponseEntity.ok(utilizatorService.adaugaUtilizator(utilizatorDTO));
    }

    // Alte metode (actualizare, ștergere) pot fi adăugate aici, dacă este necesar
}
