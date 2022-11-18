package genericUtilityImplementation;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericLibraries.AutoConstantPath;
import genericLibraries.ExcelFileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewContact {

	public static void main(String[] args) {
		
		ExcelFileUtility excel = new ExcelFileUtility();
		JavaUtility javaUtility = new JavaUtility();
		PropertyFileUtility property = new PropertyFileUtility();
		WebDriverUtility webdriver = new WebDriverUtility();
		
		property.propertyFileInitialization(AutoConstantPath.PROPERTY_FILE_PATH);
		excel.excelFileInitialization(AutoConstantPath.EXCEL_FILE_PATH);
		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:8888/");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String url = property.getDataFromPropertyFile("url");
		String timeout = property.getDataFromPropertyFile("timeouts");
		WebDriver driver = webdriver.openBrowserAndApplication(url, Long.parseLong(timeout));
		WebElement logo = driver.findElement(By.xpath("//img[@alt=\"logo\"]"));
		if(logo.isDisplayed()) {
			System.out.println("Pass : Login Page is Displayed ");
		}else {
			System.out.println("Fail : Login Page is not Displayed ");
		}
		
		String username = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
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
		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Contact");
		WebElement firtNameSalutationDropdown = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		webdriver.dropdown(firtNameSalutationDropdown, map.get("First Name Salutation"));
//		Select firstName = new Select(firtNameSalutationDropdown);
//		firstName.selectByVisibleText("Mr.");
		String lastName = map.get("Last Name");
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();
		//WebElement existingOrganization = driver.findElement(By.xpath("(//table[@class='small'])[3]/tbody/tr[last()]/td[1]"));
		//if(existingOrganization.getText().contains("XYZ")) {
		
		
		String parentWindow = webdriver.getParentWindow();
		webdriver.handleChildBrowserPopup("Accounts&action");
//		Set<String> childWindow = driver.getWindowHandles();
//		for(String win : childWindow) {
//		driver.switchTo().window(win);}
		String requiredOrganizationName = map.get("Organization Name");
	    List<WebElement> organizationsList = driver.findElements(By.xpath("(//table[@class='small'])[3]/tbody/tr/td[1]"));
	    for(WebElement organization : organizationsList) {
	    	String organizationName = organization.getText();
	    	if(requiredOrganizationName.equals(organizationName)) {
	    		organization.click();
	    	}
	    }
	    webdriver.switchToWindow(parentWindow);

		WebElement contactImage = driver.findElement(By.xpath("//input[@name='imagename']"));
		contactImage.sendKeys(map.get("Contact Image"));
		driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
		
		String contactInfoPageHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(contactInfoPageHeader.contains(lastName)) {
				System.out.println("Pass : New Contact created successfully");
				}else {
					System.out.println("Fail: New Contact is not created");
				}
			driver.findElement(By.xpath("(//a[text()='Contacts'])[2]")).click();
		String contactPageHeader = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
			if(contactPageHeader.contains("Contacts")) {
			System.out.println("Pass : Contacts Page is Displayed");
		}else {
			System.out.println("Fail : Contacts Page not found");
		}
			WebElement newContact = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[4]"));
			if(newContact.getText().equalsIgnoreCase(lastName)) {
				System.out.println("Pass : Test Case is Passed");
				excel.writeDataIntoExcel("testData", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "Create Contact");
		}else {
			
			System.out.println("Fail : Test Case is Failed");
			excel.writeDataIntoExcel("testData", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "Create Contact");
			}
		WebElement administratorImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriver.mouseHoverToElement(administratorImage);
			//Actions a = new Actions(driver);
			//a.moveToElement(administratorImage).perform();
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			excel.closeExcel();
			webdriver.closeBrowser();
			
			
			
		}
		}
		
			
		





