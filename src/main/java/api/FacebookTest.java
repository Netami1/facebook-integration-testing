package api;

import api.selenium.AbstractWebDriver;
import lombok.Getter;
import lombok.Setter;
import org.junit.After;

public abstract class FacebookTest {

    @Getter
    private final AbstractWebDriver abstractWebDriver;

    @Setter
    private boolean takeScreenshotOnEnd = false;

    public FacebookTest() {
        abstractWebDriver = new AbstractWebDriver();
        abstractWebDriver.setup();
    }

    @After
    public void tearDown() {
        abstractWebDriver.tearDown(takeScreenshotOnEnd);
    }
}
