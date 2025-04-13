package com.zoltan.bloggingwebapi.services;

import com.zoltan.bloggingwebapi.entities.Feed;
import com.zoltan.bloggingwebapi.repositories.FeedRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private RSSFeedService rssFeedService;

    @Transactional
    public void cleanAndPopulateFeeds() {
        try {
            feedRepository.deleteAll();

            List<Feed> feeds = rssFeedService.fetchWikipediaRssFeeds();
            feedRepository.saveAll(feeds);
            System.out.println("Tabella feeds pulita e ripopolata con " + feeds.size() + " elementi");
        } catch (Exception e) {
            System.out.println("Errore durante il popolamento dei feed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public long countFeeds() {
        return feedRepository.count();
    }

    public List<Feed> getAll() {
        return feedRepository.findAll();
    }


}
