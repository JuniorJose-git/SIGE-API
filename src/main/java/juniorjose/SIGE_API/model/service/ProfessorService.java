package juniorjose.SIGE_API.model.service;

import juniorjose.SIGE_API.exeption.ApiException;
import juniorjose.SIGE_API.model.entities.Professor;
import juniorjose.SIGE_API.model.repository.ProfessorRepository;
import juniorjose.SIGE_API.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor findById(Long id) throws ApiException {
        return professorRepository.findById(id).orElseThrow(() -> new ApiException("professor com id " + id + " não encontrado", HttpStatus.NOT_FOUND));
    }

    public Professor create(Professor professor) throws ApiException {
        return professorRepository.save(professor);
    }

    public Professor update(Long id, Professor professor) throws ApiException {

        Professor existing = professorRepository.findById(id).orElseThrow(() -> new ApiException("Professor com id " + id + " não encontrado",HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(professor, existing, BeanUtilsHelper.getNullPropertyNames(professor));

        return professorRepository.save(existing);

    }

    public void delete(Long id) throws ApiException {
        professorRepository.findById(id).orElseThrow(() -> new ApiException("Professor com id " + id + " não encontrado",HttpStatus.NOT_FOUND));
        professorRepository.deleteById(id);
    }
}
