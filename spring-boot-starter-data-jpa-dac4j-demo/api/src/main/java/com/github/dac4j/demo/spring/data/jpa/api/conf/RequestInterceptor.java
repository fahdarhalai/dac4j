package com.github.dac4j.demo.spring.data.jpa.api.conf;

import com.github.dac4j.demo.spring.data.jpa.service.UserService;
import com.github.dac4j.spring.data.jpa.core.extractor.SourceValueExtractorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class RequestInterceptor implements SourceValueExtractorInterface {

    @Autowired
    private UserService userService;

    @Override
    public String getSourceValue() {
        Optional<HttpServletRequest> request =
                Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .map(ServletRequestAttributes::getRequest);

        return request
                .map(httpServletRequest -> httpServletRequest.getHeader("username"))
                .map(s -> userService.findByUsername(s))
                .map(userDto -> String.valueOf(userDto.getId()))
                .orElse(String.valueOf(1));
    }
}
