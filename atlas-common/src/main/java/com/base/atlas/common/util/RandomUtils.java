package com.base.atlas.common.util;

import java.util.Random;

/**
 * @author CaiJie Pang
 * @since 2023/2/1
 */
public class RandomUtils {

  private static final String BUCKET_NUMBER = "0123456789";

  private static final String BUCKET_NUMBER_AND_LETTERS = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private RandomUtils() {}

  /**
   * 获取纯数字的随机验证码
   */
  public static String getNumberCaptcha(int length) {
    StringBuilder result = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < length; i ++) {
      int index = random.nextInt(BUCKET_NUMBER.length());
      result.append(BUCKET_NUMBER.charAt(index));
    }

    return result.toString();
  }

  /**
   * 获取字母+数字组合的随机验证码
   */
  public static String getCompositeCaptcha(int length) {
    StringBuilder result = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < length; i ++) {
      int index = random.nextInt(BUCKET_NUMBER_AND_LETTERS.length());
      result.append(BUCKET_NUMBER_AND_LETTERS.charAt(index));
    }

    return result.toString();
  }

  /**
   * 获取随机字符串
   *
   * @param num
   * @return
   */
  public static String getRandomString(Integer num) {
    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < num; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }

  /**
   * 获取随机数值
   *
   * @param num
   * @return
   */
  public static String getRandomNum(Integer num) {
    String base = "0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < num; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }


}
