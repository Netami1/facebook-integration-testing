package api.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class AbstractWebDriver {

    @Getter
    private WebDriver webDriver;
    private DesiredCapabilities capabilities;
    @Getter
    private WebDriverWait webDriverWait;

    public void setup() {
        WebDriverManager.phantomjs().setup();

        setLoggingPrefs();

        webDriver = new PhantomJSDriver(capabilities);

        webDriverWait = new WebDriverWait(webDriver, 20);

    }

    public boolean openUrl(String url) {
        webDriver.get(url);

        return webDriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) webDriver).executeScript(
                        "return document.readyState"
                ).equals("complete");
            }
        });
    }

    @SneakyThrows
    public void tearDown(boolean screenshotFirst) {
        if (screenshotFirst) {
            File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + new SimpleDateFormat("yyyyMMddHHmm'.png'").format(new Date())));
        }

        webDriver.close();
    }

    private void setLoggingPrefs() {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    }
}
