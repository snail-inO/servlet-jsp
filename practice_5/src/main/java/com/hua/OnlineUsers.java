package com.hua;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUsers implements HttpSessionListener {
	public static int counter = 0;
	public static Set<User> users = new HashSet<User>();
	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		OnlineUsers.counter++;
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		OnlineUsers.counter--;
		OnlineUsers.users.remove(se.getSession().getAttribute("user"));
	}
}
