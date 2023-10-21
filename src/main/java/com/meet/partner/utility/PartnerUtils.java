package com.meet.partner.utility;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class PartnerUtils {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");

    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public ObjectMapper mapper;

    @Autowired
    private ConstantUtility constantUtility;

    public PartnerUtils() {
        this.mapper = getMapperWithProperties(true, false, false, true);
    }

    public ObjectMapper getMapperWithProperties(Boolean initializeMapper, Boolean prototypeBean, Boolean enableIndentOutput, Boolean enableFailOnUnknownProperties) {

        ObjectMapper mapper = null;

        if (null == this.mapper || initializeMapper) {
            this.mapper = new ObjectMapper();
        }

        if (prototypeBean) {
            mapper = new ObjectMapper();
        } else {
            mapper = this.mapper;
        }

        if (null != enableFailOnUnknownProperties) {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, enableFailOnUnknownProperties);
        }

        if (null != enableIndentOutput) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }

        return mapper;
    }

    public boolean isValidGUID(String guid) {
        return stringNotEmpty(guid) && guid.length() == 36;
    }

    public boolean isValidAppCode(String appCode) {
        return stringNotEmpty(appCode) && appCode.length() == 16;
    }

    public boolean isValidEmail(String email) {
        return stringNotEmpty(email) && email.matches(constantUtility.EMAIL_REGEX);
    }

    public boolean isValidPassword(String password) {
        return stringNotEmpty(password) && password.length() >= 8;
    }

    public boolean stringNotEmpty(String text) {
        return null != text && !text.trim().isEmpty() && !text.trim().equals("\"\"");
    }

    public boolean stringEmpty(String text) {
        return null == text || text.trim().isEmpty() || text.trim().equals("\"\"");
    }

    public String getIPForRequest(HttpServletRequest servletRequest) {
        String requestedFromIP;
        if (null != servletRequest.getHeader(ConstantUtility.X_REAL_IP_HEADER)) {
            requestedFromIP = servletRequest.getHeader(ConstantUtility.X_REAL_IP_HEADER);
            logger.debug("Requested From IP, X-Real-IP " + requestedFromIP + " For API URL " + servletRequest.getRequestURL());
        } else if (null != servletRequest.getHeader(ConstantUtility.X_FORWARDED_FOR_HEADER)) {
            requestedFromIP = servletRequest.getHeader(ConstantUtility.X_FORWARDED_FOR_HEADER);
            logger.debug("Requested From IP, X-Forwarded-For " + requestedFromIP + " For API URL " + servletRequest.getRequestURL());
        } else {
            requestedFromIP = servletRequest.getRemoteAddr();
            logger.debug("Requested From IP, Remote Address " + requestedFromIP + " For API URL " + servletRequest.getRequestURL());
        }
        return requestedFromIP;
    }

    public String toSlug(String input) {
        input = input.trim();
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
