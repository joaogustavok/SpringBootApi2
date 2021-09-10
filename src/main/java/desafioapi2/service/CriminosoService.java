package desafioapi2.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import desafioapi2.model.Criminoso;
import desafioapi2.repository.CriminosoRepository;

@Service
public class CriminosoService {
	@Autowired
	private CriminosoRepository criminosoRepository;

	public Criminoso atualizar(Long id, @Valid Criminoso criminoso) {
		Criminoso criminosoSalva = buscarCriminoso(id);
		BeanUtils.copyProperties(criminoso, criminosoSalva,"id");
		return criminosoRepository.save(criminosoSalva);
		
	}
	public Criminoso buscarCriminoso(Long id) {
		Optional<Criminoso> criminoso = criminosoRepository.findById(id);
		if(criminoso.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return criminoso.get();
	}
}
