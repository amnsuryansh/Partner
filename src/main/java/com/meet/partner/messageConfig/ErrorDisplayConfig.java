package com.meet.partner.messageConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

@Component
@PropertySource("classpath:errormessage.properties")
public class ErrorDisplayConfig {
    @Autowired
    private Environment env;

    public String getProperty(String property) {
        if(null != property){ return env.getProperty(property); }
        return null;
    }

    public String getProperty(String property, List<Object> args) {
        String format = MessageFormat.format(getProperty(property), args.toArray());
        return format;
    }

}
