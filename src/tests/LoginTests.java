package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPageHelper;
import pages.HomePageHelper;
import pages.LoginPageHelper;

public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    HomePageHelper homePage;

    @BeforeMethod
    public void initTests() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded();

    }

        @Test
        public void loginNegativeLoginEmpty ()  {
            loginPage.loginNotAttlassian("",PASSWORD)
                    .pressLoginButton();
            Assert.assertEquals(loginPage.getErrorMessage(),"Missing email",
                    "The text of the error message is not correct");
        }

        //Sel-04
        @Test
        public void loginNegativeNonexistentLoginAndPassword ()  {
            loginPage.loginNotAttlassian("wweq",PASSWORD);
            Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this username",
                    "The error message is not 'There isn't an account for this username'");

        }

        //Sel-05
        @Test
        public void loginNegativeNonexistentPassword () {
            loginPage.loginAsAttlassian(LOGIN,"14675878");
            Assert.assertTrue(loginPage.getAttlassianErrorMessage().contains("Incorrect email address and / or password."),
                    "The error message is not contains the text 'Incorrect email address and / or password.'");
        }

        //Sel-06
        @Test
        public void loginPositiveTest ()  {
            loginPage.loginAsAttlassian(LOGIN,PASSWORD);
            boardsPage.waitUntilPageIsLoaded();
            Assert.assertTrue(boardsPage.getBoadsIconName().equals("Boards"),"The text on the button is not 'Board'");
        }
}



