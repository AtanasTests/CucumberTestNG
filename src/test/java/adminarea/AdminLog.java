package adminarea;

import core.BaseTest;
import org.testng.annotations.Test;

public class AdminLog extends BaseTest {

    @Test
    public void adminLog(){
        AdminLogin.login("admin", "parola123!");

    }
}
