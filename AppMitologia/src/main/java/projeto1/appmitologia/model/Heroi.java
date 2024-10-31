package projeto1.appmitologia.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heroi {
    private int id;
    private String nome;
    private String descricao;
    private String url;
    private ImageView imagem;
    public Heroi(){

    }
    public Heroi(String descricao, ImageView imagem, String nome, String url) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;

    }

    public ImageView getImagem() {
        return imagem;
    }
    public void setImagemFisica(ImageView imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return nome + " " + descricao;
    }
}
