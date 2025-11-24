package juniorjose.SIGE_API.model.service;

import jakarta.persistence.EntityManager;
import juniorjose.SIGE_API.exeption.ApiException;
import juniorjose.SIGE_API.model.entities.Agendamento;
import juniorjose.SIGE_API.model.entities.Turma;
import juniorjose.SIGE_API.model.repository.AgendamentoRepository;
import juniorjose.SIGE_API.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private   AgendamentoRepository repository;

    @Autowired
    private EntityManager entityManager;


    public List<Agendamento> findAll(){
        return repository.findAll();
    }

    public Agendamento findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Agendamento com id " + id + " não encontrado", HttpStatus.NOT_FOUND));
    }

    public Agendamento create(Agendamento agendamento){
        repository.saveAndFlush(agendamento);

        Optional<Agendamento> optionalEntity = repository.findById(agendamento.getId());
        optionalEntity.ifPresent(entity -> entityManager.refresh(entity));
        return optionalEntity.orElseThrow(() -> new ApiException("Agendamento com id " + agendamento.getId() + " não encontrado", HttpStatus.NOT_FOUND));
    }

    public Agendamento update(Long id, Agendamento agendamento) throws ApiException{
        Agendamento existente = repository.findById(id).orElseThrow(() -> new ApiException("Agendamento com id " + id + " não encontrado",HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(agendamento, existente, BeanUtilsHelper.getNullPropertyNames(agendamento));

        return  repository.save(existente);
    }

    public void delete(Long id) throws ApiException {
        repository.findById(id).orElseThrow(() -> new ApiException("Agendamento com id " + id + " não encontrado",HttpStatus.NOT_FOUND));
        repository.deleteById(id);
    }
}
