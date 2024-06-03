package io.testomat;

import com.codeborne.selenide.Configuration;

public class BaseTests {

    static {
        Configuration.baseUrl = "https://testomat.io";
    }

}
