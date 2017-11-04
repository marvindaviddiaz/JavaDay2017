package com.is4tech.search.controller;

import com.is4tech.search.domain.SearchResponseDTO;
import com.is4tech.search.component.SearchComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final SearchComponent searchComponent;

    @Autowired
    public SearchController(SearchComponent searchComponent) {
        this.searchComponent = searchComponent;
    }

    @GetMapping
    List<SearchResponseDTO> search(@RequestParam String event){
        logger.info("Input : "+ event);
        return this.searchComponent.search(event);
    }

}
