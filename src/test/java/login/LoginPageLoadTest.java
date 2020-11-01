package login;

import api.FacebookTest;
import api.data.Constants;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginPageLoadTest extends FacebookTest {

    @Before
    public void setup() {
        getAbstractWebDriver().openUrl(Constants.FACEBOOK_URL);

        setTakeScreenshotOnEnd(true);
    }

    @Test
    public void pageLoadsTitle() {
        assertTrue(getAbstractWebDriver().getWebDriver().getTitle().contains("Facebook"));
    }

    @Test
    public void loginButtonPresent() {
        assertNotNull(getAbstractWebDriver().getWebDriver().findElement(By.xpath("//button[@type='submit']")));
    }
}
