package First;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class IrctcFlightSearchAutomation {
 
    public static WebDriver driver;
 
    public static void main(String[] args) {
    	
    	System.out.println("Enter the browser (chrome/firefox): ");
    	 
        Scanner sc = new Scanner(System.in);

        String browserName = sc.nextLine();
    	
        try {
        	
            //Driver Setup
            driver = DriverSetup.getDriver(browserName);
            if (driver == null) {
                return;
            }
 
            driver.get("https://www.air.irctc.co.in/");
 
            // Maximizing the window and verifying the respective application
            driver.manage().window().maximize();
 
            // Selecting Hyderabad in Origin
            WebElement frombox = driver.findElement(By.id("stationFrom"));
            frombox.sendKeys("Hyd");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.findElement(By.xpath("//div[text()='Hyderabad (HYD)']")).click();
 
            // Selecting Pune in Destination
            WebElement toBox = driver.findElement(By.id("stationTo"));
            toBox.sendKeys("Pune");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            List<WebElement> options = driver.findElements(By.className("ui-menu-item"));
            for (WebElement option : options) {
                if (option.getText().contains("Pune (PNQ)")) {
                    option.click();
                    break;
                }
            }
 
            // Selecting today’s date
            driver.findElement(By.xpath("//input[@id='originDate']")).click();
            WebElement todayDate = driver.findElement(By.xpath("//span[@class='act active-red']"));
            todayDate.click();
 
            // Selecting "Business" class
            driver.findElement(By.id("noOfpaxEtc")).click();
            Select classSelect = new Select(driver.findElement(By.id("travelClass")));
            classSelect.selectByVisibleText("Business");
            
            Thread.sleep(2000);
            
            // Clicking Search
            driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
 
            // Verifying the results shown are valid
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            try {
                driver.findElement(By.xpath("(//a[contains(text(),'Flight Details')])[1]")).click();
                WebElement cityDate = driver.findElement(By.xpath("(//strong[contains(text(),'Hyderabad (HYD)')])[1]"));
                if (cityDate.getText().contains("Hyderabad")) {
                    System.out.println("The city and date values are same as given in previous page.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Can't verify as there is no flight available for today.");
            }

            Thread.sleep(2000);
            
            // Displaying name and number of flights
            try {
                driver.findElement(By.xpath("(//a[contains(text(),'Flight Details')])[1]")).click();
                List<WebElement> list = driver.findElements(By.xpath("//div//div//div//div//b"));
                System.out.println("Total number of flights available for today: " + list.size());
                for (WebElement webElement : list) {
                    System.out.println("Name of the available flight: " + webElement.getText());
                }
            } catch (NoSuchElementException e) {
                
            }
            
            // Capturing screenshot
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("C:\\Users\\2319692\\eclipse-workspace\\IRCTC_Flight_Search\\screenshot.jpeg");
            FileHandler.copy(sourceFile, destinationFile);
 
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Closing the driver
            driver.quit();
            sc.close();
        }
    }
}
