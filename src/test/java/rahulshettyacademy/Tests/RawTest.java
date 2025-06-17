package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RawTest {

	public static void main(String[] args) {
		String productName="ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup(); 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		//login@gmail.com login
		//Login Page
		driver.findElement(By.id("userEmail")).sendKeys("login@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("login");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		System.out.println(products.size());
		
		WebElement prod =products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		//  .card-body button:last-child             .card-body button:last-of-type
		prod.findElement(By.cssSelector(".card-body button:nth-child(4)")).click();			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));				
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-results button:nth-of-type(2)")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
//		driver.findElement(By.cssSelector(".action__submit")).click();
//		driver.findElement(By.linkText("Place Order ")).click();
		
		WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
        JavascriptExecutor js = (JavascriptExecutor) driver;        
        js.executeScript("arguments[0].click();", placeOrder);
		
		String fetchText =driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(fetchText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
		
		}
		

	}

