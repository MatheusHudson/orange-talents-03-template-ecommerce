package br.com.zup.treinomercadolivre.OpiniaoProduto;

import java.util.Set;

public class OpiniaoCalculo {

    public  Double calcularMedia(Set<OpiniaoProduto> opiniaoProduto) {
        return opiniaoProduto.stream().mapToDouble(opiniao -> opiniao.getNota()).average().orElse(0.0);
    }

    public Long contarAvaliacoes(Set<OpiniaoProduto> opiniaoProduto) {
      return opiniaoProduto.stream().mapToInt(opiniao -> opiniao.getNota()).count();
    }
}
