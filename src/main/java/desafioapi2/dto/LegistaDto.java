package desafioapi2.dto;

import java.util.List;
import java.util.stream.Collectors;

import desafioapi2.model.Legista;

public class LegistaDto {
	private Long id;
	private String nome;
	private String crm;

	public LegistaDto(Legista legista) {
		this.id = legista.getId();
		this.nome = legista.getNome();
		this.crm = legista.getCrm();
	}

	public static List<LegistaDto> converter(List<Legista> legistas) {
		return legistas.stream().map(LegistaDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCrm() {
		return crm;
	}

}
