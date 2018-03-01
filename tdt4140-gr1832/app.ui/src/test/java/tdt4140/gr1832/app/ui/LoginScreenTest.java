package tdt4140.gr1832.app.ui;

import org.junit.Test;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

public class LoginScreenTest extends FxAppTest {
    private final String usernameFieldID = "#username";
    private final String passwordFieldID = "#password";
    private final String loginButtonID = "#loginButton";

    LoginScreenTest() {
        super();
    }

    @Test
    public void textInFields() {
        verifyThat(loginButtonID, hasText("www"));
    }
}
