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

import desafioapi2.dto.VitimaDto;
import desafioapi2.event.CriaEvento;
import desafioapi2.model.Vitima;
import desafioapi2.repository.VitimaRepository;
import desafioapi2.service.VitimaService;

@RestController
@RequestMapping("/vitima")
public class VitimaController {
	@Autowired
	private VitimaRepository vitimaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private VitimaService vitimaService;

	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public List<VitimaDto> listar() {
		List<Vitima> vitima = vitimaRepository.findAll();
		return VitimaDto.converter(vitima);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public ResponseEntity<VitimaDto> buscarPeloCodigo(@PathVariable Long id) {
		Vitima vitima = vitimaService.buscarVitima(id);
		return ResponseEntity.ok(new VitimaDto(vitima));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<VitimaDto> atualizar(@PathVariable Long id, @Valid @RequestBody Vitima vitima) {
		Vitima vitimaSalva = vitimaService.atualizar(id, vitima);
		return ResponseEntity.ok(new VitimaDto(vitimaSalva));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Vitima vitima = vitimaService.buscarVitima(id);
		vitimaRepository.delete(vitima);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO') ")
	@Transactional
	public ResponseEntity<VitimaDto> criar(@Valid @RequestBody Vitima vitima, HttpServletResponse response) {
		Vitima vitimaSalva = vitimaRepository.save(vitima);
		publisher.publishEvent(new CriaEvento(this, response, vitimaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new VitimaDto(vitimaSalva));
	}
}
