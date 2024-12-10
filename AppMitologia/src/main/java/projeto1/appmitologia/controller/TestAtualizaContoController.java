package projeto1.appmitologia.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAtualizaContoController {
    @Test
    public void testValidarIdValido() {
        AtualizaContoController atualizaConto = new AtualizaContoController();
        int idValido = atualizaConto.validarId("123", "Conto");
        assertEquals(123, idValido);
    }
    @Test
    public void testValidarIdInvalido() {

        AtualizaContoController atualizaConto = new AtualizaContoController();
        int idInvalido = atualizaConto.validarId("a", "Conto");
        assertEquals(-1, idInvalido);
    }
}
