package desafioapi2.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import desafioapi2.model.Autopsia;
import desafioapi2.repository.AutopsiaRepository;

@Service
public class AutopsiaService {
	@Autowired
	private AutopsiaRepository autopsiaRepository;

	public Autopsia atualizar(Long id, @Valid Autopsia autopsia) {
		Autopsia autopsiaSalva = buscarAutopsia(id);
		BeanUtils.copyProperties(autopsia, autopsiaSalva,"id");
		return autopsiaRepository.save(autopsiaSalva);
		
	}
	public Autopsia buscarAutopsia(Long id) {
		Optional<Autopsia> autopsia = autopsiaRepository.findById(id);
		if(autopsia.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return autopsia.get();
	}
}
