package desafioapi2.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import desafioapi2.model.Legista;
import desafioapi2.repository.LegistaRepository;

@Service
public class LegistaService {
	@Autowired
	private LegistaRepository legistaRepository;

	public Legista atualizar(Long id, @Valid Legista legista) {
		Legista legistaSalva = buscarLegista(id);
		BeanUtils.copyProperties(legista, legistaSalva,"id");
		return legistaRepository.save(legistaSalva);
		
	}
	public Legista buscarLegista(Long id) {
		Optional<Legista> legista = legistaRepository.findById(id);
		if(legista.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return legista.get();
	}
}
