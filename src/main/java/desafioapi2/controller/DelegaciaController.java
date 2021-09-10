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

import desafioapi2.dto.DelegaciaDto;
import desafioapi2.event.CriaEvento;
import desafioapi2.model.Delegacia;
import desafioapi2.repository.DelegaciaRepository;
import desafioapi2.service.DelegaciaService;

@RestController
@RequestMapping("/delegacia")
public class DelegaciaController {

	@Autowired
	private DelegaciaRepository delegaciaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private DelegaciaService delegaciaService;

	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public List<DelegaciaDto> listar() {
		List<Delegacia> delegacia = delegaciaRepository.findAll();
		return DelegaciaDto.converter(delegacia);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public ResponseEntity<DelegaciaDto> buscarPeloCodigo(@PathVariable Long id) {
		Delegacia delegacia = delegaciaService.buscarDelegacia(id);
		return ResponseEntity.ok(new DelegaciaDto(delegacia));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<DelegaciaDto> atualizar(@PathVariable Long id, @Valid @RequestBody Delegacia delegacia) {
		Delegacia delegaciaSalva = delegaciaService.atualizar(id, delegacia);
		return ResponseEntity.ok(new DelegaciaDto(delegaciaSalva));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Delegacia delegacia = delegaciaService.buscarDelegacia(id);
		delegaciaRepository.delete(delegacia);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<DelegaciaDto> criar(@Valid @RequestBody Delegacia delegacia, HttpServletResponse response) {
		Delegacia delegaciaSalva = delegaciaRepository.save(delegacia);
		publisher.publishEvent(new CriaEvento(this, response, delegaciaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new DelegaciaDto(delegaciaSalva));
	}
}
