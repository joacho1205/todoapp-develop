package todoapp.todoapp_develop.global.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    private static final String USER_ID = "USER_ID";

    public static void setLoginUserId(HttpSession session, Long userId) {
        session.setAttribute(USER_ID, userId);
    }

    public static Long getLoginUserId(HttpSession session) {
        return (Long) session.getAttribute(USER_ID);
    }

    public static void logout(HttpSession session) {
        session.removeAttribute(USER_ID);
    }
}
