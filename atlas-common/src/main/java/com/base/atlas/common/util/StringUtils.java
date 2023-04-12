package com.base.atlas.common.util;

public final class StringUtils {
  public static final char SPACE = ' ';
  public static final char ASTERISK = '*';
  public static final char COMMA = ',';
  public static final char COLON = ':';
  public static final char OPEN_PARENTHESIS = '(';
  public static final char CLOSE_PARENTHESIS = ')';
  public static final char PERCENT = '%';
  public static final char UNDERLINE = '_';
  /**
   * The empty String {@code ""}.
   */
  public static final String EMPTY = "";
  /**
   * A String for linefeed LF ("\n").
   */
  public static final String LF = "\n";
  /**
   * A String for carriage return CR ("\r").
   */
  public static final String CR = "\r";
  /**
   * Represents a failed index search.
   */
  public static final int INDEX_NOT_FOUND = -1;

  private StringUtils() {}

  // Empty checks
  //-----------------------------------------------------------------------

  /**
   * <p>Checks if a CharSequence is empty ("") or null.</p>
   *
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("bob")     = false
   * StringUtils.isEmpty("  bob  ") = false
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is empty or null
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  /**
   * <p>Checks if a CharSequence is not empty ("") and not null.</p>
   *
   * <pre>
   * StringUtils.isNotEmpty(null)      = false
   * StringUtils.isNotEmpty("")        = false
   * StringUtils.isNotEmpty(" ")       = true
   * StringUtils.isNotEmpty("bob")     = true
   * StringUtils.isNotEmpty("  bob  ") = true
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is not empty and not null
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static boolean isNotEmpty(final CharSequence cs) {
    return !isEmpty(cs);
  }

  /**
   * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
   *
   * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
   *
   * <pre>
   * StringUtils.isBlank(null)      = true
   * StringUtils.isBlank("")        = true
   * StringUtils.isBlank(" ")       = true
   * StringUtils.isBlank("bob")     = false
   * StringUtils.isBlank("  bob  ") = false
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is null, empty or whitespace only
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static boolean isBlank(final CharSequence cs) {
    int strLen;
    if (cs == null || (strLen = cs.length()) == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
   *
   * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
   *
   * <pre>
   * StringUtils.isNotBlank(null)      = false
   * StringUtils.isNotBlank("")        = false
   * StringUtils.isNotBlank(" ")       = false
   * StringUtils.isNotBlank("bob")     = true
   * StringUtils.isNotBlank("  bob  ") = true
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is not empty and not null and not whitespace only
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static boolean isNotBlank(final CharSequence cs) {
    return !isBlank(cs);
  }

  // Left/Right/Mid
  //-----------------------------------------------------------------------

  /**
   * <p>Gets the leftmost {@code len} characters of a String.</p>
   *
   * <p>If {@code len} characters are not available, or the String is {@code null}, the String will be returned without
   * an exception. An empty String is returned if len is negative.</p>
   *
   * <pre>
   * StringUtils.left(null, *)    = null
   * StringUtils.left(*, -ve)     = ""
   * StringUtils.left("", *)      = ""
   * StringUtils.left("abc", 0)   = ""
   * StringUtils.left("abc", 2)   = "ab"
   * StringUtils.left("abc", 4)   = "abc"
   * </pre>
   *
   * @param str the String to get the leftmost characters from, may be null
   * @param len the length of the required String
   * @return the leftmost characters, {@code null} if null String input
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static String left(final String str, final int len) {
    if (str == null) {
      return null;
    }
    if (len < 0) {
      return EMPTY;
    }
    if (str.length() <= len) {
      return str;
    }
    return str.substring(0, len);
  }

  /**
   * <p>Gets the rightmost {@code len} characters of a String.</p>
   *
   * <p>If {@code len} characters are not available, or the String is {@code null}, the String will be returned
   * without an exception. An empty String is returned if len is negative.</p>
   *
   * <pre>
   * StringUtils.right(null, *)    = null
   * StringUtils.right(*, -ve)     = ""
   * StringUtils.right("", *)      = ""
   * StringUtils.right("abc", 0)   = ""
   * StringUtils.right("abc", 2)   = "bc"
   * StringUtils.right("abc", 4)   = "abc"
   * </pre>
   *
   * @param str the String to get the rightmost characters from, may be null
   * @param len the length of the required String
   * @return the rightmost characters, {@code null} if null String input
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static String right(final String str, final int len) {
    if (str == null) {
      return null;
    }
    if (len < 0) {
      return EMPTY;
    }
    if (str.length() <= len) {
      return str;
    }
    return str.substring(str.length() - len);
  }

  /**
   * <p>Gets {@code len} characters from the middle of a String.</p>
   *
   * <p>If {@code len} characters are not available, the remainder of the String will be returned without an exception.
   * If the String is {@code null}, {@code null} will be returned.
   * An empty String is returned if len is negative or exceeds the length of {@code str}.</p>
   *
   * <pre>
   * StringUtils.mid(null, *, *)    = null
   * StringUtils.mid(*, *, -ve)     = ""
   * StringUtils.mid("", 0, *)      = ""
   * StringUtils.mid("abc", 0, 2)   = "ab"
   * StringUtils.mid("abc", 0, 4)   = "abc"
   * StringUtils.mid("abc", 2, 4)   = "c"
   * StringUtils.mid("abc", 4, 2)   = ""
   * StringUtils.mid("abc", -2, 2)  = "ab"
   * </pre>
   *
   * @param str the String to get the characters from, may be null
   * @param pos the position to start from, negative treated as zero
   * @param len the length of the required String
   * @return the middle characters, {@code null} if null String input
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static String mid(final String str, int pos, final int len) {
    if (str == null) {
      return null;
    }
    if (len < 0 || pos > str.length()) {
      return EMPTY;
    }
    if (pos < 0) {
      pos = 0;
    }
    if (str.length() <= pos + len) {
      return str.substring(pos);
    }
    return str.substring(pos, pos + len);
  }

  // SubStringAfter/SubStringBefore
  //-----------------------------------------------------------------------

  /**
   * <p>Gets the substring before the first occurrence of a separator.
   * The separator is not returned.</p>
   *
   * <p>A {@code null} string input will return {@code null}.
   * An empty ("") string input will return the empty string.
   * A {@code null} separator will return the input string.</p>
   *
   * <p>If nothing is found, the string input is returned.</p>
   *
   * <pre>
   * StringUtils.substringBefore(null, *)      = null
   * StringUtils.substringBefore("", *)        = ""
   * StringUtils.substringBefore("abc", "a")   = ""
   * StringUtils.substringBefore("abcba", "b") = "a"
   * StringUtils.substringBefore("abc", "c")   = "ab"
   * StringUtils.substringBefore("abc", "d")   = "abc"
   * StringUtils.substringBefore("abc", "")    = ""
   * StringUtils.substringBefore("abc", null)  = "abc"
   * </pre>
   *
   * @param str the String to get a substring from, may be null
   * @param separator the String to search for, may be null
   * @return the substring before the first occurrence of the separator, {@code null} if null String input
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static String substringBefore(final String str, final String separator) {
    if (isEmpty(str) || separator == null) {
      return str;
    }
    if (separator.isEmpty()) {
      return EMPTY;
    }
    final int pos = str.indexOf(separator);
    if (pos == INDEX_NOT_FOUND) {
      return str;
    }
    return str.substring(0, pos);
  }

  /**
   * <p>Gets the substring after the first occurrence of a separator.
   * The separator is not returned.</p>
   *
   * <p>A {@code null} string input will return {@code null}.
   * An empty ("") string input will return the empty string.
   * A {@code null} separator will return the empty string if the input string is not {@code null}.</p>
   *
   * <p>If nothing is found, the empty string is returned.</p>
   *
   * <pre>
   * StringUtils.substringAfter(null, *)      = null
   * StringUtils.substringAfter("", *)        = ""
   * StringUtils.substringAfter(*, null)      = ""
   * StringUtils.substringAfter("abc", "a")   = "bc"
   * StringUtils.substringAfter("abcba", "b") = "cba"
   * StringUtils.substringAfter("abc", "c")   = ""
   * StringUtils.substringAfter("abc", "d")   = ""
   * StringUtils.substringAfter("abc", "")    = "abc"
   * </pre>
   *
   * @param str the String to get a substring from, may be null
   * @param separator the String to search for, may be null
   * @return the substring after the first occurrence of the separator, {@code null} if null String input
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static String substringAfter(final String str, final String separator) {
    if (isEmpty(str)) {
      return str;
    }
    if (separator == null) {
      return EMPTY;
    }
    final int pos = str.indexOf(separator);
    if (pos == INDEX_NOT_FOUND) {
      return EMPTY;
    }
    return str.substring(pos + separator.length());
  }

  /**
   * <p>Gets the substring before the last occurrence of a separator.
   * The separator is not returned.</p>
   *
   * <p>A {@code null} string input will return {@code null}.
   * An empty ("") string input will return the empty string.
   * An empty or {@code null} separator will return the input string.</p>
   *
   * <p>If nothing is found, the string input is returned.</p>
   *
   * <pre>
   * StringUtils.substringBeforeLast(null, *)      = null
   * StringUtils.substringBeforeLast("", *)        = ""
   * StringUtils.substringBeforeLast("abcba", "b") = "abc"
   * StringUtils.substringBeforeLast("abc", "c")   = "ab"
   * StringUtils.substringBeforeLast("a", "a")     = ""
   * StringUtils.substringBeforeLast("a", "z")     = "a"
   * StringUtils.substringBeforeLast("a", null)    = "a"
   * StringUtils.substringBeforeLast("a", "")      = "a"
   * </pre>
   *
   * @param str the String to get a substring from, may be null
   * @param separator the String to search for, may be null
   * @return the substring before the last occurrence of the separator, {@code null} if null String input
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static String substringBeforeLast(final String str, final String separator) {
    if (isEmpty(str) || isEmpty(separator)) {
      return str;
    }
    final int pos = str.lastIndexOf(separator);
    if (pos == INDEX_NOT_FOUND) {
      return str;
    }
    return str.substring(0, pos);
  }

  /**
   * <p>Gets the substring after the last occurrence of a separator.
   * The separator is not returned.</p>
   *
   * <p>A {@code null} string input will return {@code null}.
   * An empty ("") string input will return the empty string.
   * An empty or {@code null} separator will return the empty string if the input string is not {@code null}.</p>
   *
   * <p>If nothing is found, the empty string is returned.</p>
   *
   * <pre>
   * StringUtils.substringAfterLast(null, *)      = null
   * StringUtils.substringAfterLast("", *)        = ""
   * StringUtils.substringAfterLast(*, "")        = ""
   * StringUtils.substringAfterLast(*, null)      = ""
   * StringUtils.substringAfterLast("abc", "a")   = "bc"
   * StringUtils.substringAfterLast("abcba", "b") = "a"
   * StringUtils.substringAfterLast("abc", "c")   = ""
   * StringUtils.substringAfterLast("a", "a")     = ""
   * StringUtils.substringAfterLast("a", "z")     = ""
   * </pre>
   *
   * @param str the String to get a substring from, may be null
   * @param separator the String to search for, may be null
   * @return the substring after the last occurrence of the separator, {@code null} if null String input
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static String substringAfterLast(final String str, final String separator) {
    if (isEmpty(str)) {
      return str;
    }
    if (isEmpty(separator)) {
      return EMPTY;
    }
    final int pos = str.lastIndexOf(separator);
    if (pos == INDEX_NOT_FOUND || pos == str.length() - separator.length()) {
      return EMPTY;
    }
    return str.substring(pos + separator.length());
  }

  // Substring between
  //-----------------------------------------------------------------------

  /**
   * <p>Gets the String that is nested in between two instances of the same String.</p>
   *
   * <p>A {@code null} input String returns {@code null}.
   * A {@code null} tag returns {@code null}.</p>
   *
   * <pre>
   * StringUtils.substringBetween(null, *)            = null
   * StringUtils.substringBetween("", "")             = ""
   * StringUtils.substringBetween("", "tag")          = null
   * StringUtils.substringBetween("tagabctag", null)  = null
   * StringUtils.substringBetween("tagabctag", "")    = ""
   * StringUtils.substringBetween("tagabctag", "tag") = "abc"
   * </pre>
   *
   * @param str the String containing the substring, may be null
   * @param tag the String before and after the substring, may be null
   * @return the substring, {@code null} if no match
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static String substringBetween(final String str, final String tag) {
    return substringBetween(str, tag, tag);
  }

  /**
   * <p>Gets the String that is nested in between two Strings.
   * Only the first match is returned.</p>
   *
   * <p>A {@code null} input String returns {@code null}.
   * A {@code null} open/close returns {@code null} (no match).
   * An empty ("") open and close returns an empty string.</p>
   *
   * <pre>
   * StringUtils.substringBetween("wx[b]yz", "[", "]") = "b"
   * StringUtils.substringBetween(null, *, *)          = null
   * StringUtils.substringBetween(*, null, *)          = null
   * StringUtils.substringBetween(*, *, null)          = null
   * StringUtils.substringBetween("", "", "")          = ""
   * StringUtils.substringBetween("", "", "]")         = null
   * StringUtils.substringBetween("", "[", "]")        = null
   * StringUtils.substringBetween("yabcz", "", "")     = ""
   * StringUtils.substringBetween("yabcz", "y", "z")   = "abc"
   * StringUtils.substringBetween("yabczyabcz", "y", "z")   = "abc"
   * </pre>
   *
   * @param str the String containing the substring, may be null
   * @param open the String before the substring, may be null
   * @param close the String after the substring, may be null
   * @return the substring, {@code null} if no match
   */
  // From org.apache.commons.lang3.StringUtils, under Apache License 2.0
  public static String substringBetween(final String str, final String open, final String close) {
    if (str == null || open == null || close == null) {
      return null;
    }
    final int start = str.indexOf(open);
    if (start != INDEX_NOT_FOUND) {
      final int end = str.indexOf(close, start + open.length());
      if (end != INDEX_NOT_FOUND) {
        return str.substring(start + open.length(), end);
      }
    }
    return null;
  }
}
