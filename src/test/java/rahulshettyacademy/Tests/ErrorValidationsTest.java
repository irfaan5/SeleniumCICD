package rahulshettyacademy.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.testng.Assert;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups="ErrorHandling", retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
							
		landingPage.loginApplication("login2@gmail.com", "Login@12");
		String errorMessage=landingPage.getErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
		
		}	
	
	
	@Test(groups="ErrorHandling")
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName="ZARA COAT 3";
		String wrongProductName="NIKE";
//		String countryName="India";
					
		ProductCatalogue productCatalogue =landingPage.loginApplication("login2@gmail.com", "Login@123");
				
		productCatalogue.addProductToCart(productName);				
		CartPage cartPage=productCatalogue.goToCartPage();	
		
		Boolean match=cartPage.VerifyProductDisplay(wrongProductName);
		Assert.assertFalse(match);
		
	
		
		}
	
	}

