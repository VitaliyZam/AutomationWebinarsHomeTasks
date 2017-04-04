package Tests;

import Utils.BaseScript;
import Utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 01.04.2017.
 */
public class Tests extends BaseScript {
    WebDriver driver;
    //WebDriverWait wait = new WebDriverWait(driver, 10);

    private String BaseUrl = Properties.getBaseAdminUrl();
    private String login = Properties.getLogin();
    private String password = Properties.getPassword();

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    public void PageLoad() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to(BaseUrl);
        driver.manage().window().maximize();
    }

    public void LoginTest() {
        String email = "email";
        sleep(1000);

        try {
            WebElement inputEmail = driver.findElement(By.id(email));
            inputEmail.sendKeys(login);
        } catch (Exception e) {
            addError(email + " input not found");
        }

        System.out.println("Log in succeeded");


        try {
            WebElement inputPassword = driver.findElement(By.name("passwd"));
            inputPassword.sendKeys(password);
        } catch (Exception e) {
            addError("password input not found");
        }

        try {
            WebElement submitBtn = driver.findElement(By.name("submitLogin"));
            submitBtn.submit();
        } catch (Exception e) {
            addError("submit button not found");
        }

    }
    public void LogOut() {
        sleep(1000);

        try {
            WebElement avatar = driver.findElement(By.className("employee_avatar_small"));
            avatar.click();
        } catch (Exception e) {
            addError("avatar not found");
        }



        try {
            Thread.sleep(1000);
            WebElement logoutBtn = driver.findElement(By.id("header_logout"));
            logoutBtn.click();
            Thread.sleep(1000);
            System.out.println("Log out succeeded");

        } catch (InterruptedException e) {
            addError("Logout button not found");
        }
    }


    public void quit() {
        driver.quit();
    }

    public void CheckMenu() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            //Thread.sleep(1500);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("subtab-AdminParentOrders")));
            WebElement ordersTab = driver.findElement(By.id("subtab-AdminParentOrders"));
            ordersTab.click();
            sleep(1500);
        } catch (NoSuchElementException e) {
            addError("Element with id = \"subtab-AdminParentOrders\" not found");
        }

        try { //checking the correctness of the "Orders" tab refresh
            //Thread.sleep(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h2")));
            WebElement webElement = driver.findElement(By.tagName("h2"));
            String check = webElement.getText();
            System.out.println(check);
            //Thread.sleep(1000);
            driver.navigate().refresh();
            //Thread.sleep(1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h2")));
            if (check.equals(driver.findElement(By.tagName("h2")).getText())) System.out.println("Refresh of the \"Orders\" tab succeeded");
        } catch (NoSuchElementException e) {
            addError("Refresh of the \"Orders\" tab is failed");
        }

        try {
            Thread.sleep(1000);
            WebElement catalogueTab = driver.findElement(By.id("subtab-AdminCatalog"));
            catalogueTab.click();
        } catch (Exception e) {
            addError("Element with id = \"subtab-AdminCatalog\" not found");
        }


        try { //checking the correctness of the "Catalogue" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.className("toolbar-icons"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.className("toolbar-icons")).getText())) System.out.println("Refresh of the \"Catalogue\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Catalogue\" tab failed");
        }

        /*try {
            Thread.sleep(1500);
            WebElement customersTab = driver.findElement(By.linkText("Клиенты"));
            customersTab.click();
        } catch (Exception e) {
            addError("Element with id = \"subtab-AdminParentCustomer\" not found");
        }


        try { //checking the correctness of the "Customers" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.className("panel-heading"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.className("panel-heading")).getText())) System.out.println("Refresh of the \"Customers\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Customers\" tab failed");
        }



        try {
            Thread.sleep(1000);
            WebElement supportTab = driver.findElement(By.id("subtab-AdminParentCustomerThreads"));
            supportTab.click();
        } catch (Exception e) {
            addError("Element with id = \"subtab-AdminParentCustomerThreads\" not found");
        }


        try { //checking the correctness of the "Support" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.className("textarea-autosize"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.className("textarea-autosize")).getText())) System.out.println("Refresh of the \"Support\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Support\" tab failed");
        }





        try {
            Thread.sleep(1000);
            WebElement statsTab = driver.findElement(By.id("subtab-AdminStats"));
            statsTab.click();
        } catch (Exception e) {
            addError("Element with id = \"subtab-AdminStats\" not found");
        }


        try { //checking the correctness of the "Statistics" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.name("submitDateDay"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.name("submitDateDay")).getText())) System.out.println("Refresh of the \"Statistics\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Statistics\" tab failed");
        }



        try {
            Thread.sleep(1000);
            WebElement modulesTab = driver.findElement(By.id("subtab-AdminParentModulesSf"));
            modulesTab.click();
        } catch (Exception e) {
            addError("Element with id = \"subtab-AdminParentModulesSf\" not found");
        }


        try { //checking the correctness of the "Modules" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.tagName("h2"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.tagName("h2")).getText())) System.out.println("Refresh of the \"Modules\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Modules\" tab failed");
        }




        try {
            Thread.sleep(1000);
            WebElement designTab = driver.findElement(By.linkText("Design"));
            designTab.click();
        } catch (Exception e) {
            addError("Element with id = \"subtab-AdminParentThemes\" not found");
        }


        try { //checking the correctness of the "Design" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.className("panel-heading"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.className("panel-heading")).getText())) System.out.println("Refresh of the \"Design\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Design\" tab failed");
        }



        try {
            Thread.sleep(1000);
            WebElement deliverTav = driver.findElement(By.linkText("Доставка"));
            deliverTav.click();
        } catch (Exception e) {
            addError("Element with text = \"Доставка\" not found");
        }


        try { //checking the correctness of the "Доставка" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.tagName("h2"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.tagName("h2")).getText())) System.out.println("Refresh of the \"Доставка\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Доставка\" tab failed");
        }

        try {
            Thread.sleep(1000);
            WebElement payMethTab = driver.findElement(By.linkText("Способ оплаты"));
            payMethTab.click();
        } catch (Exception e) {
            addError("Element with text = \"Способ оплаты\" not found");
        }


        try { //checking the correctness of the "Способ оплаты" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.tagName("h2"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.tagName("h2")).getText())) System.out.println("Refresh of the \"Способ оплаты\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Способ оплаты\" tab failed");
        }

        try {
            Thread.sleep(1000);
            WebElement InternationalTab = driver.findElement(By.linkText("International"));
            InternationalTab.click();
        } catch (Exception e) {
            addError("Element with text = \"International\" not found");
        }


        try { //checking the correctness of the "International" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.tagName("h2"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.tagName("h2")).getText())) System.out.println("Refresh of the \"International\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"International\" tab failed");
        }


        try {
            Thread.sleep(1000);
            WebElement shopParamTab = driver.findElement(By.linkText("Shop Parameters"));
            shopParamTab.click();
        } catch (Exception e) {
            addError("Element with text = \"Shop Parameters\" not found");
        }


        try { //checking the correctness of the "Shop Parameters" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.tagName("h2"));
            String check = webElement.getText();
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.tagName("h2")).getText())) System.out.println("Refresh of the \"Shop Parameters\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Shop Parameters\" tab failed");
        }

        try {
            Thread.sleep(1000);
            WebElement configTab = driver.findElement(By.linkText("Конфигурация"));
            configTab.click();

        } catch (Exception e) {
            addError("Element with text = \"Shop Parameters\" not found");
        }


        try { //checking the correctness of the "Конфигурация" tab refresh
            Thread.sleep(1000);
            WebElement webElement = driver.findElement(By.tagName("h2"));
            String check = webElement.getText();
            //System.out.println(check);
            Thread.sleep(1000);
            driver.navigate().refresh();
            Thread.sleep(1000);
            if (check.equals(driver.findElement(By.tagName("h2")).getText())) System.out.println("Refresh of the \"Конфигурация\" tab succeeded");

        } catch (Exception e) {
            addError("Refresh of the \"Конфигурация\" tab failed");
        }*/

    }

    Tests() {
        this.driver = getDriver();
    }
}
