package genericUtilityImplementation;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
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

public class CreateNewLead {

	public static void main(String[] args) {
		ExcelFileUtility excel = new ExcelFileUtility();
		JavaUtility javaUtility = new JavaUtility();
		PropertyFileUtility property = new PropertyFileUtility();
		WebDriverUtility webdriver = new WebDriverUtility();
		
		property.propertyFileInitialization(AutoConstantPath.PROPERTY_FILE_PATH);
		excel.excelFileInitialization(AutoConstantPath.EXCEL_FILE_PATH);
		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://localhost:8888/");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
		driver.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		String leadsPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(leadsPageHeader.contains("Creating New Lead")) {
			System.out.println("Pass: Creating New Lead Page is Displayed");
		}else {
			System.out.println("Fail : Creating New Lead Page not found");
		}
		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");
		WebElement firtNameSalutationDropdown = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		webdriver.dropdown(firtNameSalutationDropdown, map.get("First Name Salutation"));
//		Select firstName = new Select(firtNameDropdown);
//		firstName.selectByVisibleText("Mr.");
	    String leadName = map.get("Last Name")+javaUtility.generateRandomNumber(100);
		driver.findElement(By.name("lastname")).sendKeys(leadName);
		driver.findElement(By.name("company")).sendKeys(map.get("Company"));
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String leadInfoPageHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(leadInfoPageHeader.contains(leadName)) {
			System.out.println("Pass : New Lead created successfully");
		}else {
			System.out.println("Fail : New Lead is not created");
		}
		driver.findElement(By.xpath("//input[@name='Duplicate']")).click();
		
		String duplicatingPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(duplicatingPageHeader.contains(leadName)) {
			System.out.println("Pass : Duplicating Lead Page is displayed");
		}else {
			System.out.println("Fail : Duplicating Lead Page not found");
		}
		String newLastName = map.get("New Last Name")+javaUtility.generateRandomNumber(100);
		WebElement lastName = driver.findElement(By.name("lastname"));
		lastName.clear();
		lastName.sendKeys(newLastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		String duplicatedLeadInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(duplicatedLeadInfo.contains(newLastName)) {
			System.out.println("Pass : Duplicated Lead Info Page is displayed");
		}else {
			System.out.println("Duplicated Lead Info Page not found");
		}
		driver.findElement(By.className("hdrLink")).click();
		
//		WebElement newCreatedLead = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[3]/td[3]"));
//		WebElement duplicatedLead = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[3]"));
		String name = driver.findElement(By.xpath("//table[@class='lvtBg']/descendant::div/table/tbody/tr[last()]/td[3]/a")).getText();
		if(name.equals(newLastName)) {
			System.out.println("Test Case passed");
			excel.writeDataIntoExcel("TestData", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
		WebElement administratorImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriver.mouseHoverToElement(administratorImage);
		//Actions a = new Actions(driver);
		//a.moveToElement(administratorImage).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		excel.closeExcel();
		webdriver.closeBrowser();
		//driver.close();
		
	}

}
