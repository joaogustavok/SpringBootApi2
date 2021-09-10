package desafioapi2.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import desafioapi2.model.Delegado;
import desafioapi2.repository.DelegadoRepository;

@Service
public class DelegadoService {
	
	@Autowired
	private DelegadoRepository delegadoRepository;

	public Delegado atualizar(Long id, @Valid  Delegado delegado) {
		Delegado delegadoSalva = buscarDelegado(id);
		BeanUtils.copyProperties(delegado, delegadoSalva,"id");
		return delegadoRepository.save(delegadoSalva);
		
	}
	public Delegado buscarDelegado(Long id) {
		Optional<Delegado> delegado = delegadoRepository.findById(id);
		if(delegado.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return delegado.get();
	}

}
