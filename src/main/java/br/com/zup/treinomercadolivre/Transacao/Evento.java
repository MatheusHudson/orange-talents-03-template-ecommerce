package br.com.zup.treinomercadolivre.Transacao;

import br.com.zup.treinomercadolivre.Compra.Compra;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class Evento {

    public void processaNota(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getUsuario().getId());

        HttpEntity<Map> entity = new HttpEntity<Map>(request, retornarHeader());
        restTemplate.postForEntity("http://127.0.0.1:8080/notas-fiscais", entity, String.class);
    }

    public void processaRanking(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getUsuario().getId());

        HttpEntity<Map> entity = new HttpEntity<Map>(request, retornarHeader());
        restTemplate.postForEntity("http://127.0.0.1:8080/ranking", entity, String.class);
    }


    public HttpHeaders retornarHeader() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJEZXNhZmlvIGpvcm5hZGEgZGV2IGVmaWNpZW50ZSBtZXJjYWRvIGxpdnJlIiwic3ViIjoibWF0aGV1c0B0ZXN0ZS5jb20iLCJpYXQiOjE2MTc5MDE3OTQsImV4cCI6MTYxNzk4ODE5NH0.IrDnolALKXGxJJ8WIqTytul1yK2tcc7eq2OZpR5Sztc";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        return headers;
    }

}
