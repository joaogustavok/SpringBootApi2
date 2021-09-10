package desafioapi2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import desafioapi2.model.Policial;

public interface PolicialRepository extends JpaRepository<Policial, Long>{

	List<Policial> findByNomeEquals(String nome);

}
