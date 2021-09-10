package desafioapi2.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import desafioapi2.model.Crime;
import desafioapi2.repository.CrimeRepository;

@Service
public class CrimeService {
	@Autowired
	private CrimeRepository crimeRepository;

	public Crime atualizar(Long id, @Valid Crime crime) {
		Crime crimeSalva = buscarCrime(id);
		BeanUtils.copyProperties(crime, crimeSalva, "id");
		return crimeRepository.save(crimeSalva);

	}

	public Crime buscarCrime(Long id) {
		Optional<Crime> crime = crimeRepository.findById(id);
		if (crime.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return crime.get();
	}
}
