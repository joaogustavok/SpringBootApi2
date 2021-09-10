package desafioapi2.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import desafioapi2.model.Delegado;

public class DelegadoDto {
	private Long id;
	private String nome;
	private String delegaciaBatalhao;
	private Long delegaciaId;
	private String funcional;
	private String turno;

	public DelegadoDto(Delegado delegado) {
		this.id = delegado.getId();
		this.nome = delegado.getNome();
		this.delegaciaId = delegado.getDelegacia().getId();
		this.delegaciaBatalhao = delegado.getDelegacia().getBatalhao();
		this.funcional = delegado.getFuncional();
		this.turno = delegado.getTurno();
	}

	public static List<DelegadoDto> converter(List<Delegado> delegados) {
		return delegados.stream().map(DelegadoDto::new).collect(Collectors.toList());

	}
	
	public static Page<DelegadoDto> converter2(Page<Delegado> delegados) {
		return delegados.map(DelegadoDto::new);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

		public String getFuncional() {
		return funcional;
	}

	public String getTurno() {
		return turno;
	}

	public String getDelegaciaBatalhao() {
		return delegaciaBatalhao;
	}

	public Long getDelegaciaId() {
		return delegaciaId;
	}

	
	

}
