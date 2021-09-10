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

import desafioapi2.dto.CrimeDto;
import desafioapi2.event.CriaEvento;
import desafioapi2.model.Crime;
import desafioapi2.repository.CrimeRepository;
import desafioapi2.service.CrimeService;

@RestController
@RequestMapping("/crime")
public class CrimeController {

	@Autowired
	private CrimeRepository crimeRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private CrimeService crimeService;

	@GetMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO') ")
	public List<CrimeDto> listar() {
		List<Crime> crime = crimeRepository.findAll();
		return CrimeDto.converter(crime);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO') ")
	public ResponseEntity<CrimeDto> buscarPeloCodigo(@PathVariable Long id) {
		Crime crime = crimeService.buscarCrime(id);
		return ResponseEntity.ok(new CrimeDto(crime));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO') ")
	@Transactional
	public ResponseEntity<CrimeDto> atualizar(@PathVariable Long id, @Valid @RequestBody Crime crime) {
		Crime crimeSalva = crimeService.atualizar(id, crime);
		return ResponseEntity.ok(new CrimeDto(crimeSalva));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO') ")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Crime crime = crimeService.buscarCrime(id);
		crimeRepository.delete(crime);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('DELEGADO') ")
	@Transactional
	public ResponseEntity<CrimeDto> criar(@Valid @RequestBody Crime crime, HttpServletResponse response) {
		Crime crimeSalva = crimeRepository.save(crime);
		publisher.publishEvent(new CriaEvento(this, response, crimeSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(new CrimeDto(crimeSalva));
	}
}
