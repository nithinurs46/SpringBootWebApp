package com.springweb.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -458192421246911433L;
	private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest httpRequest, Exception ex) {
		if (ex != null){
	        ex.printStackTrace();
	        log.error("handleException()", ex.getMessage());
	    }
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("message", "Exception occured");
		mav.addObject("url", httpRequest.getRequestURL());
		mav.setViewName("error");
		return mav;
	}
	
	// can move below methods to respective controller, 
	// if we didn't  have any class defined with @ControllerAdvice

	@ExceptionHandler(RoleNotFoundException.class)
	public ModelAndView handleRoleException(HttpServletRequest httpRequest, Exception ex) {
		if (ex != null){
	        ex.printStackTrace();
	        log.error("handleRoleException()", ex.getMessage());
	    }
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("message", "Role unavailable");
		mav.addObject("url", httpRequest.getRequestURL());
		mav.setViewName("error");
		return mav;
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ModelAndView handleUserException(HttpServletRequest httpRequest, Exception ex) {
		if (ex != null){
	        ex.printStackTrace();
	    }
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("message", "User unavailable");
		mav.addObject("url", httpRequest.getRequestURL());
		mav.setViewName("error");
		return mav;
	}
}
