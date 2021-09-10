package desafioapi2.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Embedded;

import desafioapi2.model.Delegacia;
import desafioapi2.model.Endereco;

public class DelegaciaDto {

	private Long id;
	@Embedded
	private Endereco endereco;
	private String telefone;
	private String batalhao;

	public DelegaciaDto(Delegacia delegacia) {
		this.id = delegacia.getId();
		this.endereco = delegacia.getEndereco();
		this.telefone = delegacia.getTelefone();
		this.batalhao = delegacia.getBatalhao();
	}

	public static List<DelegaciaDto> converter(List<Delegacia> delegacias) {
		return delegacias.stream().map(DelegaciaDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getBatalhao() {
		return batalhao;
	}

}
