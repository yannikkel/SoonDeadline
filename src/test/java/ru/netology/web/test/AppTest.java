package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

class AppTest {

    @AfterAll
    static void clearDB() throws SQLException {
        DataHelper.clearDB();
    }

    @AfterEach
    void tearDown() throws SQLException {
        DataHelper.clearCodeAuth();
    }

    @Test
    void shouldAuth() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        loginPage.enterLogin(authInfo);
        loginPage.enterPassword(authInfo);
        val verificationPage = loginPage.confirmAuth();
        val verificationCode = DataHelper.getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldBlockAuth() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getWrongAuthInfo();
        loginPage.enterLogin(authInfo);
        loginPage.enterPassword(authInfo);
        loginPage.confirmNotAuth();
        loginPage.enterPassword(authInfo);
        loginPage.confirmNotAuth();
        loginPage.enterPassword(authInfo);
        loginPage.confirmNotAuth();
        loginPage.checkSystemBlocked();
    }
}
