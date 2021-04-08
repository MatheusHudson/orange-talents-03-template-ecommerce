package br.com.zup.treinomercadolivre.Transacao;

import br.com.zup.treinomercadolivre.Compra.Compra;

import javax.validation.constraints.NotNull;

public class TransacaoRequest {

        @NotNull
        private Long idTransacao;

        @NotNull
        private StatusTransacao status;

        public Long getIdTransacao() {
                return idTransacao;
        }

        public StatusTransacao getStatus() {
                return status;
        }

        public Transacao obterTransacao(Compra compra) {
               return new Transacao(idTransacao, status, compra);
        }

}
