package juniorjose.SIGE_API.controller;

import juniorjose.SIGE_API.model.entities.Horario;
import juniorjose.SIGE_API.model.entities.Sala;
import juniorjose.SIGE_API.model.service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Horario> horarios = service.findAll();
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) throws Exception {
        Horario horario = service.findById(id);
        return ResponseEntity.ok(horario);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Horario horario) throws Exception {
        Horario salvo = service.create(horario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Horario horario) throws Exception {
       Horario salvo = service.update(id, horario);
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
