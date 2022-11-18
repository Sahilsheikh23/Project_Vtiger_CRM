package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewOrganizationInfoPage {

		@FindBy(xpath = "//span[@class='dvHeaderText']")
		private WebElement pageHeaderText;
		
		@FindBy(xpath="//a[@href='index.php?action=ListView&module=Accounts&parenttab=Marketing']")
		private WebElement organizationsLink;

		public NewOrganizationInfoPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		
		
		public String getPageHeader() {
			return pageHeaderText.getText();
		}
		
		public void clickOrganization() {
			organizationsLink.click();
		}
}
