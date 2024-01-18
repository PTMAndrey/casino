package com.usv.casino.controller;

import com.usv.casino.dto.RetragereDTO;
import com.usv.casino.entity.Retragere;
import com.usv.casino.service.RetragereService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/retrageri")
public class RetragereController {

    private final RetragereService retragereService;

    public RetragereController(RetragereService retragereService) {
        this.retragereService = retragereService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Retragere>> getAllRetrageri() {
        return ResponseEntity.ok(retragereService.getAllRetrageri());
    }

    @GetMapping("/getById")
    public ResponseEntity<Retragere> getRetragereDupaId(@RequestParam UUID id) {
        return ResponseEntity.ok(retragereService.getRetragereDupaId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Retragere> adaugaRetragere(@RequestBody RetragereDTO retragereDTO) {
        return ResponseEntity.ok(retragereService.adaugaRetragere(retragereDTO));
    }

}
