package projeto1.appmitologia.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestSession {




    @Test
    public void TestGetuser(){
        User usuario = new User("beastars", "LOUIS");
        assertNull(Session.getCookie());
        usuario.setUserName("Livia Priscilla");
        usuario.setPassword("Abel Heylon");
        Session.setCookie(usuario.getUserName());
        assertEquals(Session.getCookie(), usuario.getUserName());
        assertSame(Session.getCookie(), usuario.getUserName());
    }
}


