package com.stormphoenix.ogit.utils;

public class Constants {
    private static String baseURL = "http://test.com";
    static String user = "879479119";
    static String repo = "Github-Mobile";

    public static String getRepoURL (String user, String repo) {
        return baseURL + "/repo/" + user + "/" + repo;
    }

    public static Boolean isHybridPage (String url) {
        if (url.startsWith(baseURL)) {
            return true;
        }
        else return false;
    }

    public static String getUserURL (String user) {
        return baseURL + "/user/" + user;
    }

    public static String getOrgURL (String org) {
        return baseURL + "/org/" + org;
    }
}
