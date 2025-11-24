package juniorjose.SIGE_API.model.service;

import juniorjose.SIGE_API.exeption.ApiException;
import juniorjose.SIGE_API.model.entities.DiaSemana;
import juniorjose.SIGE_API.model.entities.Horario;
import juniorjose.SIGE_API.model.repository.DiaSemanaRepository;
import juniorjose.SIGE_API.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaSemanaService {

    @Autowired
    private DiaSemanaRepository repository;

    public List<DiaSemana> findAll(){
        return repository.findAll();
    }

    public DiaSemana findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Dia da semana com id " + id + " não encontrado", HttpStatus.NOT_FOUND));
    }

    public DiaSemana create(DiaSemana diaSemana) throws ApiException{
        if(diaSemana.getNome() == null || diaSemana.getNome().isEmpty()){
            throw new ApiException("O nome do dia da semana precisa ser preenchido", HttpStatus.BAD_REQUEST);
        }
        return repository.save(diaSemana);
    }

    public DiaSemana update(Long id, DiaSemana diaSemana)throws ApiException{
        DiaSemana existente = repository.findById(id)
                .orElseThrow(() -> new ApiException("Dia da semana com id " + id + " não encontrado",HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(diaSemana, existente, BeanUtilsHelper.getNullPropertyNames(diaSemana));

        return repository.save(existente);
    }

    public void delete(Long id){
        repository.findById(id)
                .orElseThrow(() -> new ApiException("Dia d asemana com id " + id + " não encontrado",HttpStatus.NOT_FOUND));
        repository.deleteById(id);
    }

}
