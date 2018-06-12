import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selenium2Example  {

	static WebDriver driver ;
	static WebDriverWait wd;  


	@BeforeClass
	public static void setupDriver() throws IOException, InterruptedException  {
	
		WebDriverManager.chromedriver().setup();
	    // Create a new instance of the Chrome driver
	    // Notice that the remainder of the code relies on the interface, 
	    // not the implementation.
	    WebDriver driver = new ChromeDriver();
	    
		System.out.println("Opening URL"); 
		driver.get("http://hardwired-dev-ed.my.salesforce.com");
		
		wd= new WebDriverWait(driver, 30); 
		wd.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']"))); 

		// Enter username in textbox 
		System.out.println("Enter username in textbox"); 
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("xxxx" ); 

		//Enter Password in texbox 
		System.out.println("Enter Password in texbox"); 
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("xxx");

		//Click on Login button 
		System.out.println("Click on Login button"); 
		driver.findElement(By.xpath("//input[@id='Login']")).click(); 
		Thread.sleep(2000); 
		System.out.println("Login Sucessfully");
		
	}

	@Before
	public void loginSalesforce() {
	
	}
	
	@Test
    public void sayCheeseTest() {
	

        // And now use this to visit Google
        driver.get("http://www.google.com");
        
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        
    }
    
    @AfterClass
    public static void closeDriver() {
        //Close the browser
        driver.quit();
        
        System.out.println("closing driver");
    }
}