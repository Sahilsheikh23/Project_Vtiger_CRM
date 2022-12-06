package testNGImplementation;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;

public class CreateNewOrganization extends BaseClass {

	@Test
	public void createNewOrganizationTest() throws InterruptedException, EncryptedDocumentException, IOException {

		SoftAssert soft = new SoftAssert();
		
//		if (homePage.getPageHeaderText().contains("Home")) {
//			System.out.println("Pass : Home Page is Displayed ");
//		} else {
//			System.out.println("Fail : Home Page not found ");
//		}
        soft.assertTrue(homePage.getPageHeaderText().contains("Home"));
		homePage.clickOrganizationsTab();

//		if (organizationPage.getPageHeaderText().contains("Organizations"))
//			System.out.println("Pass : Organizations page displayed");
//		else
//			System.out.println("Fail : Organizations page not displayed");
        soft.assertTrue(organizationPage.getPageHeaderText().contains("Organizations"));
		organizationPage.clickPlusImage();

//		if (creatingNewOrganizationPage.getPageHeaderText().contains("Creating New Organization")) {
//			System.out.println("Pass : Creating New Organization is Displayed");
//		} else {
//			System.out.println("Creating New Organization is not found");
//		}
		soft.assertTrue(creatingNewOrganizationPage.getPageHeaderText().contains("Creating New Organization"));
		Map<String, String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Organization");
		String organizationName = map.get("Organization Name") + javaUtility.generateRandomNumber(100);

		creatingNewOrganizationPage.SetOrganizationNameTextField(organizationName);
		creatingNewOrganizationPage.SelectIndustryDropdown(webdriver, map.get("Industry"));
		creatingNewOrganizationPage.clickGroupRadioButton();
		creatingNewOrganizationPage.SelectAssignedToDropdown(webdriver, map.get("Group"));
		creatingNewOrganizationPage.clickSaveButton();
		
//		if (newOrganizationInfoPage.getPageHeader().contains(organizationName)) {
//			System.out.println("Pass : New Organization Info Page is Displayed");
//		} else {
//			System.out.println("Fail : New Organization Info Page not found");
//		}
        soft.assertTrue(newOrganizationInfoPage.getPageHeader().contains(organizationName));
		newOrganizationInfoPage.clickOrganization();

		WebElement newOrganization = driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td[3]"));
		soft.assertTrue(organizationPage.getNewOrganization().equalsIgnoreCase(organizationName));
		if (organizationPage.getNewOrganization().equalsIgnoreCase(organizationName)) {
			System.out.println("Test Case is Passed");
		} else {
			System.out.println("Test Case is Failed");
		}
		soft.assertAll();

	}

}
