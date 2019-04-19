//package fre.shown.tryboot.config;
//
//import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Radon Freedom
// * created at 2019.03.26 下午10:39
// */
//
//@Configuration
//public class ErrorConfig implements ErrorViewResolver {
//
//    @Override
//    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
//
//        Map<String, Object> newModel = new HashMap<>(16);
//        for (String key : model.keySet()) {
//            newModel.put(key, model.get(key));
//        }
//
//        newModel.put("status", status.value());
//        newModel.put("error", status.getReasonPhrase());
//
//        return new ModelAndView("error", Collections.unmodifiableMap(newModel));
//    }
//}
