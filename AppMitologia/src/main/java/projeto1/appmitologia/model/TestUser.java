package projeto1.appmitologia.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUser {

    @Test
    public void TestGetuser(){
        User usuario = new User("IHWA", "YEON");
        assertEquals("IHWA", usuario.getUserName());
        usuario.setPassword("kasha");
        assertEquals("kasha", usuario.getPassword());
    }
}


