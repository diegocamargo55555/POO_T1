package projeto1.appmitologia.controller;

import org.junit.Test;
import projeto1.appmitologia.model.Heroi;

import static org.junit.Assert.assertEquals;

public class TestIncluiHeroiController {

    @Test
    public void testIncluiHeroi() {
        IncluiHeroiController atualizaHeroi = new IncluiHeroiController();
        Heroi heroi = new Heroi();
        heroi.setNome("Novo Nome");
        heroi.setDescricao("Nova Descrição");
        heroi.setUrl("nova-imagem.com");

        assertEquals("Novo Nome", heroi.getNome());
        assertEquals("Nova Descrição", heroi.getDescricao());
        assertEquals("nova-imagem.com", heroi.getUrl());
    }
}
