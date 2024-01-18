package com.usv.casino.controller;

import com.usv.casino.dto.DepunereDTO;
import com.usv.casino.entity.Depunere;
import com.usv.casino.service.DepunereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/depuneri")
public class DepunereController {

    private final DepunereService depunereService;

    public DepunereController(DepunereService depunereService) {
        this.depunereService = depunereService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Depunere>> getAllDepuneri() {
        return ResponseEntity.ok(depunereService.getAllDepuneri());
    }

    @GetMapping("/getById")
    public ResponseEntity<Depunere> getDepunereDupaId(@RequestParam UUID id) {
        return ResponseEntity.ok(depunereService.getDepunereDupaId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Depunere> adaugaDepunere(@RequestBody DepunereDTO depunereDTO) {
        return ResponseEntity.ok(depunereService.adaugaDepunere(depunereDTO));
    }

}
