package com.is4tech.search.component;

import com.is4tech.search.domain.SearchResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class SearchComponent {

    private static final Logger logger = LoggerFactory.getLogger(SearchComponent.class);
    private final Map<String , List<SearchResponseDTO>> failureCache = new HashMap<>();
    private final RestTemplate restTemplate;

    @Autowired
    public SearchComponent(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<SearchResponseDTO> search(@RequestParam String event) {
        logger.info("Looking for Event: {}", event);
        SearchResponseDTO[] result = restTemplate.getForObject("http://inventory/inventory?event={event}",
                SearchResponseDTO[].class, new Object[]{event});

        List<SearchResponseDTO> events = Arrays.asList(result);
        failureCache.put(event, events);
        return events;
    }

}
