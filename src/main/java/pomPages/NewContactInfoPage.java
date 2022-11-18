package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewContactInfoPage {
	
		@FindBy(xpath = "//span[@class='dvHeaderText']")
		private WebElement pageHeaderText;
		
		@FindBy(xpath="//a[@class='hdrLink']")
		private WebElement contactsLink;

		
		public NewContactInfoPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

	

		public String getPageHeader() {
			return pageHeaderText.getText();
		}
		
		public void clickContactsLink() {
			contactsLink.click();
		}
	

}
