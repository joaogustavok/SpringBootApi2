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

import desafioapi2.dto.CriminosoDto;
import desafioapi2.event.CriaEvento;
import desafioapi2.model.Criminoso;
import desafioapi2.repository.CriminosoRepository;
import desafioapi2.service.CriminosoService;

@RestController
@RequestMapping("/criminoso")
public class CriminosoController {
	@Autowired
	private CriminosoRepository criminosoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private CriminosoService criminosoService;

	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO') ")
	public List<CriminosoDto> listar() {
		List<Criminoso> criminoso = criminosoRepository.findAll();
		return CriminosoDto.converter(criminoso);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO') ")
	public ResponseEntity<CriminosoDto> buscarPeloCodigo(@PathVariable Long id) {
		Criminoso criminoso = criminosoService.buscarCriminoso(id);
		return ResponseEntity.ok(new CriminosoDto(criminoso));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO') ")
	@Transactional
	public ResponseEntity<CriminosoDto> atualizar(@PathVariable Long id, @Valid @RequestBody Criminoso criminoso) {
		Criminoso criminosoSalva = criminosoService.atualizar(id, criminoso);
		return ResponseEntity.ok(new CriminosoDto(criminosoSalva));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO') ")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Criminoso criminoso = criminosoService.buscarCriminoso(id);
		criminosoRepository.delete(criminoso);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO') ")
	@Transactional
	public ResponseEntity<CriminosoDto> criar(@Valid @RequestBody Criminoso criminoso, HttpServletResponse response) {
		Criminoso criminosoSalva = criminosoRepository.save(criminoso);
		publisher.publishEvent(new CriaEvento(this, response, criminosoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new CriminosoDto(criminosoSalva));
	}
}
