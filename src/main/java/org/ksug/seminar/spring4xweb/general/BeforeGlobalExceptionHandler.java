package org.ksug.seminar.spring4xweb.general;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 4.0 이전 @ControllerAdvice
 * 
 * @author honeymon
 *
 */
@ControllerAdvice
public class BeforeGlobalExceptionHandler extends
        ResponseEntityExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ModelAndView defaultErorHandler(HttpServletRequest req, Exception e)
            throws Exception {
        if (null != AnnotationUtils.findAnnotation(e.getClass(),
                ResponseStatus.class)) {
            throw e;
        }

        ModelAndView mav = new ModelAndView(
                "/static/templates/error/default.html");
        mav.addObject("url", req.getRequestURI());
        mav.addObject("message", e.getMessage());
        return mav;
    }

    public static class GlobalException extends Exception {
        private static final long serialVersionUID = 895362567863801024L;

        public GlobalException(String msg) {
            super(msg);
        }
    }
}
