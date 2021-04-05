package br.com.zup.treinomercadolivre.Produto;

import br.com.zup.treinomercadolivre.Categoria.Categoria;
import br.com.zup.treinomercadolivre.OpiniaoProduto.OpiniaoProduto;
import br.com.zup.treinomercadolivre.Pergunta.Pergunta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

public interface Repository extends CrudRepository<Produto, Long> {

    @Query("Select o From OpiniaoProduto o Where o.produto.id = :id")
    Set<OpiniaoProduto> opiniaoProduto(Long id);

    @Query("Select p From Pergunta p Where p.produto.id = :id")
    Set<Pergunta> perguntas(Long id);

    @Query("Select c From Categoria c Where c.id = :id")
    Categoria categoria(Long id);


}
