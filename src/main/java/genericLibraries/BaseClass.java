package genericLibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import pomPages.ContactPage;
import pomPages.CreatingNewContactPage;
import pomPages.CreatingNewLeadPage;
import pomPages.CreatingNewOrganizationPage;
import pomPages.DuplicatingLeadPage;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;
import pomPages.NewContactInfoPage;
import pomPages.NewLeadInfoPage;
import pomPages.NewOrganizationInfoPage;
import pomPages.OrganizationsPage;

public class BaseClass {
	protected ExcelFileUtility excel;
	protected JavaUtility javaUtility;
	protected WebDriverUtility webdriver;
	protected PropertyFileUtility property;
	protected WebDriver driver;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected OrganizationsPage organizationPage;
	protected CreatingNewOrganizationPage creatingNewOrganizationPage;
	protected NewOrganizationInfoPage newOrganizationInfoPage;
	protected ContactPage contactPage;
	protected CreatingNewContactPage creatingNewContactPage;
	protected NewContactInfoPage newContactInfoPage;
	protected LeadsPage leadsPage;
	protected CreatingNewLeadPage creatingNewLeadPage;
	protected NewLeadInfoPage newLeadInfoPage;
	protected DuplicatingLeadPage duplicatingLeadPage;
	//@BeforeSuite
	@BeforeTest
	public void testSetUp() {
		excel = new ExcelFileUtility();
		javaUtility = new JavaUtility();
	    webdriver = new WebDriverUtility();
		property = new PropertyFileUtility();
		property.propertyFileInitialization(AutoConstantPath.PROPERTY_FILE_PATH);
		excel.excelFileInitialization(AutoConstantPath.EXCEL_FILE_PATH);
		
	}
	@BeforeClass
	public void classSetUp(){
		String url = property.getDataFromPropertyFile("url");
		String time = property.getDataFromPropertyFile("timeouts");
		long timeouts = Long.parseLong(time);
		driver = webdriver.openBrowserAndApplication(url, timeouts);
		
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		organizationPage = new OrganizationsPage(driver);
		creatingNewOrganizationPage = new CreatingNewOrganizationPage(driver);
		newOrganizationInfoPage = new NewOrganizationInfoPage(driver);
//		if(loginPage.getLogo().isDisplayed()) {
//			System.out.println("Pass : Login Page is Displayed ");
//		}else {
//			System.out.println("Fail : Login Page is not Displayed ");
//		}
		Assert.assertTrue(loginPage.getLogo().isDisplayed());
		contactPage = new ContactPage(driver);
		creatingNewContactPage = new CreatingNewContactPage(driver);
		newContactInfoPage = new NewContactInfoPage(driver);
		leadsPage = new LeadsPage(driver);
		creatingNewLeadPage = new CreatingNewLeadPage(driver);
		newLeadInfoPage = new NewLeadInfoPage(driver);
		duplicatingLeadPage = new DuplicatingLeadPage(driver);
	}
	@BeforeMethod
	public void methodSetUp() {
		String username= property.getDataFromPropertyFile("username");
		String password=property.getDataFromPropertyFile("password");
		loginPage.loginToApplication(username, password);
	}
	@AfterMethod
	public void methodTearDown() {
		homePage.signOutToApplication(webdriver);
	}
	@AfterClass
	public void classTearDown() {
		webdriver.closeBrowser();
	}
	@AfterTest
	public void testTearDown() {
		excel.closeExcel();
	}
	//@AfterSuite
	
	

}
