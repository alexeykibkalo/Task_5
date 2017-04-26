package myprojects.automation.assignment5;

import io.selendroid.common.SelendroidCapabilities;
import myprojects.automation.assignment5.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseTest extends DriverFactory {
    protected RemoteWebDriver driver;
    protected GeneralActions actions;
    public boolean isMobileTesting;

    /**
     * Prepares {@link WebDriver} instance with timeout and browser window configurations.
     *
     * Driver type is based on passed parameters to the automation project,
     * creates {@link ChromeDriver} instance by default.
     *
     */
    @BeforeClass
    @Parameters({"selenium.browser", "selenium.grid"})
    public void setUp(@Optional("chrome") String browser, @Optional("") String gridUrl) {
        // TODO create WebDriver instance according to passed parameters
        //driver = new EventFiringWebDriver(DriverFactory.initDriver(browser));
        //  driver.register(new EventHandler());
        switch (browser) {

            case "chrome":

            try {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
            } catch (Exception e) {
            }

            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            // unable to maximize window in mobile mode
            if (!isMobileTesting(browser))
                driver.manage().window().maximize();

            isMobileTesting = isMobileTesting(browser);

            actions = new GeneralActions(driver);
            break;

            case "firefox":

                try {
                    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
                } catch (Exception e) {
                }

                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                // unable to maximize window in mobile mode
                if (!isMobileTesting(browser))
                    driver.manage().window().maximize();

                isMobileTesting = isMobileTesting(browser);

                actions = new GeneralActions(driver);
                break;

            case "ie":

                try {
                    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                    capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
                } catch (Exception e) {
                }

                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                // unable to maximize window in mobile mode
                if (!isMobileTesting(browser))
                    driver.manage().window().maximize();

                isMobileTesting = isMobileTesting(browser);

                actions = new GeneralActions(driver);
                break;


            case "phantomjs":

                try {
                    DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
                    driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
                } catch (Exception e) {
                }

                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                // unable to maximize window in mobile mode
                if (!isMobileTesting(browser))
                    driver.manage().window().maximize();

                isMobileTesting = isMobileTesting(browser);

                actions = new GeneralActions(driver);
                break;

            case "android":

                try {
                    DesiredCapabilities capabilities = DesiredCapabilities.android();
                    driver = new RemoteWebDriver(capabilities);
                } catch (Exception e) {
                }

                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                // unable to maximize window in mobile mode
                if (!isMobileTesting(browser))
                    driver.manage().window().maximize();

                isMobileTesting = isMobileTesting(browser);

                actions = new GeneralActions(driver);
                break;



        }
    }

    /**
     * Closes driver instance after test class execution.
     */
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     *
     * @return Whether required browser displays content in mobile mode.
     */
    private boolean isMobileTesting(String browser) {
        switch (browser) {
            case "android":
                return true;
            case "firefox":
            case "ie":
            case "internet explorer":
            case "chrome":
            case "phantomjs":
            default:
                return false;
        }
    }
}
