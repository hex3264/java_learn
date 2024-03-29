package ru.stqa.java_learn.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private RegistrationHelper registrationHelper;
    private final Properties properties;
    private WebDriver wd;
    private SoapHelper soapHelper;


    private String browser;

    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private AuthorizationHelper authorizationHelper;
    private NavigationHelper navigationHelper;
    private UserHelper userHelper;
    private DbHelper dbHelper;
    private SetPasswordHelper setPasswordHelper;

    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");

        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));


    }

    public void stop() {
        wd.quit();
        if (wd != null) {
            wd.quit();
        }
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }


    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            }
            wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public DbHelper db() {
        if (dbHelper == null) {
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public AuthorizationHelper authorization() {
        if (authorizationHelper == null) {
            authorizationHelper = new AuthorizationHelper(this);
        }
        return authorizationHelper;
    }

    public UserHelper user() {
        if (userHelper == null) {
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public NavigationHelper goTo() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public SetPasswordHelper setPassword() {
        if (setPasswordHelper == null) {
            setPasswordHelper = new SetPasswordHelper(this);
        }
        return setPasswordHelper;
    }

    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}