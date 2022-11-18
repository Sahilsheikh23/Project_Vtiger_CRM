package pomPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreatingNewContactPage {

	@FindBy(xpath="//span[@class='lvtHeaderText']") private WebElement pageHeaderText;
	@FindBy(xpath="//select[@name='salutationtype']") private WebElement firstNameSalutationDropdown;
	@FindBy(name="lastname") private WebElement lastNameTextField;
	@FindBy(xpath="(//img[@title='Select'])[1]") private WebElement organizationListPlusImage;
	@FindBy(xpath="(//table[@class='small'])[3]/tbody/tr/td[1]") private List<WebElement> existingOrganizationList;
	@FindBy(xpath="//input[@name='imagename']") private WebElement contactImage;
	@FindBy(xpath="(//input[@type='submit'])[2]") private WebElement saveButton;
    private String organizationTableRowPath = "//div[@id='ListViewContents']/descendant::table[@cellpadding=\"5\"]/tbody/tr[%d]/td[1]/a";
	
    public CreatingNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public String getPageHeaderText() {
		return pageHeaderText.getText();
	}
	public void selectFirstNameSalutationDropdown(WebDriverUtility webdriver, String value) {
		webdriver.dropdown(firstNameSalutationDropdown, value);
	}
	public void setLastName(String lastName) {
		lastNameTextField.sendKeys(lastName);
	}
	public void selectExistingOrganizationList(WebDriverUtility webdriver,String requiredOrganizationName, WebDriver driver) {
		organizationListPlusImage.click();
		String parentWindow = webdriver.getParentWindow();
		webdriver.handleChildBrowserPopup("Accounts&action");

		for (int i = 2; i < existingOrganizationList.size(); i++) {
			String requiredPath = String.format(organizationTableRowPath, i);
			WebElement organization = driver.findElement(By.xpath(requiredPath));
			String organizationName = organization.getText();
			if (organizationName.equals(requiredOrganizationName)) {
				organization.click();
				break;
			}
		}
		webdriver.switchToWindow(parentWindow);
		
	}
	
	public void uploadImage(String imageFilePath) {
		contactImage.sendKeys(imageFilePath);
	}
	public void clickSaveButton() {
		saveButton.click();
	}
	
	public void clickOrganizationListPlusImage() {
		organizationListPlusImage.click();
	}
}
	
