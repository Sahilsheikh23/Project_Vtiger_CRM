package pomPages;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.ExcelFileUtility;
import genericLibraries.JavaUtility;
import genericLibraries.WebDriverUtility;

public class CreatingNewLeadPage {

	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHeaderText;
    @FindBy(name = "salutationtype")
	private WebElement firstNameSalutationDropdown;
	@FindBy(name = "lastname")
	private WebElement leadLastNameTextField;
	@FindBy(name = "company")
	private WebElement companyTextField;
	@FindBy(xpath = "//input[contains(@value,'Save')]")
	private WebElement saveButton;
	
	public CreatingNewLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String createLead(WebDriverUtility webdriver,ExcelFileUtility excel, JavaUtility javaUtility) {
		Map<String,String> map =excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Lead");

		webdriver.dropdown(firstNameSalutationDropdown, map.get("First Name Salutation"));

		String leadName = map.get("Last Name")+javaUtility.generateRandomNumber(100);
		leadLastNameTextField.sendKeys(leadName);
		companyTextField.sendKeys(map.get("Company"));
		saveButton.click();

		return leadName;

	}

	public String getPageHeaderText() {
		return pageHeaderText.getText();
	}

}
