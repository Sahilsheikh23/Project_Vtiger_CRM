package genericUtilityImplementation;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericLibraries.AutoConstantPath;
import genericLibraries.ExcelFileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;

public class CreateNewOrganization {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException  {
		ExcelFileUtility excel = new ExcelFileUtility();
		JavaUtility javaUtility = new JavaUtility();
		PropertyFileUtility property = new PropertyFileUtility();
		WebDriverUtility webdriver = new WebDriverUtility();
		
		property.propertyFileInitialization(AutoConstantPath.PROPERTY_FILE_PATH);
		excel.excelFileInitialization(AutoConstantPath.EXCEL_FILE_PATH);
		
		
//		Random random = new Random();
//		int randomNum = random.nextInt(100);
		String url = property.getDataFromPropertyFile("url");
		String timeout = property.getDataFromPropertyFile("timeouts");
		WebDriver driver = webdriver.openBrowserAndApplication(url, Long.parseLong(timeout));
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://localhost:8888/");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement logo = driver.findElement(By.xpath("//img[@alt='logo']"));
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
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		String createNewOrganizationHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(createNewOrganizationHeader.contains("Creating New Organization")) {
			System.out.println("Pass : Creating New Organization is Displayed");
		}else {
		System.out.println("Creating New Organization is not found");
		}
		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Organization");
		String accountName = map.get("Organization Name")+javaUtility.generateRandomNumber(100);
		driver.findElement(By.name("accountname")).sendKeys(accountName);
		
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		webdriver.dropdown(industryDropdown, map.get("Industry"));
//		Select industry = new Select(industryDropdown);
//		industry.selectByVisibleText("Engineering");
		driver.findElement(By.xpath("//input[@value='T']")).click();
		WebElement assignedToDropdown = driver.findElement(By.xpath("//select[@name='assigned_group_id']"));
		webdriver.dropdown(assignedToDropdown, map.get("Group"));
//		Select assignedTo = new Select(assignedToDropdown);
//		assignedTo.selectByVisibleText("Support Group");
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();
		String newOrganizationInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(newOrganizationInfo.contains(accountName)) {
			System.out.println("Pass : New Organization Info Page is Displayed");
		}else {
			System.out.println("Fail : New Organization Info Page not found");
		}
		driver.findElement(By.className("hdrLink")).click();
		WebElement newOrganization = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[3]"));
		if(newOrganization.getText().contains(accountName)) {
			System.out.println("Test Case is Passed");
		}else
		{
			System.out.println("Test Case is Failed");
		}
		WebElement administratorImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriver.mouseHoverToElement(administratorImage);
//		Actions a = new Actions(driver);
//		a.moveToElement(administratorImage).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		excel.closeExcel();
		webdriver.closeBrowser();
		
		
		
		

	}

}
