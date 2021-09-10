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

import desafioapi2.dto.PolicialDto;
import desafioapi2.event.CriaEvento;
import desafioapi2.model.Policial;
import desafioapi2.repository.PolicialRepository;
import desafioapi2.service.PolicialService;

@RestController
@RequestMapping("/policial")
public class PolicialController {

	@Autowired
	private PolicialRepository policialRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private PolicialService policialService;

	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO') ")
	public List<PolicialDto> listar() {
		List<Policial> policial = policialRepository.findAll();
		return PolicialDto.converter(policial);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
	public ResponseEntity<PolicialDto> buscarPeloCodigo(@PathVariable Long id) {
		Policial policial = policialService.buscarPolicial(id);
		return ResponseEntity.ok(new PolicialDto(policial));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<PolicialDto> atualizar(@PathVariable Long id, @Valid @RequestBody Policial policial) {
		Policial policialSalva = policialService.atualizar(id, policial);
		return ResponseEntity.ok(new PolicialDto(policialSalva));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Policial policial = policialService.buscarPolicial(id);
		policialRepository.delete(policial);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO')")
	@Transactional
	public ResponseEntity<PolicialDto> criar(@Valid @RequestBody Policial policial, HttpServletResponse response) {
		Policial policialSalva = policialRepository.save(policial);
		publisher.publishEvent(new CriaEvento(this, response, policialSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new PolicialDto(policialSalva));
	}
}
