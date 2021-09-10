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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafioapi2.dto.DelegadoDto;
import desafioapi2.event.CriaEvento;
import desafioapi2.model.Delegado;
import desafioapi2.repository.DelegadoRepository;
import desafioapi2.service.DelegadoService;

@RestController
@RequestMapping("/delegado")
public class DelegadoController {

	@Autowired
	private DelegadoRepository delegadoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private DelegadoService delegadoService;

	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public List<DelegadoDto> listar() {
		List<Delegado> delegado = delegadoRepository.findAll();
		return DelegadoDto.converter(delegado);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public ResponseEntity<DelegadoDto> buscarPeloCodigo(@PathVariable Long id) {
		Delegado delegado = delegadoService.buscarDelegado(id);
		return ResponseEntity.ok(new DelegadoDto(delegado));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<DelegadoDto> atualizar(@PathVariable Long id, @Valid @RequestBody Delegado delegado) {
		Delegado delegadoSalva = delegadoService.atualizar(id, delegado);
		return ResponseEntity.ok(new DelegadoDto(delegadoSalva));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Delegado delegado = delegadoService.buscarDelegado(id);
		delegadoRepository.delete(delegado);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<DelegadoDto> criar(@Valid @RequestBody Delegado delegado, HttpServletResponse response) {
		Delegado delegadoSalva = delegadoRepository.save(delegado);
		publisher.publishEvent(new CriaEvento(this, response, delegadoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new DelegadoDto(delegadoSalva));
	}
}
