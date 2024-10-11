package projeto1.appmitologia.model;

public class Heroi {
    private int id;
    private String nome;
    private String descricao;
    public Heroi(String nome, int id, String descricao) {
        this.nome = nome;
        this.id = id;
        this.descricao = descricao;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
    public void setDescricaoHeroi(String descricao) {
        this.descricao = descricao;
    }
}
