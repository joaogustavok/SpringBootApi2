package desafioapi2.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import desafioapi2.model.Policial;
import desafioapi2.repository.PolicialRepository;

@Service
public class PolicialService {

	@Autowired
	private PolicialRepository policialRepository;

	public Policial atualizar(Long id, @Valid Policial policial) {
		Policial policialSalva = buscarPolicial(id);
		BeanUtils.copyProperties(policial, policialSalva, "id");
		return policialRepository.save(policialSalva);

	}

	public Policial buscarPolicial(Long id) {
		Optional<Policial> policial = policialRepository.findById(id);
		if (policial.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return policial.get();
	}

}
