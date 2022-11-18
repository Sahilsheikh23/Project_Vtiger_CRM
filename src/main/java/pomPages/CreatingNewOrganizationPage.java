package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreatingNewOrganizationPage {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']") private WebElement pageHeaderText;
	@FindBy(name="accountname") private WebElement organizationNameTextField;
	@FindBy(name="industry") private WebElement industryDropdown;
	@FindBy(xpath="//input[@value='T']") private WebElement groupRadioButton;
	@FindBy(xpath="//select[@name='assigned_group_id']") private WebElement assignedToDropdown;
	@FindBy(xpath="//input[contains(@value,'Save')]") private WebElement saveButton;
	
	public CreatingNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public String getPageHeaderText() {
		return pageHeaderText.getText();
	}
	public void SetOrganizationNameTextField(String organizationName) {
		organizationNameTextField.sendKeys(organizationName);
	}
	public void SelectIndustryDropdown(WebDriverUtility webdriver, String group) {
		webdriver.dropdown(industryDropdown, group);
	}
	public void clickGroupRadioButton() {
		groupRadioButton.click();
	}
	public void SelectAssignedToDropdown(WebDriverUtility webdriver, String industry) {
		webdriver.dropdown(assignedToDropdown, industry);
	}
	public void clickSaveButton() {
		saveButton.click();
	}
	

}
