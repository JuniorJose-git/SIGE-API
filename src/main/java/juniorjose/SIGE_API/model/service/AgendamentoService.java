package juniorjose.SIGE_API.model.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import juniorjose.SIGE_API.exeption.ApiException;
import juniorjose.SIGE_API.model.entities.*;
import juniorjose.SIGE_API.model.repository.AgendamentoRepository;
import juniorjose.SIGE_API.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    @Transactional
    public Agendamento create(Agendamento agendamento){
        repository.saveAndFlush(agendamento);

        Optional<Agendamento> optionalEntity = repository.findById(agendamento.getId());
        optionalEntity.ifPresent(entity -> entityManager.refresh(entity));
        return optionalEntity.orElseThrow(() -> new ApiException("Agendamento com id " + agendamento.getId() + " não encontrado", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Agendamento update(Long id, Agendamento agendamento) throws ApiException{
        Agendamento existente = repository.findById(id).orElseThrow(() -> new ApiException("Agendamento com id " + id + " não encontrado",HttpStatus.NOT_FOUND));

        if (agendamento.getSala() != null && agendamento.getSala().getId() != null)
            existente.setSala(entityManager.getReference(Sala.class, agendamento.getSala().getId()));

        if (agendamento.getDiaSemana() != null && agendamento.getDiaSemana().getId() != null)
            existente.setDiaSemana(entityManager.getReference(DiaSemana.class, agendamento.getDiaSemana().getId()));

        if (agendamento.getHorario() != null && agendamento.getHorario().getId() != null)
            existente.setHorario(entityManager.getReference(Horario.class, agendamento.getHorario().getId()));

        if (agendamento.getTurma() != null && agendamento.getTurma().getId() != null)
            existente.setTurma(entityManager.getReference(Turma.class, agendamento.getTurma().getId()));

        return  repository.save(existente);
    }

    public void delete(Long id) throws ApiException {
        repository.findById(id).orElseThrow(() -> new ApiException("Agendamento com id " + id + " não encontrado",HttpStatus.NOT_FOUND));
        repository.deleteById(id);
    }
}
