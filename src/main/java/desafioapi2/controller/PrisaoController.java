package desafioapi2.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafioapi2.dto.PrisaoDto;
import desafioapi2.dto.form.PrisaoForm;
import desafioapi2.event.CriaEvento;
import desafioapi2.model.Prisao;
import desafioapi2.repository.CrimeRepository;
import desafioapi2.repository.DelegadoRepository;
import desafioapi2.repository.PolicialRepository;
import desafioapi2.repository.PrisaoRepository;
import desafioapi2.service.PrisaoService;

@RestController
@RequestMapping("/prisao")
public class PrisaoController {

	@Autowired
	private PrisaoRepository prisaoRepository;
	@Autowired
	private CrimeRepository crime;
	@Autowired
	private DelegadoRepository delegado;
	@Autowired
	private PolicialRepository policial;
	@Autowired
	private PrisaoService prisaoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public List<PrisaoDto> listar() {
		List<Prisao> prisao = prisaoRepository.findAll();
		return PrisaoDto.converter(prisao);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public ResponseEntity<PrisaoDto> buscarPeloCodigo(@PathVariable Long id) {
		Prisao prisao = prisaoService.buscarPrisao(id);
		return ResponseEntity.ok(new PrisaoDto(prisao));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<PrisaoDto> cadastrar(@Valid @RequestBody PrisaoForm form, HttpServletResponse response) {
		Prisao prisaoSalva = form.converter(crime, delegado, policial);
		prisaoRepository.save(prisaoSalva);
		publisher.publishEvent(new CriaEvento(this, response, prisaoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new PrisaoDto(prisaoSalva));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<PrisaoDto> atualizar(@PathVariable Long id, @Valid @RequestBody Prisao prisao) {
		Prisao prisaoSalva = prisaoService.atualizar(id, prisao);
		return ResponseEntity.ok(new PrisaoDto(prisaoSalva));
	}

}
