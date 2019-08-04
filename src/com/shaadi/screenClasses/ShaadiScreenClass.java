package com.shaadi.screenClasses;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Shaadi screen class
 */
public class ShaadiScreenClass {

    private static final String[] WIN_RUNTIME = {"cmd.exe", "/C"};

    WebDriver driver;
    public By login = By.id("com.shaadi.android:id/signIn");
    public By userName = By.id("com.shaadi.android:id/username");
    public By password = By.id("com.shaadi.android:id/password");
    public By signIn = By.id("com.shaadi.android:id/SignInWithProgressbar");
    public By search = By.xpath("//hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView");

    public By martialStatus = By.id("com.shaadi.android:id/set_marital_status");
    public By searchEdit = By.id("com.shaadi.android:id/search_edit");
    public By searchLabel = By.id("com.shaadi.android:id/label");
    public By apply = By.id("com.shaadi.android:id/tv_apply");
    public By setReligion = By.id("com.shaadi.android:id/set_religion");
    public By displayLanguage = By.id("com.shaadi.android:id/set_display_language");
    public By setCaste = By.id("com.shaadi.android:id/set_caste");
    public By setCountry = By.id("com.shaadi.android:id/set_country");
    public By setState = By.id("com.shaadi.android:id/set_state");
    public By setCity = By.id("com.shaadi.android:id/set_city");
    public By setManglik = By.id("com.shaadi.android:id/set_manglik");
    public By searchBtn = By.id("com.shaadi.android:id/btn_search");
    public By searchResult = By.id("com.shaadi.android:id/textView1");
    public By modifySearch = By.id("com.shaadi.android:id/modifysearchbtn");



    public ShaadiScreenClass(WebDriver driver){
        this.driver = driver;
    }

    //Click on login button
    public void clickLogin(){
        driver.findElement(login).click();
    }

    //set username
    public void setUserName(String emailId){
        driver.findElement(userName).sendKeys(emailId);
    }

    //set passwrod
    public void setPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }

    //Click on login button
    public void clickSigIn(){
        driver.findElement(signIn).click();
    }

    //Click on search button
    public void clickSearch(){
        driver.findElement(search).click();
    }

    //Click on martial status button
    public void clickMartialStatus(){
        driver.findElement(martialStatus).click();
    }

    //Click on search edit text field
    public void clickSearchEdit(String txt){
        driver.findElement(searchEdit).sendKeys(txt);
    }

    //Click on search label button
    public void clickSearchLabel(){
        driver.findElement(searchLabel).click();
    }

    //Click on apply button
    public void clickApply(){
        driver.findElement(apply).click();
    }

    //Click on set religion button
    public void clickSetReligion(){
        driver.findElement(setReligion).click();
    }

    //Click on display language button
    public void clickDisplayLanguage(){
        driver.findElement(displayLanguage).click();
    }

    //Click on set caste button
    public void clickSetCaste(){
        driver.findElement(setCaste).click();
    }

    //Click on set country button
    public void clickSetCountry(){
        driver.findElement(setCountry).click();
    }

    //Click on set state button
    public void clickSetState(){
        driver.findElement(setState).click();
    }

    //Click on set city button
    public void clickSetCity(){
        driver.findElement(setCity).click();
    }

    //Click on manglik button
    public void clickSetManglik(){
        driver.findElement(setManglik).click();
    }

    //Click on search button
    public void clickSearchBtn(){
        driver.findElement(searchBtn).click();
    }

    public void loginToShaadi(String strUserName,String strPasword) throws InterruptedException {
        Thread.sleep(15000);
        this.clickLogin();
        //Fill user name
        this.setUserName(strUserName);
        //Fill password
        this.setPassword(strPasword);
        //Click Login button
        this.clickSigIn();
        Thread.sleep(15000);
        if(driver.findElement(By.xpath("//android.widget.Button")).isDisplayed()){
            driver.findElement(By.xpath("//android.widget.Button")).click();
        }
        Thread.sleep(3000);
        if(driver.findElement(By.xpath("//android.widget.Button")).isDisplayed()){
            driver.findElement(By.xpath("//android.widget.Button")).click();
        }
    }

    public void searchAndClick(String searchTxt){
        clickSearchEdit(searchTxt);
        clickSearchLabel();
        clickApply();
    }

    /**
     * Method to get results of windows commands in a list
     */
    public static List<String> executeCommand(String... command) {

        System.out.println("Command::----" + command[0] + "\n");
        System.out.print("command to run:: ");
        String[] allCommand;
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                allCommand = concat(WIN_RUNTIME, command);
            } else {
                allCommand = concat(new String[]{"/bin/sh", "-c"}, command);
            }

            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(allCommand);
            Thread.sleep(2000);

            List deviceList = IOUtils.readLines(process.getInputStream());
            List<String> line = new ArrayList<>();
            for (Object eachDevice : deviceList) {
                eachDevice = eachDevice.toString().substring(0, eachDevice.toString().indexOf("\t"));
                line.add(eachDevice.toString());
            }
            return line;
        } catch (Exception e) {
            return null;
        }
    }

    private static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
