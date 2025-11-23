package juniorjose.SIGE_API.model.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import juniorjose.SIGE_API.exeption.ApiException;
import juniorjose.SIGE_API.model.entities.Turma;
import juniorjose.SIGE_API.model.repository.ProfessorRepository;
import juniorjose.SIGE_API.model.repository.TurmaRepository;
import juniorjose.SIGE_API.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService  {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Turma findById(Long id) throws ApiException {
        return turmaRepository.findById(id).orElseThrow(() -> new ApiException("turma com id " + id + " não encontrada", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Turma create(Turma turma) {

        turmaRepository.saveAndFlush(turma);

        Optional<Turma> optionalEntity = turmaRepository.findById(turma.getId());
        optionalEntity.ifPresent(entity -> entityManager.refresh(entity));
        return optionalEntity.orElseThrow(() -> new ApiException("turma com id " + turma.getId() + " não encontrada", HttpStatus.NOT_FOUND));
    }

    public Turma update(Long id, Turma turma) throws ApiException {

        Turma existing = turmaRepository.findById(id).orElseThrow(() -> new ApiException("Turma com id " + id + " não encontrada",HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(turma, existing, BeanUtilsHelper.getNullPropertyNames(turma));

        return turmaRepository.save(existing);

    }

    public void delete(Long id) throws ApiException {
        turmaRepository.findById(id).orElseThrow(() -> new ApiException("Turma com id " + id + " não encontrada",HttpStatus.NOT_FOUND));
        turmaRepository.deleteById(id);
    }

}
