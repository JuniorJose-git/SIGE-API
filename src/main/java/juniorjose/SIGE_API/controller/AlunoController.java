package juniorjose.SIGE_API.controller;

import juniorjose.SIGE_API.model.entities.Aluno;
import juniorjose.SIGE_API.model.entities.Professor;
import juniorjose.SIGE_API.model.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // lista todos os alunos
    @GetMapping
    public ResponseEntity<?> list() {
        List<Aluno> alunos = alunoService.findAll();
        return ResponseEntity.ok(alunos);
    }

    // Encontra um aluno por id
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) throws Exception {
        Aluno aluno = alunoService.findById(id);
        return ResponseEntity.ok(aluno);
    }

    // Cria um novo aluno
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Aluno aluno) throws Exception {
        Aluno saved = alunoService.create(aluno);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    // atualiza um aluno
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Aluno aluno) throws Exception {
        Aluno saved = alunoService.update(id, aluno);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    // deleta um aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)  {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
