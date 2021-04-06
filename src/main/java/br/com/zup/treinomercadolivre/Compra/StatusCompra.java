package br.com.zup.treinomercadolivre.Compra;

public enum StatusCompra {

    INICIADA("Iniciada"),
    CONCLUIDA("Concluida");

    private String descricao;

    StatusCompra(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
