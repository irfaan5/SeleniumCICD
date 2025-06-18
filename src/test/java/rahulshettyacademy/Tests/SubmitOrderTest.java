package rahulshettyacademy.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException {
//		String productName="IPHONE productName13 PRO";
		String countryName = "India";

		System.out.println("email / password - " + input.get("email") + " / " + input.get("password"));

		System.out.println("ProductName - " + input.get("product"));

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
//		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		String getMessage = confirmationPage.getConfirmationMsg();
		AssertJUnit.assertTrue(getMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods =  {"submitOrder"})
	public void OrderHistory() throws InterruptedException {
		// To Verify "IPHONE" is displaying in Orders Page History
		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication("login2@gmail.com", "Login@123");
		OrderPage orderPage = productCatalogue.goToOrderPage();

		Boolean match = orderPage.verifyDesireProduct(productName);
		Assert.assertTrue(match);

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}
//
//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> hm1=new HashMap<String, String>();
//		hm1.put("email","login@gmail.com");
//		hm1.put("password","login");
//		hm1.put("product","ADIDAS ORIGINAL");
//		
//		HashMap<String,String> hm2=new HashMap<String,String>();
//		hm2.put("email","maven.abc@gmail.com");
//		hm2.put("password","maven");
//		hm2.put("product","IPHONE 13 PRO");
//		
//		HashMap<String,String> hm3=new HashMap<String,String>();
//		hm3.put("email","loginrahul@gmail.com");
//		hm3.put("password","loginrahul");
//		hm3.put("product","ZARA COAT 3");
//		
//		return new Object[][] {{hm1},{hm2},{hm3}};

//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"login2@gmail.com","Login@123","ADIDAS ORIGINAL"},
//							   {"maven.abc@gmail.com","maven","IPHONE 13 PRO"},
//							   {"loginrahul@gmail.com","loginrahul","ZARA COAT 3"}
// update test							  };

}
