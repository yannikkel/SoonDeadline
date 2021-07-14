package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
  private SelenideElement verificationField = $("input[name='code']");
  private SelenideElement verifyButton = $("[data-test-id=action-verify]");

  public DashboardPage verify(String code) {
    verificationField.setValue(code);
    verifyButton.click();
    return new DashboardPage();
  }
}
