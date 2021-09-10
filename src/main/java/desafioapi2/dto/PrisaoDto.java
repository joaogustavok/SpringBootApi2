package desafioapi2.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import desafioapi2.model.Prisao;

public class PrisaoDto {
	private Long id;
	private Long vitimaID;
	private String vitimaNome;
	private Long criminosoID;
	private String criminosoNome;
	private Long policialID;
	private String policialNome;
	private String policialPatente;
	private Long delegadoID;
	private String delegadoNome;
	private Long delegaciaID;
	private String delegaciaBatalhao;
	private LocalDateTime data;

	public PrisaoDto(Prisao prisao) {
		this.id = prisao.getId();
		this.vitimaID = prisao.getVitima().getId();
		this.vitimaNome = prisao.getVitima().getNome();
		this.criminosoID = prisao.getCriminoso().getId();
		this.criminosoNome = prisao.getCriminoso().getNome();
		this.policialID = prisao.getPolicial().getId();
		this.policialNome = prisao.getPolicial().getNome();
		this.policialPatente = prisao.getPolicial().getPatente();
		this.delegadoID = prisao.getDelegado().getId();
		this.delegadoNome = prisao.getDelegado().getNome();
		this.delegaciaID = prisao.getDelegado().getDelegacia().getId();
		this.delegaciaBatalhao = prisao.getDelegado().getDelegacia().getBatalhao();
		this.data = prisao.getData();
	}

	public static List<PrisaoDto> converter(List<Prisao> prisao) {
		return prisao.stream().map(PrisaoDto::new).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
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

	public Long getPolicialID() {
		return policialID;
	}

	public String getPolicialNome() {
		return policialNome;
	}

	public String getPolicialPatente() {
		return policialPatente;
	}

	public Long getDelegadoID() {
		return delegadoID;
	}

	public String getDelegadoNome() {
		return delegadoNome;
	}

	public Long getDelegaciaID() {
		return delegaciaID;
	}

	public String getDelegaciaBatalhao() {
		return delegaciaBatalhao;
	}

	public LocalDateTime getData() {
		return data;
	}

}
