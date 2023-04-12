package com.base.atlas.common.util;

import com.base.atlas.common.constant.Characters;

import java.util.UUID;

/**
 * @author CaiJie Pang
 * @since 2023/2/3
 */
public class IdUtils {

  private final static Boolean lock = true;

  public static String uuid() {
    return UUID.randomUUID().toString().replace("-", "").toUpperCase();
  }

  public static String createOneByNanoTime() {
    long currentTime;
    synchronized (lock) {
      currentTime = System.nanoTime();
    }
    StringBuilder binaryTime = new StringBuilder(Long.toBinaryString(currentTime));
    int capacity = binaryTime.length();
    int capacityCompletion = 5 - (capacity % 5);
    if (capacityCompletion != 5) {
      for (int i = 0; i < capacityCompletion; i ++) {
        binaryTime.insert(0, "0");
      }
    }
    StringBuilder uidBuilder = new StringBuilder();
    String index = "00000";
    while (binaryTime.length() > 0) {
      index = binaryTime.substring(0, 5);
      uidBuilder.append(Characters.RANDOM_CHARACTER_EXCLUDE_SPECIAL.charAt(Integer.parseInt(index, 2)));
      binaryTime.delete(0, 5);
    }
    return uidBuilder.toString();
  }
}
