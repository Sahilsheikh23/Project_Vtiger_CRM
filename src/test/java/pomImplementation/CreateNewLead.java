package pomImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.AutoConstantPath;
import genericLibraries.ExcelFileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import pomPages.CreatingNewLeadPage;
import pomPages.DuplicatingLeadPage;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;
import pomPages.NewLeadInfoPage;

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
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadsPage leadsPage = new LeadsPage(driver);
		CreatingNewLeadPage creatingNewLeadPage = new CreatingNewLeadPage(driver);
		NewLeadInfoPage newLeadInfoPage = new NewLeadInfoPage(driver);
		DuplicatingLeadPage duplicatingLeadPage = new DuplicatingLeadPage(driver);
		
		//WebElement logo = driver.findElement(By.xpath("//img[@alt=\"logo\"]"));
		if(login.getLogo().isDisplayed()) {
			System.out.println("Pass : Login Page is Displayed ");
		}else {
			System.out.println("Fail : Login Page is not Displayed ");
		}
		String username = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		login.loginToApplication(username, password);
//		driver.findElement(By.name("user_name")).sendKeys(username);
//		driver.findElement(By.name("user_password")).sendKeys(password);
//		driver.findElement(By.id("submitButton")).click();
		//String homePageTitle = driver.getTitle();
		if(home.getPageHeaderText().contains("Home")) {
			System.out.println("Pass : Home Page is Displayed ");
		}else {
			System.out.println("Fail : Home Page not found ");
		}
		//driver.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
		home.clickLeadsTab();
		//driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		leadsPage.clickPlusImage();
		//String leadsPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(creatingNewLeadPage.getPageHeaderText().contains("Creating New Lead")) {
			System.out.println("Pass: Creating New Lead Page is Displayed");
		}else {
			System.out.println("Fail : Creating New Lead Page not found");
		}
		String leadName = creatingNewLeadPage.createLead(webdriver, excel, javaUtility);
//		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");
//		WebElement firtNameSalutationDropdown = driver.findElement(By.xpath("//select[@name='salutationtype']"));
//		webdriver.dropdown(firtNameSalutationDropdown, map.get("First Name Salutation"));
//		Select firstName = new Select(firtNameDropdown);
//		firstName.selectByVisibleText("Mr.");
//	    String leadName = map.get("Last Name")+javaUtility.generateRandomNumber(100);
//		driver.findElement(By.name("lastname")).sendKeys(leadName);
//		driver.findElement(By.name("company")).sendKeys(map.get("Company"));
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//		
		//String leadInfoPageHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(newLeadInfoPage.getPageHeader().contains(leadName)) {
			System.out.println("Pass : New Lead created successfully");
		}else {
			System.out.println("Fail : New Lead is not created");
		}
		//driver.findElement(By.xpath("//input[@name='Duplicate']")).click();
		newLeadInfoPage.clickDuplicateButton();
		//String duplicatingPageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(duplicatingLeadPage.getPageHeader().contains(leadName)) {
			System.out.println("Pass : Duplicating Lead Page is displayed");
		}else {
			System.out.println("Fail : Duplicating Lead Page not found");
		}
		
		String newLastName = duplicatingLeadPage.duplicatingLead(excel, javaUtility);
//		WebElement lastName = driver.findElement(By.name("lastname"));
//		lastName.clear();
//		lastName.sendKeys(newLastName);
//		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//String duplicatedLeadInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(newLeadInfoPage.getPageHeader().contains(newLastName)) {
			System.out.println("Pass : Duplicated Lead Info Page is displayed");
		}else {
			System.out.println("Duplicated Lead Info Page not found");
		}
		//driver.findElement(By.className("hdrLink")).click();
		newLeadInfoPage.clickLeads();
		
//		WebElement newCreatedLead = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[3]/td[3]"));
//		WebElement duplicatedLead = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[3]"));
		//String name = driver.findElement(By.xpath("//table[@class='lvtBg']/descendant::div/table/tbody/tr[last()]/td[3]/a")).getText();
		System.out.println(newLastName);
		System.out.println(leadsPage.getLastLeadName());
		if(leadsPage.getLastLeadName().equals(newLastName)) {
			System.out.println("Test Case passed");
			excel.writeDataIntoExcel("TestData", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
			
		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
		
		home.signOutToApplication(webdriver);
//		WebElement administratorImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		webdriver.mouseHoverToElement(administratorImage);
//		//Actions a = new Actions(driver);
//		//a.moveToElement(administratorImage).perform();
//		
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		excel.closeExcel();
		webdriver.closeBrowser();
		//driver.close();
		
	}

}
