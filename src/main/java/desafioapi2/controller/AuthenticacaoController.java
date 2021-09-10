package desafioapi2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafioapi2.dto.TokenDto;
import desafioapi2.dto.form.LoginForm;
import desafioapi2.email.EmailService;
import desafioapi2.model.Usuario;
import desafioapi2.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticacaoController {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping
	public ResponseEntity<TokenDto> autenticarTest(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();

		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			Usuario usuario = (Usuario) authentication.getPrincipal();
			
			if(usuario.getPerfil().getNome().equals("ADVOGADO")) {
				emailService.enviaEmail(authentication);
				return ResponseEntity.ok(new TokenDto(token, "Bearer") );
			}
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	

}
