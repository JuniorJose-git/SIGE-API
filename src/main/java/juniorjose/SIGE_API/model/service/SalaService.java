package juniorjose.SIGE_API.model.service;

import juniorjose.SIGE_API.exeption.ApiException;
import juniorjose.SIGE_API.model.entities.Sala;
import juniorjose.SIGE_API.model.entities.Usuario;
import juniorjose.SIGE_API.model.repository.SalaRepository;
import juniorjose.SIGE_API.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {
    @Autowired
    private SalaRepository repository;

    public List<Sala> findAll(){return repository.findAll();}

    public Sala findById(Long id) throws Exception{
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Sala com id " + id + " não encontrada", HttpStatus.NOT_FOUND));
    }

    public Sala create(Sala sala) throws ApiException{
        if( sala.getNome() == null || sala.getNome().isEmpty()){
            throw new ApiException("O nome precisa ser preenchido", HttpStatus.BAD_REQUEST);
        }

        return repository.save(sala);
    }

    public Sala update(Long id, Sala sala) throws ApiException{

        Sala existente = repository.findById(id)
                .orElseThrow(() -> new ApiException("Sala com id " + id + " não encontrada",HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(sala, existente, BeanUtilsHelper.getNullPropertyNames(existente));

        return repository.save(existente);
    }

    public void delete(Long id){
        repository.findById(id)
                .orElseThrow(() -> new ApiException("Sala com id " + id + " não encontrada",HttpStatus.NOT_FOUND));
        repository.deleteById(id);
    }
}
