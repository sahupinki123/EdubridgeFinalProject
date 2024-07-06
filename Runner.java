package Runner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


	@CucumberOptions(
			features= {"E:\\Automation Testing\\eclipse backup\\Ebay\\src\\test\\resources\\feature1.feature","E:\\Automation Testing\\eclipse backup\\Ebay\\src\\test\\resources\\feature2.feature"},
			glue= {"StepDefination"},
			plugin= {"pretty","html:target/newjavarun.html"}
			)
			public class Runner extends AbstractTestNGCucumberTests  {
			  
			}

