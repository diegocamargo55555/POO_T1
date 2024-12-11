package projeto1.appmitologia.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUser {

    @Test
    public void TestGetuser(){
        User usuario = new User("MOMO", "MONO");
        usuario.setUserName("NHY");
        usuario.setPassword("Fiona Heylon");
        assertEquals("NHY", usuario.getUserName() );
        assertEquals("Fiona Heylon", usuario.getPassword() );
    }
}


