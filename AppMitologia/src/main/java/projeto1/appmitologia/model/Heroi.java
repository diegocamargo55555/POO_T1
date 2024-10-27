package projeto1.appmitologia.model;

public class Heroi {
    private int id;
    private String nome;
    private String descricao;
    private String imagem;
    public Heroi(){

    }
    public Heroi(String descricao, String imagem, String nome) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
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
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
