package stepdefinition;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AddCustomerPage;
import pageobjects.LoginPage;
import pageobjects.SearchCustomerPage;

public class Steps extends BaseClass{

	@Before
	public void SetUp() throws IOException
	{
		configprop = new Properties();
		FileInputStream ip = new FileInputStream("config.properties");
		configprop.load(ip);
		
		  //ChromeOptions options = new ChromeOptions();
		  // options.addArguments("--remote-allow-origins=*");
		   logger = Logger.getLogger("nopComemrse");
		   PropertyConfigurator.configure("log4j.properties");
		   
		   String br = configprop.getProperty("browser");
		   if(br.equals("chrome")) 
		   {
		   driver = new ChromeDriver();
		   driver.manage().window().maximize();
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   }
		   else
		   {
			   driver = new FirefoxDriver();
		   }
		   
	}
	
	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {
	  
	   logger.info("**********Launching Browser**********");
	   lp = new LoginPage(driver);
	}

	@When("User open URL {string}")
	public void user_open_url(String URL) {
		logger.info("**********Launching URL**********");
	    driver.get(URL);
	}

	@When("User enters email as {string}")
	public void user_enters_email_as(String email) {
	   logger.info("**********Entering Email**********");
	   lp.EnterEmail(email);
	}

	@When("User enters password as {string}")
	public void user_enters_password_as(String password) {
	   logger.info("**********Entering Password**********");
	   lp.EnterPassword(password);
	}

	@When("User click on login button")
	public void user_click_on_login_button() {
		logger.info("**********Login**********");
	    lp.ClickLoginButton();
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String title) {
		logger.info("**********Veryfing Title**********");
	    Assert.assertEquals(title, driver.getTitle());
	}

	@When("User click on logout button")
	public void user_click_on_logout_button() {
		logger.info("**********Logout**********");
	    lp.ClickLogOutButton();
	}

	@Then("The page title should be {string}")
	public void the_page_title_should_be(String title) {
		logger.info("**********Veryfing Title**********");
		Assert.assertEquals(title, driver.getTitle());
	}

	@Then("Close the browser")
	public void close_the_browser() {
		logger.info("**********Closing Browser**********");
	    driver.quit();
	}
	
	//Add new customers

	@When("User click on customers menu")
	public void user_click_on_customers_menu() {
		addCust = new AddCustomerPage(driver);
		logger.info("**********Click Menu**********");
	    addCust.ClickCustomerMenu();
	}

	@When("User click on sub customers menu")
	public void user_click_on_sub_customers_menu() {
		logger.info("**********Click Customer Menu**********");
	    addCust.ClickCustomerSubMenu();
	}

	@When("User click on Add new button")
	public void user_click_on_add_new_button() {
		logger.info("**********Click Add New Button**********");
	    addCust.ClickAddNewButton();
	}

	@Then("User can view add new customer page")
	public void user_can_view_add_new_customer_page() {
		logger.info("**********Veryfing Title**********");
	    Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("**********Entering Customer Info**********");
		String email = randomestring()+"@gmail.com";
	    addCust.EnterEmail(email);
	    addCust.EnterPassword("admin123");
	    addCust.EnterFName("Amy");
	    addCust.EnterLastName("John");
	    addCust.SetGender("Female");
	    addCust.EnterDateofBirth("01/29/1995");
	    addCust.setCustomerRole("Guests");
	    addCust.setManagerOfVendor("Vendor 1");
	}

	@When("User click on save button")
	public void user_click_on_save_button() throws InterruptedException {
		logger.info("**********Click Save Button**********");
	    addCust.ClickOnSaveButton();
	    Thread.sleep(3000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String message) {
		logger.info("**********Veryfing Confirmation Message**********");
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	}
	
	// Search Customer
	@When("User Enter customer Email")
	public void user_enter_customer_email() {
		logger.info("**********Enter Email To Search**********");
	    searchCust = new SearchCustomerPage(driver);
	    searchCust.EnterEmail("victoria_victoria@nopCommerce.com");
	}

	@When("User click on search button")
	public void user_click_on_search_button() throws InterruptedException {
		logger.info("**********Click Search Button**********");
	    searchCust.ClickSearchButton();
	    Thread.sleep(3000);
	}

	@Then("User should found email in the search table")
	public void user_should_found_email_in_the_search_table() {
		logger.info("**********Veryfing Email Present In The Search Table**********");
		boolean status = searchCust.SearchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}
	
	// Search by name
	@When("User enter First Name")
	public void user_enter_first_name() {
		logger.info("**********Enter Name To Search**********");
		searchCust = new SearchCustomerPage(driver);
		searchCust.EnterFname("Victoria");
	}

	@When("User enter Last Name")
	public void user_enter_last_name() {
		logger.info("**********Enter Name To Search**********");
	    searchCust.EnterLname("Terces");
	}

	@Then("User should found name in the search table")
	public void user_should_found_name_in_the_search_table() {
		logger.info("**********Veryfing Name Present In The Search Table**********");
	    boolean status = searchCust.SearchCustomerByName("Victoria Terces");
	    Assert.assertEquals(true, status);
	}



}
