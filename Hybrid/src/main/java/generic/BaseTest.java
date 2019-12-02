package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public abstract class BaseTest implements IAutoConst {
	
public WebDriver driver;
	
	static {
		System.setProperty(CHROME_KEY,CHROME_VALUE);
		System.setProperty(GECKO_KEY,GECKO_VALUE);
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void openApp(String browser)
	{
		String appURL = TestUtility.getProperty(CONFIG_PATH,"URL");
		String strITO = TestUtility.getProperty(CONFIG_PATH,"ITO");
		long ITO = Long.parseLong(strITO);
		
		if(browser.equalsIgnoreCase("Chrome")) {
		driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			driver=new FirefoxDriver();
		}
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void closeApp(ITestResult testResult) {
		String name=testResult.getName();
		int status=testResult.getStatus();
		if(status==1) {
			Reporter.log(name+" is PASSED",true);
		}
		else {
			Reporter.log(name+" is FAILED",true);
			TestUtility.getPhoto(driver, IMG_PATH, name);
		}
				
		driver.close();
	}

}
