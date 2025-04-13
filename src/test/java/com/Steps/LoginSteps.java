package com.Steps;
import com.Pages.LoginPage;
import com.Utility.Utilities;

import io.cucumber.java.en.*;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();
    @Given("Launch the application")
    public void launch_the_application() {
    	loginPage.launchTheApplication();
    }

    @When("user enters valid username and password")
    public void enter_credentials() throws InterruptedException   {
        loginPage.enterUserName(Utilities.getProperty("username"));
        loginPage.enterPassword(Utilities.getProperty("password"));
        loginPage.clickOnLogInButton();
    }

    @And("user should be navigated to home page")
    public void verify_home_page() {
        loginPage.checkForProfileImage();
    }
    
    @Then("logout from the application")
    public void logout_from_the_application(){
    	loginPage.logout();
    }
    
}
