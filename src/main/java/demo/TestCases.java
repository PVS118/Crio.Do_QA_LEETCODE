package demo;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }


    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        // TestCase01: Verify the Leetcode Homepage URL
        //  Description: Verify that the Leetcode homepage URL contains "leetcode"
        // Test Steps:
        //Navigate to the Leetcode website ( https://leetcode.com/ ).

        driver.get("https://leetcode.com/");
        //Verify that the URL contains "leetcode"
        String currentURL = driver.getCurrentUrl();
        // String expectedURL = "https://leetcode.com/";
        if (currentURL.contains("leetcode")) {
            System.out.println("URL of the Leetcode homepage contains \"leetcode\"");
        } else {
            System.out.println("URL of the Leetcode homepage not  contains \"leetcode\"");
        }

        //  Expected Result: The URL of the Leetcode homepage contains "leetcode"
        //driver.get("https://www.google.com");
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() {
        System.out.println("Start Test case: testCase02");
        //  TestCase02: Verify Problem Set URL and Display First 5 Questions
        // Description: Retrieve details of the first 5 questions on the problems page..
        // Test Steps:
        //Click on the "Questions" link on the Leetcode homepage.

        driver.get("https://leetcode.com/");

        WebElement questions = driver.findElement(By.xpath("//*[@id=\"product\"]/div/div/div[1]/div/h2"));
        questions.click();

        WebElement viewQuestions = driver.findElement(By.xpath("//*[@id=\"product\"]/div/div/div[1]/div/a/p"));
        viewQuestions.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //Verify that you are on the problem set page, by checking the URL contains "problemset".
        driver.get("https://leetcode.com/problemset/all/");
        String currentURL = driver.getCurrentUrl();
        // String expectedURL = "https://leetcode.com/problemset/all/";
        if (currentURL.contains("problemset")) {
            System.out.println("you are on the problem set page");
        } else {
            System.out.println("you are not on the problem set page");
        }
        //no thanks pop up
        WebElement popUp = driver.findElement(By.xpath("//*[@id=\"radix-:r0:\"]/div[2]/button[1]"));
        popUp.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Retrieve the details of the first 5 questions and print them.
        for (int i = 2; i <= 6; i++) {
            WebElement cell = driver.findElement(By.xpath("((//div[@role='table'])[2]//div//div[@role='rowgroup']//div[@role='row'])[" + i + "]//div[@class='truncate']//a"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOf(cell));
            System.out.println(cell.getText());
        }
        //Make sure to check that the title of each question is correct and that you are selecting the questions from the first problem, i.e., "Two Sum".

        //Expected Result: The correct details of the problems are obtained and verified.

        System.out.println("end Test case: testCase02");
    }

    public void testCase03() {
        System.out.println("Start Test case: testCase03");
        //TestCase03: Verify the Two Sum problem
       // Description: Open the Two Sum problem and verify the URL.
        //Test Steps:
        //Open the first problem i.e, Two Sum.
        WebElement twoSum_link = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div[2]/div[2]/div[1]/div[5]/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div/div/a"));
        twoSum_link.click();
        //Verify that the URL contains "two-sum"
        String currentURL = driver.getCurrentUrl();
        if(currentURL.contains("two-sum")){
            System.out.println("the URL contains two-sum");
        }
        else {
            System.out.println("the URL does not contains two-sum");
        }


       // Expected Result: The URL of the problem contains "two-sum"
        System.out.println("end Test case: testCase03");

    }

public void testCase04() {
    System.out.println("Start Test case: testCase04");

    // TestCase04: Ensure the submissions tab displays "Register or Sign In"
    //**Description: ** Check the submissions tab and verify if it displays "Register or Sign In"
    //Test Steps:
    //Click on the submission tab.
    WebElement submitTab= driver.findElement(By.xpath("//*[@id=\"qd-content\"]/div[1]/div/div/div/div[1]/div/div/a[4]/div/span"));
    submitTab.click();
    //Verify that it displays "Register or Sign In"

    WebElement registerSignInMessage = driver.findElement(By.xpath("//*[@id=\"qd-content\"]/div[1]/div/div/div/div[2]/div/div/a")); // Change the locator as per your application
    String actualMessage = registerSignInMessage.getText();
    String expectedMessage = "Register or Sign In";

    // Check if the actual message matches the expected result
    if (actualMessage.equals(expectedMessage)) {
        System.out.println("Test Passed! The message is displayed correctly.");
    } else {
        System.out.println("Test Failed! The message is not displayed correctly.");
    }
    // Expected Result: The message "Register or Sign In" is displayed when you click on the submissions tab.
    System.out.println("end Test case: testCase04");
}
}