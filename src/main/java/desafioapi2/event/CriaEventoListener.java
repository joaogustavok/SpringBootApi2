package desafioapi2.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class CriaEventoListener implements ApplicationListener<CriaEvento> {

	@Override
	public void onApplicationEvent(CriaEvento criaEvento) {
		HttpServletResponse response = criaEvento.getResponse();
		Long id = criaEvento.getId();
		adiocionarHeaderLocation(response, id);
	}
	private void adiocionarHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
