package desafioapi2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import desafioapi2.dto.DelegadoDto;
import desafioapi2.dto.PolicialDto;
import desafioapi2.model.Delegado;
import desafioapi2.model.Policial;
import desafioapi2.repository.DelegadoRepository;
import desafioapi2.repository.PolicialRepository;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('JUIZ') or hasAuthority('ADVOGADO') or hasAuthority('DELEGADO')")
public class ExceedsController {
 
	@Autowired
	private DelegadoRepository delegadoRepository;
	
	@Autowired
	private PolicialRepository policialRepository;
	
	
	@GetMapping("/delegado/asc")
	public Page<DelegadoDto> listarAsc( @PageableDefault(sort = "nome",page =0,size=10) Pageable paginacao){
		Page<Delegado> delegados = delegadoRepository.findAll(paginacao);
		return DelegadoDto.converter2(delegados);
	}
	
	@GetMapping("/delegado/desc")
	public Page<DelegadoDto> listarDesc( @PageableDefault(sort = "nome",page =0,size=10,direction = Direction.DESC) Pageable paginacao){
		Page<Delegado> delegados = delegadoRepository.findAll(paginacao);
		return DelegadoDto.converter2(delegados);
	}
	
	@GetMapping("/policial")
	public List<PolicialDto> ProcurarPeloNome(@RequestParam String nome){
		List<Policial> policial = policialRepository.findByNomeEquals(nome);
		return PolicialDto.converter(policial);
	}
		
	}
	

