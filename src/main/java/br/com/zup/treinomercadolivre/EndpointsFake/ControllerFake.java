package br.com.zup.treinomercadolivre.EndpointsFake;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ControllerFake {

    @PostMapping("/notas-fiscais")
    public void cadastrarNota(@RequestBody @Valid Request request) {
        System.out.println("Nota fiscal, id da compra: " + request.getIdCompra() + " id comprador: " + request.getIdComprador());

    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody  Request request) {
        System.out.println("Ranking de compra");
    }
}
