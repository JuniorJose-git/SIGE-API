package juniorjose.SIGE_API.model.repository;
import juniorjose.SIGE_API.model.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
