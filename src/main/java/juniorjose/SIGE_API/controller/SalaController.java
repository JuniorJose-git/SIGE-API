package juniorjose.SIGE_API.controller;

import juniorjose.SIGE_API.model.entities.Aluno;
import juniorjose.SIGE_API.model.entities.Sala;
import juniorjose.SIGE_API.model.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService service;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Sala> salas = service.findAll();
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) throws Exception {
        Sala sala = service.findById(id);
        return ResponseEntity.ok(sala);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Sala sala) throws Exception {
        Sala salva = service.create(sala);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salva.getId())
                .toUri();
        return ResponseEntity.created(location).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Sala sala) throws Exception {
        Sala salva = service.update(id, sala);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salva.getId())
                .toUri();
        return ResponseEntity.created(location).body(salva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)  {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
