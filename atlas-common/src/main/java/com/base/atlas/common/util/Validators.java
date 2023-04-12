package com.base.atlas.common.util;

/**
 * @author CaiJie Pang
 * @since 2023/2/3
 */
public class Validators {

  private static final String PHONE_NUMBER_RULE = "^1[3-9]\\d{9}$";

  private static final String MAIL_RULE = "[a-zA-Z0-9]\\w+@[a-zA-Z0-9]+\\.(cn|com|com.cn|net|gov)+";

  private boolean result = true;

  private boolean executed = false;

  private Validators(boolean pass) {
    this.result = pass;
  }

  public static Validators validatePhoneNumber(String phoneNumber) {
    return validate(phoneNumber, PHONE_NUMBER_RULE);
  }

  public static Validators validateMail(String mail) {
    return validate(mail, MAIL_RULE);
  }

  private static Validators validate(String source, String rule) {
    if (source == null) {
      return new Validators(false);
    }
    return new Validators(source.matches(rule));
  }

  public Validators onValidationPass(Executor executor) {
    if (this.result && !this.executed) {
      executor.execute();
      this.executed = true;
    }
    return this;
  }

  public Validators onValidationFailed(Executor executor) {
    if (!this.result && !this.executed) {
      executor.execute();
      this.executed = true;
    }
    return this;
  }

  public interface Executor {
    void execute();
  }
}
