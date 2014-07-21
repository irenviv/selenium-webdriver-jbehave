package com.rozetka.main;

import java.util.concurrent.TimeUnit;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Iryna_Vintonyk
 *
 */

public class BaseWebdriverActions {
	
	protected WebDriver driver;
		
	@BeforeScenario
    public void setUp() throws Exception {
		driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();    
	}

    @AfterScenario
    public void tearDown() throws Exception {
    	driver.manage().deleteAllCookies();
        if (driver != null)
            driver.quit();
    }
    
}
