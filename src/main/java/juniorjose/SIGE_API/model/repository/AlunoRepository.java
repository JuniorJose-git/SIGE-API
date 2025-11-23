package juniorjose.SIGE_API.model.repository;
import juniorjose.SIGE_API.model.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
