package com.kafka.searchservice.controller;

import com.kafka.searchservice.dto.ProductTagsDto;
import com.kafka.searchservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @PostMapping("/tagCache")
    public void addTagCache(@RequestBody ProductTagsDto request) {
        searchService.addTagCache(request.productId, request.tags);
    }

    @DeleteMapping("/tagCache")
    public void removeTagCache(@RequestBody ProductTagsDto request) {
        searchService.removeTagCache(request.productId, request.tags);
    }

    @GetMapping("/tags/{tag}/productIds")
    public List<Long> getTagProductIds(@PathVariable String tag) {
        return searchService.getProductIdsByTag(tag);
    }

}
