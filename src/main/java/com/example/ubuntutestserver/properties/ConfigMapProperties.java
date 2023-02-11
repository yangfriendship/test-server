package com.example.ubuntutestserver.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "configmap")
@Getter
@Setter
public class ConfigMapProperties {

    private Map<String, Map<String, Object>> props = new HashMap<>();

    public Map<String, Object> getProps() {
        return Collections.unmodifiableMap(this.props);
    }

}
