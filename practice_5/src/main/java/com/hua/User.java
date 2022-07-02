package com.hua;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionListener;

public class User implements HttpSessionBindingListener{
	private String name;
	private String data;
	private Date lastLogin;
	private String browser;
	
	public User(String name) {
		this.name = name;
	}
	
	public void valueBound(HttpSessionBindingEvent event) {
		this.data = lastLogin + "<br>" + name + ", " + browser;
	}
	
	public String getData() {
		return data;
	}
	
	public String getName() {
		return name;
	}
	
	public User refreshTime() {
		this.lastLogin = new Date();
		return this;
	}
	
	public User setBrowser(String browser) {
		this.browser = browser;
		return this;
	}
}
