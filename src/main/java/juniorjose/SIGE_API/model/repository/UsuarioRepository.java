package juniorjose.SIGE_API.model.repository;

import juniorjose.SIGE_API.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
