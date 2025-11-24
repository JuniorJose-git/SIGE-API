package juniorjose.SIGE_API.model.service;

import juniorjose.SIGE_API.exeption.ApiException;
import juniorjose.SIGE_API.model.entities.Horario;
import juniorjose.SIGE_API.model.entities.Sala;
import juniorjose.SIGE_API.model.repository.HorarioRepository;
import juniorjose.SIGE_API.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository repository;

    public List<Horario> findAll(){
        return repository.findAll();
    }

    public Horario findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Horario com id " + id + " não encontrado", HttpStatus.NOT_FOUND));
    }

    public Horario create(Horario horario) throws ApiException{
        if(horario.getHorarioInicio() == null){
            throw new ApiException("O horário de início precisa ser preenchido", HttpStatus.BAD_REQUEST);
        }

        if(horario.getHorarioFim() == null){
            throw new ApiException("O horário de fim precisa ser preenchido", HttpStatus.BAD_REQUEST);
        }

        return repository.save(horario);
    }

    public Horario update(Long id, Horario horario)throws ApiException{
        Horario existente = repository.findById(id)
                .orElseThrow(() -> new ApiException("Horario com id " + id + " não encontrado",HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(horario, existente, BeanUtilsHelper.getNullPropertyNames(horario));

        return repository.save(existente);
    }

    public void delete(Long id){
        repository.findById(id)
                .orElseThrow(() -> new ApiException("Horario com id " + id + " não encontrado",HttpStatus.NOT_FOUND));
        repository.deleteById(id);
    }
}
