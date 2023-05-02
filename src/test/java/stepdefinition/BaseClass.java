package stepdefinition;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageobjects.AddCustomerPage;
import pageobjects.LoginPage;
import pageobjects.SearchCustomerPage;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCust;
	public static Logger logger;
	public Properties configprop;
	
	public static String randomestring()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
}
