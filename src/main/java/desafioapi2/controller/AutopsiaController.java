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

import desafioapi2.dto.AutopsiaDto;
import desafioapi2.event.CriaEvento;
import desafioapi2.model.Autopsia;
import desafioapi2.repository.AutopsiaRepository;
import desafioapi2.service.AutopsiaService;

@RestController
@RequestMapping("/autopsia")
public class AutopsiaController {

	@Autowired
	private AutopsiaRepository autopsiaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private AutopsiaService autopsiaService;

	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public List<AutopsiaDto> listar() {
		List<Autopsia> autopsia = autopsiaRepository.findAll();
		return AutopsiaDto.converter(autopsia);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public ResponseEntity<AutopsiaDto> buscarPeloCodigo(@PathVariable Long id) {
		Autopsia autopsia = autopsiaService.buscarAutopsia(id);
		return ResponseEntity.ok(new AutopsiaDto(autopsia));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<AutopsiaDto> criar(@Valid @RequestBody Autopsia autopsia, HttpServletResponse response) {
		Autopsia autopsiaSalva = autopsiaRepository.save(autopsia);
		publisher.publishEvent(new CriaEvento(this, response, autopsiaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new AutopsiaDto(autopsiaSalva));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<AutopsiaDto> atualizar(@PathVariable Long id, @Valid @RequestBody Autopsia autopsia) {
		Autopsia autopsiaSalva = autopsiaService.atualizar(id, autopsia);
		return ResponseEntity.ok(new AutopsiaDto(autopsiaSalva));
	}
}
