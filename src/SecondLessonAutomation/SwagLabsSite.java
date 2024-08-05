package SecondLessonAutomation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SwagLabsSite {

	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com");
	}

	@Test(priority = 1)
	public void login() throws InterruptedException {
		String userName = "user-name";
		String password = "password";

		WebElement userNameFeild = driver.findElement(By.id(userName));
		WebElement passwordFeild = driver.findElement(By.id(password));
		WebElement loginButton = driver.findElement(By.id("login-button"));

		userNameFeild.sendKeys("standard_user");
		passwordFeild.sendKeys("secret_sauce");

		Thread.sleep(500);

		loginButton.click();
	}

	@Test(priority = 2)
	public void addItemsToCart() throws InterruptedException {
		Thread.sleep(1000);

		List<WebElement> addItemButtons = driver
				.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));

		System.out.println("Number of buttons found: " + addItemButtons.size());

//		AddItemButton.forEach(button -> System.out.println(button.getText()));
//		AddItemButton.get(0).click();

		for (int i = 0; i < addItemButtons.size(); i++) {
			addItemButtons.get(i).click();
			Thread.sleep(500);
		}
	}

	@Test(priority = 3)
	private void checkout() throws InterruptedException {

		WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
		cartIcon.click();
		Thread.sleep(1000);

		WebElement checkoutButton = driver.findElement(By.id("checkout"));
		checkoutButton.click();
		Thread.sleep(1000);

		WebElement firstNameFeild = driver.findElement(By.id("first-name"));
		WebElement lastNameFeild = driver.findElement(By.id("last-name"));
		WebElement postalCodeFeild = driver.findElement(By.id("postal-code"));
		WebElement continueButton = driver.findElement(By.id("continue"));
		firstNameFeild.sendKeys("Omar");
		Thread.sleep(1000);
		lastNameFeild.sendKeys("Abu Snineh");
		Thread.sleep(1000);
		postalCodeFeild.sendKeys("13710");
		Thread.sleep(1000);
		continueButton.click();
		Thread.sleep(1000);

		WebElement finishButton = driver.findElement(By.id("finish"));
		finishButton.click();
		Thread.sleep(1000);

		WebElement backHomeButton = driver.findElement(By.id("back-to-products"));
		backHomeButton.click();
		Thread.sleep(1000);
	}

	@Test(priority = 4)
	public void addItemsFromCardPage() throws InterruptedException {

		List<WebElement> itemNames = driver.findElements(By.className("inventory_item_name"));
		System.out.println("the No size is " + itemNames.size());

		for (int i = 0; i < itemNames.size(); i++) {
			WebElement itemName = itemNames.get(i);/* The Best Line :) */
			itemName.click();
			Thread.sleep(500);

			WebElement addToCartButton = driver.findElement(By.id("add-to-cart"));
			addToCartButton.click();
			Thread.sleep(500);

			backPage();
			Thread.sleep(500);

			// Refresh the item names list after navigating back
			itemNames = driver.findElements(By.className("inventory_item_name"));

		}

//		Wrong Loop Because the itemName Still directly executed .click method after navigate to backPage
//		for (WebElement itemName : itemNames) {
//			itemName.click();/*The Bad Line :( */
//			Thread.sleep(1000);
//			
//			WebElement addToCartButton = driver.findElement(By.id("add-to-cart"));
//			addToCartButton.click();
//			Thread.sleep(1000);
//			
//			backPage();
//			Thread.sleep(1000);
//			
//		    Refresh the item names list after navigating back
//			itemNames= driver.findElements(By.className("inventory_item_name"));
//		}

	}

	@Test(priority = 5)
	public void removeAllItemsFromCart() throws InterruptedException {
//		addItemsToCart();
		WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
		cartIcon.click();

		List<WebElement> RemoveButton = driver.findElements(By.cssSelector(".btn.btn_secondary.btn_small.cart_button"));

//		System.out.println("the No of items equal = " + RemoveButton.size());

		for (WebElement button : RemoveButton) {
			button.click();
			Thread.sleep(500);
		}
		backPage();
	}

	public void backPage() {
		driver.navigate().back();
	}

	@Test(priority = 6)
	public void logout() throws InterruptedException {
		WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
		WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
		menuButton.click();

		Thread.sleep(1000);
		logoutButton.click();
	}
}
