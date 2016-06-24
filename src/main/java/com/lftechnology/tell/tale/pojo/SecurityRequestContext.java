package com.lftechnology.tell.tale.pojo;

import com.lftechnology.tell.tale.entity.User;

/**
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 * Jun 25, 2016
 * 
 */
public class SecurityRequestContext {

	private static ThreadLocal<User> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocalToken = new ThreadLocal<>();

    private SecurityRequestContext() {
    }

    public static User getCurrentUser() {
        return threadLocal.get();
    }
    
    public static void setCurrentUser(User user) {
        threadLocal.set(user);
    }

    public static void removeCurrentUser() {
        threadLocal.remove();
    }
    
    public static String getRandomKey() {
        return threadLocalToken.get();
    }

    public static void setRandomKey(String randomKey) {
        threadLocalToken.set(randomKey);
    }

    public static void removeRandomKey() {
        threadLocalToken.remove();
    }
}
