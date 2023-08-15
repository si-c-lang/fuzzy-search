package com.app.fuzzy.controller;

import com.app.fuzzy.entity.fuzzySearch;
import com.app.fuzzy.repository.fuzzySearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class fuzzySearchController {

    @Autowired
    private fuzzySearchRepository fuzzySearchRepository;

    @PostMapping("/addItem")
    public String addItem(@RequestBody fuzzySearch fuzzySearch) {
        fuzzySearchRepository.save(fuzzySearch);
        return "Saved the Item" + fuzzySearch.getId();
    }

    @PostMapping("/addItems")
    public String addItems(@RequestBody List<fuzzySearch> foodItems){
        fuzzySearchRepository.saveAll(foodItems);
        return "saved";
    }

    @GetMapping("/search")
    public List<String> searchItems(@RequestParam String name) {
        List<String> foodItems = fuzzySearchRepository.findAll().stream()
                .map(fuzzySearch::getName)
                .distinct()
                .collect(Collectors.toList());
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        List<String> matchedFoods = new ArrayList<>();

        for (String food : foodItems) {
            String[] queryTokens = name.toLowerCase().split("\\s+");
            String[] nameTokens = food.toLowerCase().split("\\s+");

            boolean isMatch = false;

            for (String queryToken : queryTokens) {
                for (String nameToken : nameTokens) {
                    int distance = levenshteinDistance.apply(queryToken, nameToken);
                    System.out.println(distance);
                    if (distance <= 2) {
                        matchedFoods.add(food);
                    }
                }

            }
        }
        return matchedFoods;
    }
}
