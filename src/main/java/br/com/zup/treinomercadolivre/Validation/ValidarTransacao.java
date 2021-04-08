package br.com.zup.treinomercadolivre.Validation;

import br.com.zup.treinomercadolivre.Transacao.StatusTransacao;
import br.com.zup.treinomercadolivre.Transacao.Transacao;
import br.com.zup.treinomercadolivre.Transacao.TransacaoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
public class ValidarTransacao implements Validator {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean supports(Class<?> aClass) {
        return TransacaoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        TransacaoRequest request = (TransacaoRequest) o;
        Boolean existIdTransacao = em.createQuery("Select case when (count(t) > 0 ) then true else false end" +
                " From Transacao t Where t.idTransacao = :id", Boolean.class)
                .setParameter("id",request.getIdTransacao()).getSingleResult();

        if(!existIdTransacao) {
            return;
        }

        Transacao transacao = em.createQuery("Select t From Transacao t Where t.idTransacao = :id", Transacao.class)
                .setParameter("id", request.getIdTransacao()).getSingleResult();

        if(transacao.getStatusTransacao() == StatusTransacao.SUCESSO ) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Está transação já foi processada com sucesso!");
        }



    }
}
