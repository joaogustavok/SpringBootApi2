package desafioapi2.dto.form;

import java.util.Optional;

import desafioapi2.model.Crime;
import desafioapi2.model.Delegado;
import desafioapi2.model.Policial;
import desafioapi2.model.Prisao;
import desafioapi2.repository.CrimeRepository;
import desafioapi2.repository.DelegadoRepository;
import desafioapi2.repository.PolicialRepository;

public class PrisaoForm {

	private Long idCrime;
	private Long idDelegado;
	private Long idPolicial;


	public Long getIdCrime() {
		return idCrime;
	}

	public Long getIdDelegado() {
		return idDelegado;
	}

	public Long getIdPolicial() {
		return idPolicial;
	}

	public Prisao converter(CrimeRepository crimeRepository, DelegadoRepository delegadoRepository,
			PolicialRepository policialRepository) {
		Optional<Crime> crime = crimeRepository.findById(idCrime);
		Optional<Delegado> delegado = delegadoRepository.findById(idDelegado);
		Optional<Policial> policial = policialRepository.findById(idPolicial);
		return new Prisao(crime, delegado, policial);
	}
}
