package com.github.dac4j.spring.data.jpa.core.extractor;

import org.springframework.stereotype.Component;

@Component
public class SourceValueExtractor {

    private static SourceValueExtractorInterface sourceValueExtractorInterface;

    public SourceValueExtractor(SourceValueExtractorInterface sourceValueExtractorInterface) {
        SourceValueExtractor.sourceValueExtractorInterface = sourceValueExtractorInterface;
    }

    public static final SourceValueExtractorInterface get() {
        return sourceValueExtractorInterface;
    }
}
