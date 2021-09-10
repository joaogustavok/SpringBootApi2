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

import desafioapi2.dto.LegistaDto;
import desafioapi2.event.CriaEvento;
import desafioapi2.model.Legista;
import desafioapi2.repository.LegistaRepository;
import desafioapi2.service.LegistaService;

@RestController
@RequestMapping("/legista")
public class LegistaController {
	@Autowired
	private LegistaRepository legistaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private LegistaService legistaService;

	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public List<LegistaDto> listar() {
		List<Legista> legista = legistaRepository.findAll();
		return LegistaDto.converter(legista);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public ResponseEntity<LegistaDto> buscarPeloCodigo(@PathVariable Long id) {
		Legista legista = legistaService.buscarLegista(id);
		return ResponseEntity.ok(new LegistaDto(legista));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<LegistaDto> atualizar(@PathVariable Long id, @Valid @RequestBody Legista legista) {
		Legista legistaSalva = legistaService.atualizar(id, legista);
		return ResponseEntity.ok(new LegistaDto(legistaSalva));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Legista legista = legistaService.buscarLegista(id);
		legistaRepository.delete(legista);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<LegistaDto> criar(@Valid @RequestBody Legista legista, HttpServletResponse response) {
		Legista legistaSalva = legistaRepository.save(legista);
		publisher.publishEvent(new CriaEvento(this, response, legistaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new LegistaDto(legistaSalva));
	}
}
