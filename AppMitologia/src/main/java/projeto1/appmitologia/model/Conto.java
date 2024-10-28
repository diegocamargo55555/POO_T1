package projeto1.appmitologia.model;

public class Conto {
    int id;
    private String descricao;
    private String nome;
    private String localizacao;
    private String nomeHeroi;
    public Conto() {

    }
    public Conto(String descricao, String nome, String localizacao, String nomeHeroi) {
        this.descricao = descricao;
        this.nome = nome;
        this.localizacao = localizacao;
        this.nomeHeroi = nomeHeroi;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    public String getNomeHeroi() {
        return nomeHeroi;
    }
    public void setNomeHeroi(String nomeHeroi) {
        this.nomeHeroi = nomeHeroi;
    }
    @Override
    public String toString() {
        return nome + " " + localizacao + " " + descricao + " " + nomeHeroi;
    }
}
