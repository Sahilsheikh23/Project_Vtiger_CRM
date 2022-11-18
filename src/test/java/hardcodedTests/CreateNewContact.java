package hardcodedTests;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewContact {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement logo = driver.findElement(By.xpath("//img[@alt=\"logo\"]"));
		if(logo.isDisplayed()) {
			System.out.println("Pass : Login Page is Displayed ");
		}else {
			System.out.println("Fail : Login Page is not Displayed ");
		}
		if(logo.isDisplayed()) {
			System.out.println("Pass : Login Page is Displayed ");
		}else {
			System.out.println("Fail : Login Page is not Displayed ");
		}
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("sahil");
		driver.findElement(By.id("submitButton")).click();
		String homePageTitle = driver.getTitle();
		if(homePageTitle.contains("Home")) {
			System.out.println("Pass : Home Page is Displayed ");
		}else {
			System.out.println("Fail : Home Page not found ");
		}
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		String contactsPageHeader = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		if(contactsPageHeader.contains("Contacts")) {
			System.out.println("Pass : Contacts Page is Displayed");
		}else {
			System.out.println("Fail : Contacts Page not found");
		}
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String createNewContactHeader = driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();
		if (createNewContactHeader.contains("Creating New Contact")) {
			System.out.println("Pass : Create New Contact Page is Displayed");
		}else {
			System.out.println("Fail : Create New Contact Page is not found");
		}
		WebElement firtNameDropdown = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		Select firstName = new Select(firtNameDropdown);
		firstName.selectByVisibleText("Mr.");
		driver.findElement(By.name("firstname")).sendKeys("sahil");
		driver.findElement(By.name("lastname")).sendKeys("sheikh");
		driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();
		//WebElement existingOrganization = driver.findElement(By.xpath("(//table[@class='small'])[3]/tbody/tr[last()]/td[1]"));
		//if(existingOrganization.getText().contains("XYZ")) {
		
		String parentWindow = driver.getWindowHandle();
		Set<String> childWindow = driver.getWindowHandles();
		for(String win : childWindow) {
		driver.switchTo().window(win);}
		String organizationsListPage = driver.findElement(By.xpath("//td[@class='moduleName']")).getText();
		if(organizationsListPage.contains("Organizations")) {
			System.out.println("Pass : Organization List Page is Displayed");
		}else {
			System.out.println("Fail : Organization List Page not found ");
		}
		driver.findElement(By.xpath("//a[text()='XYZ']")).click();
		driver.switchTo().window(parentWindow);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
		driver.findElement(By.xpath("//input[@name='imagename']")).sendKeys("C:\\vtiger-crm-logo.gif");
		String contactImage = driver.findElement(By.xpath("(//table[@class='small'])[4]/tbody/tr[28]/td[2]")).getText();
		if(contactImage.contains("No file chosen")) {
			System.out.println("Fail : No Image Uploaded ");
		}else {
			System.out.println("Pass : Image Uploaded");
		}
			driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
			String newContactInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(newContactInfoHeader.contains("Contact Information")) {
				System.out.println("Pass : New Contact Info Page is Displayed");
				}else {
					System.out.println("Fail: New Contact Info Page not found");
				}
			driver.findElement(By.xpath("(//a[text()='Contacts'])[2]")).click();
			String contactListHeader = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
			if(contactListHeader.contains("Contacts")) {
				System.out.println("Pass : Contact List Page is Displayed");
			}else {
				System.out.println("Fail : Contact List Page not found");
			}
			WebElement newContact = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[4]"));
			if(newContact.getText().contains("sheikh")) {
				System.out.println("Pass : Test Case is Passed");
			}else {
				
				System.out.println("Fail : Test Case is Failed");
			}
			WebElement administratorImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions a = new Actions(driver);
			
			a.moveToElement(administratorImage).perform();
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			driver.close();
			
			
			
		}
		}
		
			
		





