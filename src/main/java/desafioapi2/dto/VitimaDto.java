package desafioapi2.dto;

import java.util.List;
import java.util.stream.Collectors;

import desafioapi2.model.Vitima;

public class VitimaDto {
	private Long id;
	private String nome;
	private String cpf;

	public VitimaDto(Vitima vitima) {
		this.id = vitima.getId();
		this.nome = vitima.getNome();
		this.cpf = vitima.getCpf();
	}

	public static List<VitimaDto> converter(List<Vitima> vitimas) {
		return vitimas.stream().map(VitimaDto::new).collect(Collectors.toList());
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
