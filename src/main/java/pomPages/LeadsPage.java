package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {
	
	@FindBy(xpath="//img[@title='Create Lead...']")
	private WebElement plusImage;

	@FindBy(xpath="//a[@href='index.php?action=ListView&module=Contacts&parenttab=Marketing']")
	private WebElement pageHeaderText;

	@FindBy(xpath="//table[@class='lvtBg']/descendant::div/table/tbody/tr[last()]/td[3]/a")
	private WebElement lastContactInList;

	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	
	public void clickPlusImage() {
		plusImage.click();
	}

	public String getPageHeaderText() {
		return pageHeaderText.getText();
	}

	public String getLastLeadName() {
		return lastContactInList.getText();
	}



}
