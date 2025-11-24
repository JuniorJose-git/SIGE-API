package juniorjose.SIGE_API.controller;

import juniorjose.SIGE_API.model.entities.Aluno;
import juniorjose.SIGE_API.model.entities.Turma;
import juniorjose.SIGE_API.model.service.AlunoService;
import juniorjose.SIGE_API.model.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    // lista todas as turmas
    @GetMapping
    public ResponseEntity<?> list() {
        List<Turma> professors = turmaService.findAll();
        return ResponseEntity.ok(professors);
    }

    // Encontra uma turma por id
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) throws Exception {
        Turma turma = turmaService.findById(id);
        return ResponseEntity.ok(turma);
    }

    // Cria uma nova turma
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Turma turma) throws Exception {
        Turma saved = turmaService.create(turma);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    // atualiza uma turma
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Turma turma) throws Exception {
        Turma saved = turmaService.update(id, turma);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    // deleta uma turma
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)  {
        turmaService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
