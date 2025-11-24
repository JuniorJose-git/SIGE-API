package juniorjose.SIGE_API.controller;

import juniorjose.SIGE_API.model.entities.DiaSemana;
import juniorjose.SIGE_API.model.entities.Sala;
import juniorjose.SIGE_API.model.service.DiaSemanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/dias_semana")
public class DiaSemanaController {

    @Autowired
    private DiaSemanaService service;

    @GetMapping
    public ResponseEntity<?> list() {
        List<DiaSemana> dias = service.findAll();
        return ResponseEntity.ok(dias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) throws Exception {
        DiaSemana diaSemana = service.findById(id);
        return ResponseEntity.ok(diaSemana);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DiaSemana diaSemana) throws Exception {
        DiaSemana salvo = service.create(diaSemana);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(diaSemana.getId())
                .toUri();
        return ResponseEntity.created(location).body(diaSemana);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DiaSemana diaSemana) throws Exception {
       DiaSemana salvo = service.update(id, diaSemana);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)  {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
