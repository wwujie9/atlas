package com.base.atlas.session;

/**
 * @author CaiJie Pang
 * @since 2023/1/26
 */
public class SessionUtil {

  private static final ThreadLocal<UserSession> userSessionThreadLocal = new ThreadLocal<>();

  public static void setUserSession(UserSession userSession) {
    if (userSession == null) {
      return;
    }
    userSessionThreadLocal.set(userSession);
  }

  public static String getCurrentUserId() {
    UserSession session = getSession();
    if (session == null || session.getUserId() == null) {
      return null;
    }
    return String.valueOf(session.getUserId());
  }

  public static UserSession getSession() {
    return userSessionThreadLocal.get();
  }
}
