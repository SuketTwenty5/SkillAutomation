package t5.ipe.cucumber.objects;

public class IpeUserRepository {

    private static final IpeUser user = new IpeUser();

    static {
        String userLogin = System.getProperty("user.login");
        String userPassword = System.getProperty("user.password");

        user.setLogin(userLogin);
        user.setPassword(userPassword);
    }


    public static IpeUser getUserInfo() {
        return user;
    }


}
