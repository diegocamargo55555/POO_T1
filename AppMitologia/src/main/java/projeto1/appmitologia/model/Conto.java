package projeto1.appmitologia.model;

public class Conto {
    private int id;
    private String descricao;
    private String nome;
    private int ano;
    private String localizacao;
    private int heroiId;
    public Conto(int id, String descricao, String nome, int ano, String localizacao, int heroiId) {
        this.id = id;
        this.descricao = descricao;
        this.nome = nome;
        this.ano = ano;
        this.localizacao = localizacao;
        this.heroiId = heroiId;
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
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    public int getHeroiId() {
        return heroiId;
    }
    public void setHeroiId(int heroiId) {
        this.heroiId = heroiId;
    }

}
