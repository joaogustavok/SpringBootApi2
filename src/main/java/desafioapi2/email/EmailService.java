package desafioapi2.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import desafioapi2.model.Usuario;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public String enviaEmail(Authentication authentication) {
		SimpleMailMessage message = new SimpleMailMessage();
		Usuario logado = (Usuario) authentication.getPrincipal();
		message.setSubject("Spring Api");
		message.setText("Voce Entrou na Aplicacao; Seu Login Foi um Sucesso :D ");
		message.setTo(logado.getEmail());
		message.setFrom("<jgkworkstudio@gmail.com>");

		try {
			mailSender.send(message);
			return "Email enviado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao enviar email.";
		}
	}
}
