package hardcodedTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewLead {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement logo = driver.findElement(By.xpath("//img[@alt=\"logo\"]"));
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
		driver.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		String leadsPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(leadsPageHeader.contains("Creating New Lead")) {
			System.out.println("Pass: Creating New Lead Page is Displayed");
		}else {
			System.out.println("Fail : Creating New Lead Page not found");
		}
		
		WebElement firtNameDropdown = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		Select firstName = new Select(firtNameDropdown);
		firstName.selectByVisibleText("Mr.");
		driver.findElement(By.name("firstname")).sendKeys("sahil");
		driver.findElement(By.name("lastname")).sendKeys("sheikh");
		driver.findElement(By.name("company")).sendKeys("SSS");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String leadInfoPageHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(leadInfoPageHeader.contains("Lead Information")) {
			System.out.println("Pass : Lead Info Page is displayed");
		}else {
			System.out.println("Lead Info Page not found");
		}
		driver.findElement(By.xpath("//input[@name='Duplicate']")).click();
		String duplicatingPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(duplicatingPageHeader.contains("Duplicating")) {
			System.out.println("Pass : Duplicating Lead Page is diplayed");
		}else {
			System.out.println("Fail : Duplicating Lead Page not found");
		}
		WebElement lastName = driver.findElement(By.name("lastname"));
		lastName.clear();
		lastName.sendKeys("SHEIKH");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String duplicatedLeadInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(duplicatedLeadInfo.contains("SHEIKH")) {
			System.out.println("Pass : Duplicated Lead Info Page is displayed");
		}else {
			System.out.println("Duplicated Lead Info Page not found");
		}
		driver.findElement(By.className("hdrLink")).click();
		WebElement newCreatedLead = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[3]/td[3]"));
		WebElement duplicatedLead = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[3]"));
		if(newCreatedLead.getText().contains("sheikh")&&(duplicatedLead.getText().contains("SHEIKH"))){
			System.out.println("Test Case Passed");
		}else {
			System.out.println("Test Case Failed");
		}
		WebElement administratorImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		
		a.moveToElement(administratorImage).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.close();
		
	}

}
