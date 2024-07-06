package StepDefination;

import org.testng.annotations.Test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchFunction {
	
	WebDriver driver;
	//hooks
	@Before
	public void setup() {
		System.out.println("Ebay SearchFunctionality Check ");
		
	}
	@After
	public void tearDown1() {
		System.out.println("Testing is done succesfully");
		
	}
	@BeforeStep
	public void step() {
		System.out.println("Executing this step");
		
	}
	@AfterStep
	public void exitstep() {
		System.out.println(" successfully execute this step");
		
	}

	@Given("I navigate to the eBay website")
	public void i_navigate_to_the_e_bay_website() {
	   
		 driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
	    driver.get("https://www.ebay.com/");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@When("I search for a laptop")
	public void i_search_for_a_laptop() {
		  WebElement searchBox = driver.findElement(By.id("gh-ac"));
	        searchBox.sendKeys("laptop");
	        searchBox.submit();
	}

	@When("I click on the first laptop in the search results")
	public void i_click_on_the_first_laptop_in_the_search_results() {
		  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));  
		 WebElement cartIcon = driver.findElement(By.xpath("/html/body/div[6]/div[4]/div[3]/div[1]/div[3]/ul/li[3]/div/div[2]/a/div/span"));
	        cartIcon.click();
	    
		 
	}

	@When("I add the laptop to the cart")
	public void i_add_the_laptop_to_the_cart() {
	    // Write code here that turns the phrase above into concrete actions
		Set<String> windowhand=driver.getWindowHandles();
		 Iterator <String> it=windowhand.iterator();
		    String parent=it.next();
		    String child=it.next();
		    driver.switchTo().window(child);
		  WebElement addToCartButton = new FluentWait<>(driver)
		            .withTimeout(Duration.ofSeconds(30))  // Maximum wait time of 30 seconds
		            .pollingEvery(Duration.ofSeconds(5))  // Polling interval of 5 seconds
		            .ignoring(NoSuchElementException.class)
		            .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"binBtn_btn_1\"]")));

		        addToCartButton.click();
		        // Handle pop-up and proceed to checkout as a guest
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		        WebElement checkoutAsGuestButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mainContent\"]/div[1]/div[7]/ul/li[1]/div/div/div/div[2]/div[3]/div/div/div[2]/div[2]/a/span/span")));
		        checkoutAsGuestButton.click();

		       
	}
	 @Then("The laptop should be added to the cart successfully")
	    public void the_laptop_should_be_added_to_the_cart_successfully() throws IOException {
	        // Optionally, verify if the item is added to the cart by checking the cart icon or cart page

	        // Check if the item is in the cart
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        String Expectedtitle="Review item and shipping";
			String  actualTittle= driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/div[1]/section[1]/span/h2")).getText();
			System.out.println( actualTittle);
			Assert.assertEquals(actualTittle, Expectedtitle,"text not matched");
			TakesScreenshot ts = ((TakesScreenshot)driver);
		       File source = ts.getScreenshotAs(OutputType.FILE);
		       File destination = new File("./src/test/resources/cart.png");
		       FileUtils.copyFile(source, destination);
			driver.quit();
			
		    
		    
}
}


