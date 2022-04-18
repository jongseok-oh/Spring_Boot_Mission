package dev.jong.demo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jong.demo.exception.BaseException;
import dev.jong.demo.exception.ErrorResponseDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PostExceptionResolver extends AbstractHandlerExceptionResolver {
    @Override
    protected ModelAndView doResolveException(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        logger.debug(ex.getClass());
//        if (ex instanceof BaseException){
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            try{
//                response.getOutputStream().print(
//                        new ObjectMapper().writeValueAsString(
//                                new ErrorResponseDto("in resolver, message: " + ex.getMessage())
//                        )
//                );
//                response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//                return new ModelAndView();
//            }catch (IOException e){
//                logger.warn("Handling exception cause exception: {}", e);
//            }
//        }
        return null;
    }
}
