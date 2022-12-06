package testNGImplementation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.AutoConstantPath;
import genericLibraries.BaseClass;
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

public class CreateNewLead extends BaseClass {
	@Test
	public void createNewLeadTest() {
		SoftAssert soft = new SoftAssert();
//		if (homePage.getPageHeaderText().contains("Home")) {
//			System.out.println("Pass : Home Page is Displayed ");
//		} else {
//			System.out.println("Fail : Home Page not found ");
//		}
		soft.assertTrue(homePage.getPageHeaderText().contains("Home"));

		homePage.clickLeadsTab();

		leadsPage.clickPlusImage();

//		if (creatingNewLeadPage.getPageHeaderText().contains("Creating New Lead")) {
//			System.out.println("Pass: Creating New Lead Page is Displayed");
//		} else {
//			System.out.println("Fail : Creating New Lead Page not found");
//		}
		soft.assertTrue(creatingNewLeadPage.getPageHeaderText().contains("Creating New Lead"));
		String leadName = creatingNewLeadPage.createLead(webdriver, excel, javaUtility);

//		if (newLeadInfoPage.getPageHeader().contains(leadName)) {
//			System.out.println("Pass : New Lead created successfully");
//		} else {
//			System.out.println("Fail : New Lead is not created");
//		}
		soft.assertTrue(newLeadInfoPage.getPageHeader().contains(leadName));

		newLeadInfoPage.clickDuplicateButton();

//		if (duplicatingLeadPage.getPageHeader().contains(leadName)) {
//			System.out.println("Pass : Duplicating Lead Page is displayed");
//		} else {
//			System.out.println("Fail : Duplicating Lead Page not found");
//		}
		soft.assertTrue(duplicatingLeadPage.getPageHeader().contains(leadName));

		String newLastName = duplicatingLeadPage.duplicatingLead(excel, javaUtility);

//		if (newLeadInfoPage.getPageHeader().contains(newLastName)) {
//			System.out.println("Pass : Duplicated Lead Info Page is displayed");
//		} else {
//			System.out.println("Duplicated Lead Info Page not found");
//		}
		soft.assertTrue(newLeadInfoPage.getPageHeader().contains(newLastName));

		newLeadInfoPage.clickLeads();
        soft.assertTrue(leadsPage.getLastLeadName().equals(newLastName));
		if (leadsPage.getLastLeadName().equals(newLastName)) {
			System.out.println("Test Case passed");
			excel.writeDataIntoExcel("TestData", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}

		else {
			System.out.println("Test Case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "Create Lead");
		}
		soft.assertAll();

	}

}
