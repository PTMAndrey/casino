package com.usv.casino.controller;

import com.usv.casino.dto.JocDTO;
import com.usv.casino.entity.Joc;
import com.usv.casino.service.JocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jocuri")
public class JocController {

    private final JocService jocService;

    public JocController(JocService jocService) {
        this.jocService = jocService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Joc>> getAllJocuri() {
        return ResponseEntity.ok(jocService.getAllJocuri());
    }

    @GetMapping("/getById")
    public ResponseEntity<Joc> getJocDupaId(@RequestParam UUID id) {
        return ResponseEntity.ok(jocService.getJocDupaId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Joc> adaugaJoc(@RequestBody JocDTO jocDTO) {
        return ResponseEntity.ok(jocService.adaugaJoc(jocDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Joc> actualizeazaJoc(@RequestParam UUID id, @RequestBody JocDTO jocDTO) {
        return ResponseEntity.ok(jocService.actualizeazaJoc(id, jocDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> stergeJoc(@RequestParam UUID id) {
        jocService.stergeJoc(id);
        return ResponseEntity.ok().build();
    }
}
