package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	
	@FindBy(css=".cartSection h3")
	List <WebElement> cartProducts;
	
	@FindBy(css=".subtotal button")
	WebElement checkoutPage;
	
	public Boolean VerifyProductDisplay(String productName) {
		
		System.out.println("VerifyProductDisplay : "+productName);
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		System.out.println("Match : "+match);
		return match;
	}
	
	

	public CheckoutPage goToCheckoutPage() {
		
		checkoutPage.click();
		return new CheckoutPage(driver);
	}
	


}
