package juniorjose.SIGE_API.model.service;

import juniorjose.SIGE_API.exeption.ApiException;
import juniorjose.SIGE_API.model.entities.Usuario;
import juniorjose.SIGE_API.model.repository.UsuarioRepository;
import juniorjose.SIGE_API.util.BeanUtilsHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) throws ApiException {
        return usuarioRepository.findById(id).orElseThrow(() -> new ApiException("Usuário com id " + id + " não encontrado", HttpStatus.NOT_FOUND));
    }

    public Usuario create(Usuario usuario) throws ApiException {

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new ApiException("O e-mail precisa ser preenchido", HttpStatus.BAD_REQUEST);
        }

        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new ApiException("A senha precisa ser preenchida", HttpStatus.BAD_REQUEST);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuario) throws ApiException {

        Usuario existing = usuarioRepository.findById(id).orElseThrow(() -> new ApiException("Usuário com id " + id + " não encontrado",HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(usuario, existing, BeanUtilsHelper.getNullPropertyNames(usuario));

        return usuarioRepository.save(existing);

    }

    public void delete(Long id) throws ApiException {
        usuarioRepository.findById(id).orElseThrow(() -> new ApiException("Usuário com id " + id + " não encontrado",HttpStatus.NOT_FOUND));
        usuarioRepository.deleteById(id);
    }

}
