package juniorjose.SIGE_API.model.repository;

import juniorjose.SIGE_API.model.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
