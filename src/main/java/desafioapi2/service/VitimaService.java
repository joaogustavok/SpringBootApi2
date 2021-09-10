package desafioapi2.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import desafioapi2.model.Vitima;
import desafioapi2.repository.VitimaRepository;

@Service
public class VitimaService {
	@Autowired
	private VitimaRepository vitimaRepository;

	public Vitima atualizar(Long id, @Valid Vitima vitima) {
		Vitima vitimaSalva = buscarVitima(id);
		BeanUtils.copyProperties(vitima, vitimaSalva,"id");
		return vitimaRepository.save(vitimaSalva);
		
	}
	public Vitima buscarVitima(Long id) {
		Optional<Vitima> vitima = vitimaRepository.findById(id);
		if(vitima.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return vitima.get();
	}
}
