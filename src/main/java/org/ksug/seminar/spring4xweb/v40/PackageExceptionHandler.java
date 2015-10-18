package org.ksug.seminar.spring4xweb.v40;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 패키지 한정판!!
 * 
 * @author honeymon
 *
 */
@ControllerAdvice(basePackages = { "org.ksug.seminar.spring4xweb.v40.controlleradvice" })
public class PackageExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView("static/templates/error/default.html");
        mav.addObject("url", req.getRequestURI());
        mav.addObject("message", e.getMessage());
        mav.addObject("handler", this.getClass().getName());
        return mav;
    }
}
