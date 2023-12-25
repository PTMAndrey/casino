package com.usv.casino.controller;

import com.usv.casino.entity.Beneficiu;
import com.usv.casino.service.BeneficiuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    @GetMapping("/getAll")
    public ResponseEntity<List<Beneficiu>> getBeneficii(){
        return ResponseEntity.ok(beneficiuService.getBeneficiu());
    }
    @GetMapping("/getByID")
    public ResponseEntity<Beneficiu> getBeneficiuDupaId(@RequestParam UUID id) {
        return ResponseEntity.ok(beneficiuService.getBeneficiuDupaId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Beneficiu> adaugaBeneficiu(@RequestBody Beneficiu beneficiu) {
        return ResponseEntity.ok(beneficiuService.adaugaBeneficiu(beneficiu));
    }

    /* // MVC laborator
    @GetMapping("/adauga")
    public String afiseazaFormularAdaugare(Model model) {
        model.addAttribute("beneficiu", new Beneficiu());
        return "formular_adaugare_beneficiu";
    }

    @PostMapping("/adauga")
    public String adaugaBeneficiuMVC(@ModelAttribute Beneficiu beneficiu, Model model) {
        Beneficiu beneficiuAdaugat = beneficiuService.adaugaBeneficiu(beneficiu);

        if (beneficiuAdaugat != null) {
            model.addAttribute("mesaj", "Beneficiu adăugat cu succes!");
        } else {
            model.addAttribute("mesaj", "Eroare la adăugarea beneficiului. Încearcă din nou.");
        }

        return "redirect:/beneficiu/afisare"; // Redirectează către lista de beneficii după adăugare
    }

    @GetMapping("/afisare")
    public ResponseEntity<List<Beneficiu>> afiseazaBeneficii() {
        List<Beneficiu> beneficii = beneficiuService.getBeneficiu();
        return new ResponseEntity<>(beneficii, HttpStatus.OK);
    }
*/
}
