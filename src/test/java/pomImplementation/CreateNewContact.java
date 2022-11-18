package pomImplementation;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.AutoConstantPath;
import genericLibraries.ExcelFileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import pomPages.ContactPage;
import pomPages.CreatingNewContactPage;
import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.NewContactInfoPage;

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
		ContactPage contactPage = new ContactPage(driver);
		CreatingNewContactPage creatingNewContactPage = new CreatingNewContactPage(driver);
		NewContactInfoPage newContactInfoPage = new NewContactInfoPage(driver);
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		
		
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
//		String homePageTitle = driver.getTitle();
		if(home.getPageHeaderText().contains("Home")) {
			System.out.println("Pass : Home Page is Displayed ");
		}else {
			System.out.println("Fail : Home Page not found ");
		}
		//driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		home.clickContactsTab();
		//String contactsPageHeader = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		if(contactPage.getPageHeaderText().contains("Contacts")) {
			System.out.println("Pass : Contacts Page is Displayed");
		}else {
			System.out.println("Fail : Contacts Page not found");
		}
		//driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		contactPage.clickPlusImage();
		//String createNewContactHeader = driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();
		if (creatingNewContactPage.getPageHeaderText().contains("Creating New Contact")) {
			System.out.println("Pass : Creating New Contact Page is Displayed");
		}else {
			System.out.println("Fail : Creating New Contact Page is not found");
		}
		
		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Contact");
		creatingNewContactPage.selectFirstNameSalutationDropdown(webdriver,  map.get("First Name Salutation"));
		//WebElement firtNameSalutationDropdown = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		//webdriver.dropdown(firtNameSalutationDropdown, map.get("First Name Salutation"));
//		Select firstName = new Select(firtNameSalutationDropdown);
//		firstName.selectByVisibleText("Mr.");
		
		String lastName = map.get("Last Name") + javaUtility.generateRandomNumber(100);
		creatingNewContactPage.setLastName(lastName);
		//driver.findElement(By.name("lastname")).sendKeys(lastName);
		//driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();
		creatingNewContactPage.clickOrganizationListPlusImage();
		//WebElement existingOrganization = driver.findElement(By.xpath("(//table[@class='small'])[3]/tbody/tr[last()]/td[1]"));
		//if(existingOrganization.getText().contains("XYZ")) {
		
		
//		String parentWindow = webdriver.getParentWindow();
//		webdriver.handleChildBrowserPopup("Accounts&action");
//		Set<String> childWindow = driver.getWindowHandles();
//		for(String win : childWindow) {
//		driver.switchTo().window(win);}
		String requiredOrganizationName = map.get("Organization Name");
		creatingNewContactPage.selectExistingOrganizationList(webdriver, requiredOrganizationName, driver);
//	    List<WebElement> organizationsList = driver.findElements(By.xpath("(//table[@class='small'])[3]/tbody/tr/td[1]"));
//	    for(WebElement organization : organizationsList) {
//	    	String organizationName = organization.getText();
//	    	if(requiredOrganizationName.equals(organizationName)) {
//	    		organization.click();
//	    	}
//	    }
//	    webdriver.switchToWindow(parentWindow);

		//WebElement contactImage = driver.findElement(By.xpath("//input[@name='imagename']"));
		creatingNewContactPage.uploadImage(map.get("Contact Image"));
		//contactImage.sendKeys(map.get("Contact Image"));
		//driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
		creatingNewContactPage.clickSaveButton();
		//String contactInfoPageHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(newContactInfoPage.getPageHeader().contains(lastName)) {
				System.out.println("Pass : New Contact created successfully");
				}else {
					System.out.println("Fail: New Contact is not created");
				}
			//driver.findElement(By.xpath("(//a[text()='Contacts'])[2]")).click();
			newContactInfoPage.clickContactsLink();
		String contactPageHeader = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
			if(contactPage.getPageHeaderText().contains("Contacts")) {
			System.out.println("Pass : Contacts Page is Displayed");
		}else {
			System.out.println("Fail : Contacts Page not found");
		}
			//WebElement newContact = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[4]"));
			
			if(contactPage.getNewContactInList().equalsIgnoreCase(lastName)) {
				System.out.println("Pass : Test Case is Passed");
				excel.writeDataIntoExcel("testData", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "Create Contact");
		}else {
			
			System.out.println("Fail : Test Case is Failed");
			excel.writeDataIntoExcel("testData", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "Create Contact");
			}
//		WebElement administratorImage = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		webdriver.mouseHoverToElement(administratorImage);
//			//Actions a = new Actions(driver);
//			//a.moveToElement(administratorImage).perform();
//			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			home.signOutToApplication(webdriver);
			excel.closeExcel();
			webdriver.closeBrowser();
			
			
			
		}
		}
		
			
		





