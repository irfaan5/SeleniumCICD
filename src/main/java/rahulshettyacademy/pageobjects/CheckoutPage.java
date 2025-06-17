package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By dropDownBy=By.cssSelector(".ta-results");	
	By submitBy=By.cssSelector(".action__submit");
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	@FindBy(css=".ta-results button:nth-of-type(2)")
	WebElement selectCountry;	

	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	
	public void selectCountry(String countryName) {
		
		Actions a= new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(dropDownBy);		
		selectCountry.click();
		
		
	}
	
	public ConfirmationPage placeOrder() {
		
		waitForElementToAppear(submitBy);		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", placeOrder);
		ConfirmationPage confirmationPage=new ConfirmationPage(driver);
		return confirmationPage;
	}
	
		
}
