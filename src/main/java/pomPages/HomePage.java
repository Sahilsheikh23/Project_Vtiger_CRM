package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class HomePage {
	
	@FindBy (xpath= "//a[@class='hdrLink']") private WebElement pageHeaderText;
	@FindBy (xpath= "(//a[text()='Organizations'])[1]") private WebElement organizationsTab;
    @FindBy(xpath="//a[text()='Contacts']") private WebElement contactsTab;
    @FindBy(xpath="(//a[text()='Leads'])[1]") private WebElement leadsTab;
    @FindBy(xpath="//img[@src='themes/softed/images/user.PNG']") private WebElement administratorImage;
    @FindBy(xpath="//a[text()='Sign Out']") private WebElement signoutLink;
    
    public HomePage(WebDriver driver) {
    	PageFactory.initElements(driver,this);
    }

	public String getPageHeaderText() {
		return pageHeaderText.getText();
	}
	public void clickOrganizationsTab() {
		organizationsTab.click();
	}
	public void clickContactsTab() {
		contactsTab.click();
	}
	public void clickLeadsTab() {
		leadsTab.click();
	}
	public void signOutToApplication(WebDriverUtility webdriver) {
		webdriver.mouseHoverToElement(administratorImage);
		signoutLink.click();
	}
	

	
}
