package pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(xpath="//img[@alt='logo']") private WebElement logo;
	@FindBy(name="user_name") private WebElement usernameTextField;
	@FindBy(name="user_password") private WebElement passwordTextField;
	@FindBy(id="submitButton") private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLogo() {
		return logo;
	}
	
	public void loginToApplication(String username, String password) {
		usernameTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		loginButton.click();
	}
	 
	

}
