package projeto1.appmitologia.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heroi {
    private int id;
    private String nome;
    private String descricao;
    private String imagem;
    private ImageView imagemFisica;
    public Heroi(){

    }
    public Heroi(String descricao, ImageView imagemFisica, String nome, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagemFisica = imagemFisica;
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

    public ImageView getImagemFisica() {
        return imagemFisica;
    }
    public void setImagemFisica(ImageView imagemFisica) {
        this.imagemFisica = imagemFisica;
    }

    @Override
    public String toString() {
        return nome + " " + descricao;
    }
}
