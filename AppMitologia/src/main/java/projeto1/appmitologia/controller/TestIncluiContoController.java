package projeto1.appmitologia.controller;

import org.junit.Test;
import projeto1.appmitologia.model.Conto;

import static org.junit.Assert.assertEquals;

public class TestIncluiContoController {
    @Test
    public void testIncluiConto() {
        IncluiContoController atualizaHeroi = new IncluiContoController();
        Conto conto = new Conto();
        conto.setNome("Novo Nome");
        conto.setDescricao("Nova Descrição");
        conto.setNomeHeroi("Novo NomeHeroi");
        conto.setLocalizacao("Novo Localizacao");

        assertEquals("Novo Nome", conto.getNome());
        assertEquals("Nova Descrição", conto.getDescricao());
        assertEquals("Novo NomeHeroi", conto.getNomeHeroi());
        assertEquals("Novo Localizacao", conto.getLocalizacao());
    }
}
