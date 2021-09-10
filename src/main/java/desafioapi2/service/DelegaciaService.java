package desafioapi2.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import desafioapi2.model.Delegacia;
import desafioapi2.repository.DelegaciaRepository;

@Service
public class DelegaciaService {
	
	@Autowired
	private DelegaciaRepository delegaciaRepository;

	public Delegacia atualizar(Long id, @Valid Delegacia delegacia) {
		Delegacia delegaciaSalva = buscarDelegacia(id);
		BeanUtils.copyProperties(delegacia, delegaciaSalva,"id");
		return delegaciaRepository.save(delegaciaSalva);
		
	}
	public Delegacia buscarDelegacia(Long id) {
		Optional<Delegacia> delegacia = delegaciaRepository.findById(id);
		if(delegacia.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return delegacia.get();
	}

}
