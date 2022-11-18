package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	@FindBy(xpath="//a[@class='hdrLink']") private WebElement pageHeaderText;
	@FindBy(xpath="//img[@alt='Create Contact...']") private WebElement plusImage;
	@FindBy(xpath="//table[@class='lvt small']/tbody/tr[last()]/td[4]") private WebElement newContactInList;
	
	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public String getPageHeaderText() {
		return pageHeaderText.getText();
	}

	public void clickPlusImage() {
		plusImage.click();
	}
	public String getNewContactInList() {
		return newContactInList.getText();
	}
	
}
