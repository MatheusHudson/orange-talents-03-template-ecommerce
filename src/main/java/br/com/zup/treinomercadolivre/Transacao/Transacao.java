package br.com.zup.treinomercadolivre.Transacao;

import br.com.zup.treinomercadolivre.Compra.Compra;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idTransacao;

    private StatusTransacao statusTransacao;

    @CreationTimestamp
    private LocalDateTime instante = LocalDateTime.now();

    @ManyToOne
    private Compra compra;

    @Deprecated
    public Transacao () {

    }

    public Transacao(Long idTransacao, StatusTransacao statusTransacao, Compra compra) {
        this.idTransacao = idTransacao;
        this.statusTransacao = statusTransacao;
        this.compra = compra;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }
}
