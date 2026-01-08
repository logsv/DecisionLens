package com.decisionlens.review.service;

import com.decisionlens.review.model.Rule;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class RuleLoader {

    @Getter
    private List<Rule> rules = Collections.emptyList();

    @PostConstruct
    public void loadRules() {
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            ClassPathResource resource = new ClassPathResource("rules.yaml");
            if (resource.exists()) {
                try (InputStream inputStream = resource.getInputStream()) {
                    rules = mapper.readValue(inputStream, new TypeReference<List<Rule>>() {});
                    log.info("Loaded {} rules from rules.yaml", rules.size());
                }
            } else {
                log.warn("rules.yaml not found in classpath");
            }
        } catch (IOException e) {
            log.error("Failed to load rules", e);
        }
    }
}
