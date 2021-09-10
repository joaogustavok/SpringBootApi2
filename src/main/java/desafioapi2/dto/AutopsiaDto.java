package desafioapi2.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import desafioapi2.model.Autopsia;

public class AutopsiaDto {
	private Long id;
	private Long legistaID;
	private String legistaNome;
	private Long vitimaID;
	private String vitimaNome;
	private LocalDateTime data;
	private String laudo;

	public AutopsiaDto(Autopsia autopsia) {
		this.id = autopsia.getId();
		this.legistaID = autopsia.getLegista().getId();
		this.legistaNome = autopsia.getLegista().getNome();
		this.vitimaID = autopsia.getVitima().getId();
		this.vitimaNome = autopsia.getVitima().getNome();
		this.data = autopsia.getData();
		this.laudo = autopsia.getLaudo();
	}

	public static List<AutopsiaDto> converter(List<Autopsia> autopsias) {
		return autopsias.stream().map(AutopsiaDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public Long getLegistaID() {
		return legistaID;
	}

	public String getLegistaNome() {
		return legistaNome;
	}

	public Long getVitimaID() {
		return vitimaID;
	}

	public String getVitimaNome() {
		return vitimaNome;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getLaudo() {
		return laudo;
	}

}
