package crud.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {
    
	public ModelAndView processUnauthenticatedException(NativeWebRequest request,UnauthorizedException e) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("ex",e);
		mv.setViewName("login");
		return mv;
	}
}
