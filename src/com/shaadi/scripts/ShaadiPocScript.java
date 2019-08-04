package com.shaadi.scripts;


import com.shaadi.screenClasses.ShaadiScreenClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 Login to the App with username "wer129@bankas.in" & password "test"
 Go to search Now
 Select marital status as never married & Click on apply
 Click on Religion>>Hindu & Click on apply
 Click on mother tongue>hindu & Click on apply
 Click on community>>96k kokanastha,agri & Click on apply
 Click on country>>india,usa & Click on apply
 Click on state>>maharashtra,Andhra Pradesh & Click on apply
 Click on city>>mumbai,Pune & & Click on apply
 Click on city>>mumbai,Pune & & Click on apply
 Click on manglik/chevai dosham>>no manglik & & Click on apply
 Click on search now
 Verify selected fields displayed on profile cards
 Verify the count displayed on search listing header according to profiles
 Now Scroll the profiles
 Open any profile & Click on connect now
 */

public class ShaadiPocScript {
    protected static AppiumDriver driver;
    private static AndroidDriver androidDriver;
    @BeforeClass
    public static void beforeClass() throws IOException {
        File apkFilePath = new File("./binary/shaadi.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion","4.4");
        capabilities.setCapability("deviceName","GT-I9060I");
        capabilities.setCapability("udid","4d00451cdedb3200");
        capabilities.setCapability("autoLaunch",true);
        capabilities.setCapability("newCommandTimeout","1000");
        capabilities.setCapability("app",apkFilePath.getCanonicalPath());
        capabilities.setCapability("clearSystemFiles",true);
        capabilities.setCapability("fullReset",true);
        capabilities.setCapability("autoGrantPermissions",true);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("appWaitActivity","com.shaadi.android.ui.achivement_splash.SplashActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        androidDriver = (AndroidDriver) driver;
    }

    @Test
    public void test() throws InterruptedException {
        ShaadiScreenClass shaadiScreenClass = new ShaadiScreenClass(androidDriver);
        shaadiScreenClass.loginToShaadi("wer129@bankas.in","test");

        shaadiScreenClass.clickSearch();
        Thread.sleep(3000);

        driver.context("NATIVE_APP");
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.5);
        int endx = (int) (size.width * 0.5);
        int starty = (int) (size.height * 0.8);
        int endy = (int) (size.height * 0.4);
        shaadiScreenClass.executeCommand("adb shell input touchscreen swipe "+startx+" "+starty+" "+endx+" "+endy);

        if(!driver.findElement(shaadiScreenClass.martialStatus).getText().equals("Never Married")){
            shaadiScreenClass.clickMartialStatus();
            shaadiScreenClass.searchAndClick("Never Married");
        }
        shaadiScreenClass.executeCommand("adb shell input touchscreen swipe "+startx+" "+starty+" "+endx+" "+endy);
        if(!driver.findElement(shaadiScreenClass.setReligion).getText().equals("Hindu")){
            shaadiScreenClass.clickSetReligion();
            shaadiScreenClass.searchAndClick("Hindu");
        }
        shaadiScreenClass.clickDisplayLanguage();
        shaadiScreenClass.searchAndClick("Hindi");

        shaadiScreenClass.clickSetCaste();
        shaadiScreenClass.searchAndClick("96k kokanastha");
        shaadiScreenClass.clickSetCaste();
        shaadiScreenClass.searchAndClick("Agri");

        shaadiScreenClass.executeCommand("adb shell input touchscreen swipe "+startx+" "+starty+" "+endx+" "+endy);
        shaadiScreenClass.clickSetCountry();
        shaadiScreenClass.searchAndClick("USA");

        shaadiScreenClass.clickSetState();
        shaadiScreenClass.searchAndClick("Maharashtra");

        shaadiScreenClass.clickSetState();
        shaadiScreenClass.searchAndClick("Andhra Pradesh");

        shaadiScreenClass.clickSetCity();
        shaadiScreenClass.searchAndClick("Mumbai");

        shaadiScreenClass.clickSetCity();
        shaadiScreenClass.searchAndClick("Pune");

        shaadiScreenClass.clickSetManglik();
        shaadiScreenClass.searchAndClick("No Mangliks");

        shaadiScreenClass.executeCommand("adb shell input touchscreen swipe "+startx+" "+starty+" "+endx+" "+endy);
        shaadiScreenClass.executeCommand("adb shell input touchscreen swipe "+startx+" "+starty+" "+endx+" "+endy);
        shaadiScreenClass.clickSearchBtn();
        Thread.sleep(7000);
        junit.framework.Assert.assertEquals(driver.findElement(shaadiScreenClass.searchResult).getText(),
                "NO MORE MATCHES");
        junit.framework.Assert.assertTrue(driver.findElement(shaadiScreenClass.modifySearch).isEnabled());
    }

    @AfterClass
    public static void afterClass(){
        driver.quit();
    }
}
