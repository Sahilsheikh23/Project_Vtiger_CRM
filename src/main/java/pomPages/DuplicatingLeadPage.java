package pomPages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.ExcelFileUtility;
import genericLibraries.JavaUtility;

public class DuplicatingLeadPage {

	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHeaderText;
	@FindBy(name="lastname")
	private WebElement lastNameTextField;
	@FindBy(xpath = "//input[contains(@value,'Save')]")
	private WebElement saveButton;
	

	public DuplicatingLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeaderText.getText();
	}

	public String duplicatingLead(ExcelFileUtility excel, JavaUtility javaUtility) {
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");
		String leadLastName =map.get("New Last Name")+javaUtility.generateRandomNumber(100);
		lastNameTextField.clear();
		lastNameTextField.sendKeys(leadLastName);
		saveButton.click();
		return leadLastName;
	}
	
	
}
