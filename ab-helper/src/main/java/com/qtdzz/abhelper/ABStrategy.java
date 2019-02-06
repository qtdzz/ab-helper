package com.qtdzz.abhelper;

import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import java.util.Random;
import java.util.UUID;

public class ABStrategy {
  private static final ABStrategy INSTANCE = new ABStrategy();
  private static final String AB_VARIANT_SESSION_ATTRIBUTE = "AB_VARIANT_CHOICE";
  private static final String AB_USER_ID = "AB_CURRENT_USER_ID";

  private static final String COOKIE_AB_USERID = "_abhelper_user_id";
  private static final String COOKIE_AB_CHOICE_PREFIX = "_abhelper_choice_";

  private static final int MAXAGE = 2 * 7 * 24 * 2600;
  private final Object lock = new Object();
  private final Random random = new Random();

  private ABStrategy() {
    // no op
  }

  public static ABStrategy getInstance() {
    return INSTANCE;
  }

  public <T> T getVariant(ABOptions<T> options) {
    int choice = getChoice(options);
    return options.getAb()[choice];
  }

  private <T> int getChoice(ABOptions<T> options) {
    String id = options.getId();
    String attributeName = COOKIE_AB_CHOICE_PREFIX + id;

    String choice = getCookie(attributeName);
    VaadinSession vaadinSession = VaadinSession.getCurrent();
    if (StringUtils.isNotBlank(choice) && StringUtils.isNumeric(choice) && Integer.valueOf(choice) < options.getAb().length) {
      vaadinSession.setAttribute(attributeName, null);
      return Integer.valueOf(choice);
    }
    vaadinSession.lock();
    Integer newChoice;
    try {
      newChoice = (Integer) vaadinSession
              .getAttribute(attributeName);
      if (newChoice == null) {
        newChoice = random.nextInt(options.getAb().length);
        vaadinSession.setAttribute(attributeName,
                newChoice);
      }
      setCookie(attributeName, newChoice.toString());
      return newChoice;

    } finally {
      vaadinSession.unlock();
    }
  }

  private String getUserId() {
    String userId = getCookie(COOKIE_AB_USERID);
    VaadinSession vaadinSession = VaadinSession.getCurrent();
    if (StringUtils.isNotBlank(userId)) {
      vaadinSession.setAttribute(COOKIE_AB_USERID, null);
      return userId;
    }
    vaadinSession.lock();
    String userIdFromSession;
    try {
      userIdFromSession = (String) vaadinSession.getAttribute(COOKIE_AB_USERID);
      if (StringUtils.isBlank(userIdFromSession)) {
        userIdFromSession = UUID.randomUUID().toString();
      }
      setCookie(COOKIE_AB_USERID, userIdFromSession);
      vaadinSession.setAttribute(COOKIE_AB_USERID, userIdFromSession);
      return userIdFromSession;
    } finally {
      vaadinSession.unlock();
    }
  }

  private void setCookie(String cookieName, String cookieValue) {
    Cookie cookie = new Cookie(cookieName, cookieValue);
    cookie.setMaxAge(MAXAGE);
    VaadinService.getCurrentResponse().addCookie(cookie);
  }

  private String getCookie(String cookieName) {
    Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
    if (cookies == null) {
      return null;
    }
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals(cookieName)) {
        return cookie.getValue();
      }
    }
    return null;
  }
}
