package juniorjose.SIGE_API.controller;

import juniorjose.SIGE_API.model.entities.Professor;
import juniorjose.SIGE_API.model.entities.Usuario;
import juniorjose.SIGE_API.model.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    // lista todos os professores
    @GetMapping
    public ResponseEntity<?> list() {
        List<Professor> professors = professorService.findAll();
        return ResponseEntity.ok(professors);
    }

    // Encontra um professor por id
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) throws Exception {
        Professor professor = professorService.findById(id);
        return ResponseEntity.ok(professor);
    }

    // Cria um novo professor
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Professor professor) throws Exception {
        Professor saved = professorService.create(professor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    // atualiza um professor
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Professor professor) throws Exception {
        Professor saved = professorService.update(id, professor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    // deleta um professor
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)  {
        professorService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
