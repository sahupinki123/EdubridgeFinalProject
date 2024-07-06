package StepDefination;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SubNav {
	WebDriver driver;
	//hooks
	@Before
	public void setup() {
		System.out.println("Ebay SubNnav Check ");
		
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
		//
	}
	@Given("I am on the eBay homepage")
	public void i_am_on_the_e_bay_homepage() {
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
	    driver.get("https://www.ebay.com/");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@When("I navigate to Fashion than Footwear after that Women's Shoes\"")
	public void i_navigate_to_fashion_than_footwear_after_that_women_s_shoes() {
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		 Actions a = new Actions(driver);
		 a.moveToElement(driver.findElement(By.xpath("//*[@id=\"vl-flyout-nav\"]/ul/li[4]/a"))).build().perform();
		 driver.findElement(By.xpath("//*[@id=\"vl-flyout-nav\"]/ul/li[4]/div[2]/div[1]/nav[1]/ul/li[1]/a")).click();
		 driver.findElement(By.xpath("//*[@id=\"s0-17-12-0-1[0]-0-0\"]/ul/li[2]/a")).click();
		 
		 
	}
	@Then("I should see women's shoes listed")
	public void i_should_see_women_s_shoes_listed() throws IOException {
		String Expectedtitle="Women's Shoes";
		String  actualTittle= driver.findElement(By.xpath("/html/body/div[3]/div[2]/h1/span")).getText();
		System.out.println( actualTittle);
		Assert.assertEquals(actualTittle, Expectedtitle,"text not matched");
		TakesScreenshot ts = ((TakesScreenshot)driver);
	       File source = ts.getScreenshotAs(OutputType.FILE);
	       File destination = new File("./src/test/resources/shoes.png");
	       FileUtils.copyFile(source, destination);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.close();
	   
	}
}
