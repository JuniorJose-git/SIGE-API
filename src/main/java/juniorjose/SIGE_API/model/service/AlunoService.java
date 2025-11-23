package juniorjose.SIGE_API.model.service;

import juniorjose.SIGE_API.exeption.ApiException;
import juniorjose.SIGE_API.model.entities.Aluno;
import juniorjose.SIGE_API.model.repository.AlunoRepository;
import juniorjose.SIGE_API.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) throws ApiException {
        return alunoRepository.findById(id).orElseThrow(() -> new ApiException("aluno com id " + id + " não encontrado", HttpStatus.NOT_FOUND));
    }

    public Aluno create(Aluno aluno) throws ApiException {
        return alunoRepository.save(aluno);
    }

    public Aluno update(Long id, Aluno aluno) throws ApiException {

        Aluno existing = alunoRepository.findById(id).orElseThrow(() -> new ApiException("aluno com id " + id + " não encontrado",HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(aluno, existing, BeanUtilsHelper.getNullPropertyNames(aluno));

        return alunoRepository.save(existing);

    }

    public void delete(Long id) throws ApiException {
        alunoRepository.findById(id).orElseThrow(() -> new ApiException("Aluno com id " + id + " não encontrado",HttpStatus.NOT_FOUND));
        alunoRepository.deleteById(id);
    }
}
