package io.testomat.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.TextCheck;

public class BaseTests {

    static {
        Configuration.baseUrl = "https://testomat.io";
        Configuration.browserSize = "1440*900";
        Configuration.textCheck = TextCheck.PARTIAL_TEXT;
    }

}
