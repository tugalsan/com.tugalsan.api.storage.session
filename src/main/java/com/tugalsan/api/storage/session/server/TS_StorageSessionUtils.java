package com.tugalsan.api.storage.session.server;

import java.util.*;
import javax.servlet.http.*;
import com.tugalsan.api.time.client.*;
import com.tugalsan.api.log.server.*;

public class TS_StorageSessionUtils {

    final private static TS_Log d = TS_Log.of(TS_StorageSessionUtils.class);

    public static HttpSession of(HttpServletRequest req) {
        d.ci("getInstance");
        var session = req.getSession(false);
        if (session == null) {
            session = req.getSession(true);
            session.setMaxInactiveInterval(TGS_TimeUtils.SECS_TIMEOUT_HOURS_WORK());
        }
        if (d.infoEnable) {
            var create = getCreationTime(session);
            var last = getLastAccessedTime(session);
            var diff = last.getSecondsDifference(create);
            d.cr("session.create, last, diff", create.toString_timeOnly(), last.toString_timeOnly(), diff);
        }
        return session;
    }

    public static TGS_Time getCreationTime(HttpSession session) {
        return TGS_Time.ofMillis(session.getCreationTime());
    }

    public static TGS_Time getLastAccessedTime(HttpSession session) {
        return TGS_Time.ofMillis(session.getLastAccessedTime());
    }

    public static void setObj(HttpServletRequest req, String name, Object value) {
        d.ci("set", "name", name, "value", value);
        of(req).setAttribute(name, value);
    }

    public static Object getObj(HttpServletRequest req, String name) {
        d.ci("get", "name", name);
        return of(req).getAttribute(name);
    }

    public static void setStr(HttpServletRequest req, String name, CharSequence value) {
        setObj(req, name, value.toString());
    }

    public static String getStr(HttpServletRequest req, String name) {
        var o = getObj(req, name);
        return o == null ? null : String.valueOf(o);
    }

    public static void del(HttpServletRequest req, String name) {
        d.ci("del", "name", name);
        of(req).removeAttribute(name);
    }
}
