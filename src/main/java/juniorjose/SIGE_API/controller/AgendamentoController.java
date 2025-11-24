package juniorjose.SIGE_API.controller;

import juniorjose.SIGE_API.model.entities.Agendamento;
import juniorjose.SIGE_API.model.entities.Turma;
import juniorjose.SIGE_API.model.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService service;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Agendamento> agendamentos = service.findAll();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) throws Exception {
        Agendamento agendamento = service.findById(id);
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Agendamento agendamento) throws Exception {
        Agendamento salvo = service.create(agendamento);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Agendamento agendamento) throws Exception {
        Agendamento salvo = service.update(id, agendamento);
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
