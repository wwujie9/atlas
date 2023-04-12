package com.base.atlas.common.util.encrypt;

import com.base.atlas.common.util.StringUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author CaiJie Pang
 * @since 2023/2/3
 */
public class MD5Encrypt {

  public static String encrypt(String source, int... iterations) {
    if (StringUtils.isEmpty(source)) {
      return null;
    }

    byte[] md5Digset = encryptToByte((source).getBytes(), iterations);
    if (md5Digset == null) {
      return null;
    }

    char[] hexDigest = Hex.encodeHex(md5Digset);
    return String.valueOf(hexDigest);
  }

  private static byte[] encryptToByte(byte[] source, int... iterations) {
    try {

      MessageDigest md5Digest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
      md5Digest.update(source);
      if (iterations != null && iterations.length > 0) {
        int iteration = iterations[0];
        if (iteration > 1) {
          for (int i = 0; i < iteration; i ++) {
            md5Digest.update(source);
          }
        }
      }
      return md5Digest.digest();
    } catch (NoSuchAlgorithmException e) {
      // this exception would never occur
      return null;
    }
  }
}
