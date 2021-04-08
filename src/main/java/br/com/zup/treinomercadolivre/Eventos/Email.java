package br.com.zup.treinomercadolivre.Eventos;

public class Email {

    private String para;
    private String de;
    private String assunto;
    private String corpo;

    public Email(String para, String de, String assunto, String corpo) {
        this.para = para;
        this.de = de;
        this.assunto = assunto;
        this.corpo = corpo;
    }

    public String getPara() {
        return para;
    }

    public String getDe() {
        return de;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getCorpo() {
        return corpo;
    }
}
