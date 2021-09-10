package desafioapi2.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import desafioapi2.model.Prisao;
import desafioapi2.repository.PrisaoRepository;

@Service
public class PrisaoService {
	@Autowired
	private PrisaoRepository prisaoRepository;

	public Prisao atualizar(Long id, @Valid Prisao prisao) {
		Prisao prisaoSalva = buscarPrisao(id);
		BeanUtils.copyProperties(prisao, prisaoSalva, "id");
		return prisaoRepository.save(prisaoSalva);

	}

	public Prisao buscarPrisao(Long id) {
		Optional<Prisao> prisao = prisaoRepository.findById(id);
		if (prisao.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return prisao.get();
	}
}
