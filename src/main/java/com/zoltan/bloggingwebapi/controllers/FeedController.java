package com.zoltan.bloggingwebapi.controllers;

import com.zoltan.bloggingwebapi.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @PostMapping("/populate")
    public String populateFeeds() {
        feedService.cleanAndPopulateFeeds();
        return "Feeds popolati con successo! Feed presenti: " + feedService.countFeeds();
    }

    @GetMapping("/count")
    public long countFeeds() {
        return feedService.countFeeds();
    }
}
