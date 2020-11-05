package study.spring.request_reponse_log.config.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LogProperties {

    @Value("${interceptor.log.exclude-methods}")
    private String excludeMethods;

    private String[] excludeMethodArray;

    public String[] getExcludeMethodArray() {
        if (excludeMethods == null) {
            excludeMethodArray = getSplitArray(excludeMethods);
        }

        return excludeMethodArray;
    }

    public boolean isExcludeMethod(final String method) {
        return Arrays.stream(getExcludeMethodArray()).anyMatch(excludeMethod -> excludeMethod.equalsIgnoreCase(method));
    }

    private String[] getSplitArray(String csv) {
        return csv.split(",");
    }
}
