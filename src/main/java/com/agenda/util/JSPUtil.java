package com.agenda.util;

public class JSPUtil {

    public final static String viewPrefix = "/WEB-INF/view/";
    public final static String viewSuffix = ".jsp";

    public static String resolvePath(String s) {
        return viewPrefix + s + viewSuffix;
    }

}
