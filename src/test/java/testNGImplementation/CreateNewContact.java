package testNGImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.AutoConstantPath;
import genericLibraries.BaseClass;

public class CreateNewContact extends BaseClass{
    @Test
	public void createNewContactTest() {
    	SoftAssert soft = new SoftAssert();
    	
//		if(homePage.getPageHeaderText().contains("Home")) {
//			System.out.println("Pass : Home Page is Displayed ");
//		}else {
//			System.out.println("Fail : Home Page not found ");
//		}
    	soft.assertTrue(homePage.getPageHeaderText().contains("Home"));
		
		homePage.clickContactsTab();
		
//		if(contactPage.getPageHeaderText().contains("Contacts")) {
//			System.out.println("Pass : Contacts Page is Displayed");
//		}else {
//			System.out.println("Fail : Contacts Page not found");
//		}
		soft.assertTrue(contactPage.getPageHeaderText().contains("Contacts"));
		
		contactPage.clickPlusImage();
		
//		if (creatingNewContactPage.getPageHeaderText().contains("Creating New Contact")) {
//			System.out.println("Pass : Creating New Contact Page is Displayed");
//		}else {
//			System.out.println("Fail : Creating New Contact Page is not found");
//		}
		soft.assertTrue(creatingNewContactPage.getPageHeaderText().contains("Creating New Contact"));
		
		Map<String,String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Contact");
		creatingNewContactPage.selectFirstNameSalutationDropdown(webdriver,  map.get("First Name Salutation"));

		String lastName = map.get("Last Name") + javaUtility.generateRandomNumber(100);
		creatingNewContactPage.setLastName(lastName);
		
		creatingNewContactPage.clickOrganizationListPlusImage();
		

		String requiredOrganizationName = map.get("Organization Name");
		creatingNewContactPage.selectExistingOrganizationList(webdriver, requiredOrganizationName, driver);

		creatingNewContactPage.uploadImage(map.get("Contact Image"));
	
		creatingNewContactPage.clickSaveButton();
		
//			if(newContactInfoPage.getPageHeader().contains(lastName)) {
//				System.out.println("Pass : New Contact created successfully");
//				}else {
//					System.out.println("Fail: New Contact is not created");
//				}
		soft.assertTrue(newContactInfoPage.getPageHeader().contains(lastName));
			
			newContactInfoPage.clickContactsLink();
		String contactPageHeader = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
//			if(contactPage.getPageHeaderText().contains("Contacts")) {
//			System.out.println("Pass : Contacts Page is Displayed");
//		}else {
//			System.out.println("Fail : Contacts Page not found");
//		}
		soft.assertTrue(contactPage.getPageHeaderText().contains("Contacts"));
			
			soft.assertTrue(contactPage.getNewContactInList().equalsIgnoreCase(lastName));
			if(contactPage.getNewContactInList().equalsIgnoreCase(lastName)) {
				System.out.println("Pass : Test Case is Passed");
				excel.writeDataIntoExcel("testData", "Pass", AutoConstantPath.EXCEL_FILE_PATH, "Create Contact");
		}else {
			
			System.out.println("Fail : Test Case is Failed");
			excel.writeDataIntoExcel("testData", "Fail", AutoConstantPath.EXCEL_FILE_PATH, "Create Contact");
			}

			soft.assertAll();
			
		}
		}
		
			
		





