package com.base.atlas.common.util;

import java.util.Collection;

public final class CollectionUtils {
  private CollectionUtils() {}

  /**
   * Null-safe check if the specified collection is empty.
   * <p>
   * Null returns true.
   * </p>
   *
   * @param coll  the collection to check, may be null
   * @return true if empty or null
   */
  // From org.apache.commons.collections4.CollectionUtils,  under Apache License 2.0
  public static boolean isEmpty(final Collection<?> coll) {
    return coll == null || coll.isEmpty();
  }

  /**
   * Null-safe check if the specified collection is not empty.
   * <p>
   * Null returns false.
   * </p>
   *
   * @param coll  the collection to check, may be null
   * @return true if non-null and non-empty
   */
  // From org.apache.commons.collections4.CollectionUtils,  under Apache License 2.0
  public static boolean isNotEmpty(final Collection<?> coll) {
    return !isEmpty(coll);
  }
}
