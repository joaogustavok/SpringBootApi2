package desafioapi2.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import desafioapi2.model.Crime;

public class CrimeDto {
	private Long idCrime;
	private Long vitimaID;
	private String vitimaNome;
	private Long criminosoID;
	private String criminosoNome;
	private LocalDateTime data;
	private String descricao;

	public CrimeDto(Crime crime) {
		this.idCrime = crime.getId();
		this.vitimaID = crime.getVitima().getId();
		this.vitimaNome = crime.getVitima().getNome();
		this.criminosoID = crime.getCriminoso().getId();
		this.criminosoNome = crime.getCriminoso().getNome();
		this.data = crime.getData();
		this.descricao = crime.getDescricao();
	}

	public static List<CrimeDto> converter(List<Crime> crimes) {
		return crimes.stream().map(CrimeDto::new).collect(Collectors.toList());
	}

	public Long getIdCrime() {
		return idCrime;
	}

	public Long getVitimaID() {
		return vitimaID;
	}

	public String getVitimaNome() {
		return vitimaNome;
	}

	public Long getCriminosoID() {
		return criminosoID;
	}

	public String getCriminosoNome() {
		return criminosoNome;
	}

	public LocalDateTime getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}

}
