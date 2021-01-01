import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Greenkart {

	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//Fuctional Testing
		

			String[] products = { "Cucumber", "Mushroom", "Beans", "Water Melon" };
	      
			System.setProperty("webdriver.chrome.driver", "E://chromedriver.exe"); // Invokes the chrome browser
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();

			//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);// Implict wait
			WebDriverWait w = new WebDriverWait(driver,5);// Explicit Wait
			driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");// this will hit the url
			System.out.println(driver.getTitle());
			System.out.println(driver.getCurrentUrl());

			addItems(driver, products);
			driver.findElement(By.xpath("//img[@alt='Cart']")).click();
			driver.findElement(By.xpath("//button[contains(text(),'PROCEED')]")).click();
			
			w.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoCode")));
			driver.findElement(By.className("promoCode")).sendKeys("rahulshettyacademy");
			driver.findElement(By.cssSelector(".promoBtn")).click();
			
			w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
			System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
			driver.findElement(By.xpath("//button[text()='Place Order']")).click();
			
			
			//driver.close();
		}
	
	public static void addItems(WebDriver driver, String[] products) 
	{
		List<WebElement> productList = driver.findElements(By.xpath("//h4[@class='product-name']"));
		int j=0;
		for (int i = 0; i < productList.size(); i++) {

			String[] name = productList.get(i).getText().split("-");
			String formattedName = name[0].trim();
			
			
			List productNameList = Arrays.asList(products);
			if (productNameList.contains(formattedName)) {
			
				j++;
				System.out.println(formattedName);
				driver.findElements(By.cssSelector("div.product-action")).get(i).click();
				if(j==products.length)
				{
					break;
				}
				
				
			}
		
		
		}
	}
}
	


