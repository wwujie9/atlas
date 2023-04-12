package com.base.atlas.common.util.encrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author CaiJie Pang
 * @since 2022/10/13
 */
public class AESEncrypt {

  private final static String ALGORITHM = "AES";

  public static byte[] encrypt(byte[] content, String keySeed)
      throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
      InvalidKeyException {
    SecretKeySpec secretKey = new SecretKeySpec(generateKey(keySeed), "AES");
    return doEncrypt(content, Cipher.ENCRYPT_MODE, secretKey);
  }

  public static byte[] decrypt(byte[] content, String keySeed)
      throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
      InvalidKeyException {
    SecretKeySpec secretKey = new SecretKeySpec(generateKey(keySeed), "AES");
    return doEncrypt(content, Cipher.DECRYPT_MODE, secretKey);
  }

  public static byte[] decryptWithoutKeyShifting(byte[] content, String keySeed)
      throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
      InvalidKeyException {
    SecretKeySpec secretKey = new SecretKeySpec(keySeed.getBytes(StandardCharsets.UTF_8), "AES");
    return doEncrypt(content, Cipher.DECRYPT_MODE, secretKey);
  }

  private static byte[] doEncrypt(byte[] contentArray, int mode, SecretKey secretKey)
      throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
      IllegalBlockSizeException, BadPaddingException {
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(mode, secretKey);
    return cipher.doFinal(contentArray);
  }

  public static String parseToHexStr(byte[] buffer) {
    StringBuilder sb = new StringBuilder();
    for (byte b : buffer) {
      String hex = Integer.toHexString(b & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      sb.append(hex.toUpperCase());
    }
    return sb.toString();
  }

  public static byte[] parseHexStrToByte(String src) {
    if (src == null || src.length() < 1) {return null;}
    byte[] result = new byte[src.length() / 2];
    for (int i = 0; i < src.length() / 2; i++) {
      int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);
      int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);
      result[i] = (byte) (high * 16 + low);
    }
    return result;
  }

  private static byte[] generateKey(String key) {
    byte[] dics = "1231g81f5478hhuig84h1f9q3f2x7ers".getBytes(StandardCharsets.UTF_8);
    byte[] bkeys = key.getBytes(StandardCharsets.UTF_8);
    byte[] keys = bkeys;
    int keylength = bkeys.length;
    if (keylength > 0 && keylength < 16) {
      keys = new byte[16];
      System.arraycopy(bkeys, 0, keys, 0, keylength);
      for (int i = keylength; i < 16; i++) {
        keys[i] = dics[i];
      }
    }

    if (keylength > 16 && keylength < 24) {
      keys = new byte[24];
      System.arraycopy(bkeys, 0, keys, 0, keylength);
      for (int i = keylength; i < 24; i++) {
        keys[i] = dics[i];
      }
    }

    if (keylength > 24 && keylength < 32) {
      keys = new byte[32];
      System.arraycopy(bkeys, 0, keys, 0, keylength);
      for (int i = keylength; i < 32; i++) {
        keys[i] = dics[i];
      }
    }

    if (keylength > 32) {
      keys = new byte[32];
      System.arraycopy(bkeys, 0, keys, 0, 32);
    }

    return keys;
  }
}
