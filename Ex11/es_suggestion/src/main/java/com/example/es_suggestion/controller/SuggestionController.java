package com.example.es_suggestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.*;

@RestController
public class SuggestionController {
    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/get/{keyword}", method = RequestMethod.GET)
    public List<String> getSuggestion(@PathVariable String keyword) throws IOException {
        SuggestionController titleSearchRepository = context.getBean(SuggestionController.class);
        return titleSearchRepository.getSuggestion(keyword);
    }
}
