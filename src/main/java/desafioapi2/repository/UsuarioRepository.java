package desafioapi2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import desafioapi2.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
}
