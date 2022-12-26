package com.alexandrov;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    static final String UrlKinopoisk = "https://www.kinopoisk.ru/";
    static final String UrlLamoda = "https://www.lamoda.ru/";
    static final String UrlDemoQa = "https://demoqa.com/text-box";

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }
}
