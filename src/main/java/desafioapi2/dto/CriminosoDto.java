package desafioapi2.dto;

import java.util.List;
import java.util.stream.Collectors;

import desafioapi2.model.Criminoso;

public class CriminosoDto {
	private Long id;
	private String nome;
	private String cpf;

	public CriminosoDto(Criminoso criminoso) {
		this.id = criminoso.getId();
		this.nome = criminoso.getNome();
		this.cpf = criminoso.getCpf();
	}

	public static List<CriminosoDto> converter(List<Criminoso> criminosos) {
		return criminosos.stream().map(CriminosoDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

}
