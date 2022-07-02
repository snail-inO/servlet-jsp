package com.hua;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncoderWrapper extends HttpServletRequestWrapper{
	public EncoderWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		return Optional.ofNullable(getRequest().getParameter(name))
				.map(p -> p.replace("<", "&lt").replace(">", "&gt"))
				name.replaceAll("[b]", "")
				.orElse(null);
	}
}
