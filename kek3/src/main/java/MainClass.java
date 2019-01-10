
import javafx.scene.Group;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import javax.naming.Name;
import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class MainClass {

    org.openqa.selenium.WebDriver driver;
    WebDriverWait wait;
    String name = "youqweqwe";
    String pass = "qwe123qwe";
    String goTo = "https://yandex.ru/";


    void loginName() throws InterruptedException {
        driver.findElement(By.xpath("//form[@class='passport-Domik-Form']//div[1]//label[1]//input[1]")).click();
        driver.findElement(By.xpath("//form[@class='passport-Domik-Form']//div[1]//label[1]//input[1]")).sendKeys(name);
    }

    void loginPassword() {
        driver.findElement(By.xpath("//form[@class='passport-Domik-Form']//div[2]//label[1]//input[1]")).click();
        driver.findElement(By.xpath("//form[@class='passport-Domik-Form']//div[2]//label[1]//input[1]")).sendKeys(pass);

    }

    void loginButton() throws InterruptedException {
        driver.findElement(By.xpath("//span[@class='passport-Button-Text']")).submit();
        Thread.sleep(6000);
    }

    void letter() {
        driver.findElement(By.xpath("//span[contains(@class, 'mail-ComposeButton-Text')]")).click();
    }


    void checkBoxArray(String mail) {
        List<WebElement> lables = driver.findElements(By.xpath("//span[contains(@class, '-FromText')]"));
        List<WebElement> set = driver.findElements(By.xpath("//span[contains(@class, 'nb-checkbox-flag ')]"));

        for (int i = 0; i < lables.size(); i++) {
            if (lables.get(i).getAttribute("title").contains(mail)) {
                set.get(i).click();
            }
        }
    }

    void buttonDelete() {
        driver.findElement(By.xpath("//span[contains(@class, '-delete')]")).click();
        if (driver.findElements(By.xpath("//div[@class=\"tooltip__description\"]")).isEmpty()) {

        } else {
            driver.findElement(By.xpath("//button[contains(@class, 'confirm-mops')]")).click();
        }
    }

    void buttonSend() {
        WebElement buttonSend = driver.findElement(By.xpath("//*[@id=\"nb-16\"]"));
        buttonSend.click();
    }

    void whoWrite() {
        WebElement who = driver.findElement(By.xpath("//div[@name='to']"));
        wait.until(ExpectedConditions.elementToBeClickable(who));
        who.sendKeys("youqweqwe@yandex.ru");
    }

    void topicWrite() {
        WebElement xex = driver.findElement(By.xpath("//input[@name='subj-ebc4b07a4211ab2bd162b0b25d60e4837e9f018e']"));
        wait.until(ExpectedConditions.elementToBeClickable(xex));
        xex.sendKeys("Очень Важная!");
    }

    void peremSet() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='nb-3']//*[@class='svgicon svgicon-mail--Settings']")));
        WebElement settings = driver.findElement(By.xpath("//a[@id='nb-3']//*[@class='svgicon svgicon-mail--Settings']"));
        Actions settAc = new Actions(driver);
        settAc.moveToElement(settings).click().build().perform();
    }

    void listArraySetting() {
        List<WebElement> set = driver.findElements(By.xpath("//div//div//span[contains(@class,'settings-popup-menu-item-content')]"));
        set.get(0).click();
    }

    void russOrEng() {
        if (driver.findElements(By.xpath("//span[contains(text(),'Time zone')]")).isEmpty()) {
            driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).click();
            driver.findElement(By.xpath("//img[@class='b-mail-icon b-mail-icon_lang-en']")).click();
        }
    }

    void loginName2() {
        driver.findElement(By.xpath("//*[@id=\"passp-field-login\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"passp-field-login\"]")).sendKeys(name);
    }

    void loginPassword2() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[contains(@class, '_size_m')]"))));
        //driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/form/div[1]/div/div[1]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"passp-field-passwd\"]")).sendKeys(pass);
    }

    void loginButton2() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/form/div[3]/button[1]")).submit();
    }

    void loginButton3() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div/form/div[2]/button")).submit();
    }



        @BeforeMethod (groups = {"BEF-1"})
        public void setUp() throws InterruptedException {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 90, 500);
            driver.get(goTo);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div[1]/div/a[1]"))));
            driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div[1]/div/a[1]")).click();

            if (driver.findElements(By.xpath("//form[@class='passport-Domik-Form']//div[2]//label[1]//input[1]")).isEmpty()) {
                loginName2();
                loginButton2();
                loginPassword2();
                loginButton3();
                //driver.get(goMail);
            } else {
                loginName();
                loginPassword();
                loginButton();
                //driver.get(goMail);
            }


        }

        @AfterMethod (groups = {"AFT-1"})
        public void close() throws InterruptedException {
            Thread.sleep(6000);
            driver.quit();
        }


        @Test(groups = {"SW-1"}) //Зайти в аккаунт и поменять язык с русского на английский
        public void firstTest() {

            peremSet();
            listArraySetting();
            russOrEng();
        }

        @Test(groups = {"MES-1"})
            //Зайти в аккаунт и отправить письмо без получателя и темы
        void secondTest() {
            //без получателя и темы выдаст ошибку
            letter();
            buttonSend();
        }

        @Test(groups = {"MES-2"})
            //Зайти в аккаунт и отправить письмо определенному получателю
        void thirdTest() {
            letter();
            whoWrite();
            buttonSend();
        }

        @Test(groups = {"MES-3"})
//Зайти в аккаунт и отправить письмо без получателя, но с темой
        void fourthTest() {
            //без получателя выдаст ошибку
            letter();
            topicWrite();
            buttonSend();
        }

        @Test(groups = {"MES-4"})
//Зайти в аккаунт и отправить письмо определенному получателю с определенной темой
        void fifthTest() {
            letter();
            whoWrite();
            topicWrite();
            buttonSend();
        }

        @Test(groups = {"DEL-1"})
            //Зайти в аккаунт и удалить все письма определенного характера
        void sixthTest() {
            checkBoxArray("youqweqwe");
            buttonDelete();
        }
    }
