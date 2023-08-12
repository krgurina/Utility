package JUnitTest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/*
* для JUnit тестов надо подключить
* File -> Project Structure -> Libraries -> + -> Maven ->
* org.testng:testng:7.7.0
* */

/*
* 7 лаба
* */
public class Test {
    @BeforeSuite
    void startSuite() {
        System.out.println("------ работает BeforeSuite ------");
    }
    @AfterSuite
    void endSuite() {
        System.out.println("------ работает AfterSuite ------");
    }

}
