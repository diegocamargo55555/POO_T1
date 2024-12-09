package projeto1.appmitologia.model;
// refatoração encapsulamento session
public class Session {
    private static String cookie;

    public static String getCookie() {
        return cookie;
    }

    public static void setCookie(String cookie) {
        Session.cookie = cookie;
    }
}
