package Tests;

import Utils.BaseScript;

/**
 * Created by User on 01.04.2017.
 */
public class MainTest extends BaseScript{
    public static void main(String[] args) {
        System.out.println(getBROWSER());
        Tests tests = new Tests();
        tests.PageLoad();
        tests.LoginTest();
        tests.CheckMenu();
        //tests.LogOut();
        //tests.quit();
    }
}
