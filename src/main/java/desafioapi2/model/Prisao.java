package desafioapi2.model;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Prisao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private Policial policial;
	@NotNull
	@ManyToOne
	private Vitima vitima;
	@NotNull
	@ManyToOne
	private Criminoso criminoso;
	@NotNull
	@ManyToOne
	private Delegacia delegacia;
	@NotNull
	@ManyToOne
	private Delegado delegado;
	
	private LocalDateTime data = LocalDateTime.now();
	
	public Prisao() {
		
	}

	public Prisao( Optional<Crime> crime, Optional<Delegado> delegado,
			Optional<Policial> policial) {
		this.policial = policial.get();
		this.vitima = crime.get().getVitima();
		this.criminoso = crime.get().getCriminoso();
		this.delegacia = delegado.get().getDelegacia();
		this.delegado = delegado.get();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Policial getPolicial() {
		return policial;
	}

	public void setPolicial(Policial policial) {
		this.policial = policial;
	}

	public Vitima getVitima() {
		return vitima;
	}

	public void setVitima(Vitima vitima) {
		this.vitima = vitima;
	}

	public Criminoso getCriminoso() {
		return criminoso;
	}

	public void setCriminoso(Criminoso criminoso) {
		this.criminoso = criminoso;
	}

	public Delegacia getDelegacia() {
		return delegacia;
	}

	public void setDelegacia(Delegacia delegacia) {
		this.delegacia = delegacia;
	}

	public Delegado getDelegado() {
		return delegado;
	}

	public void setDelegado(Delegado delegado) {
		this.delegado = delegado;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
}
