package script;

import org.testng.annotations.Test;

import generic.BaseTest;
import generic.TestUtility;
import pages.EnterPage;
import pages.LoginPage;

public class ValidLogin extends BaseTest {
	@Test
	public void testValidLogin() {
		String un=TestUtility.getValue(XL_PATH,"ValidLogin",1,0);
		String pw=TestUtility.getValue(XL_PATH, "ValidLogin",1,1);
		String eTitle=TestUtility.getValue(XL_PATH,"ValidLogin",1,2);
		LoginPage l=new LoginPage(driver);
		//Enter Valid UN
		l.setUserName(un);
		//Enter Valid pw
		l.setPassword(pw);
		TestUtility.getPhoto(driver, IMG_PATH, eTitle);
		//Click login
		l.clickLogin();
		//verify home page....
		EnterPage e=new EnterPage(driver);
		e.verifyHomePageIsDisplayed(driver, eTitle);
		TestUtility.getPhoto(driver, IMG_PATH, eTitle);
	}
}
