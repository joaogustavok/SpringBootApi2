package desafioapi2.dto;

import java.util.List;
import java.util.stream.Collectors;

import desafioapi2.model.Policial;

public class PolicialDto {

	private Long id;
	private String nome;
	private String funcional;
	private String patente;

	public PolicialDto(Policial policial) {
		this.id = policial.getId();
		this.nome = policial.getNome();
		this.funcional = policial.getFuncional();
		this.patente = policial.getPatente();
	}

	public static List<PolicialDto> converter(List<Policial> policial) {
		return policial.stream().map(PolicialDto::new).collect(Collectors.toList());
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

	public String getPatente() {
		return patente;
	}

}
