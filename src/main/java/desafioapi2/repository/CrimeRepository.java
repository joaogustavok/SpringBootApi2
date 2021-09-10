package desafioapi2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import desafioapi2.model.Crime;

public interface CrimeRepository extends JpaRepository<Crime, Long>{

}
