package com.zoltan.bloggingwebapi.runners;

import com.zoltan.bloggingwebapi.services.FeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FeedPopulationRunner implements CommandLineRunner {

    private final FeedService feedService;

    public FeedPopulationRunner(FeedService feedService) {
        this.feedService = feedService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Esecuzione iniziale del popolamento dei feed...");
        feedService.cleanAndPopulateFeeds();
        System.out.println("Popolamento iniziale completato. Feed presenti: " + feedService.countFeeds());
    }

    @Scheduled(cron = "0 0 3,9 * * *")
    public void scheduledFeedPulation() {
        System.out.println("Esecuzione schedulata del popolamento dei feed... ");
        feedService.cleanAndPopulateFeeds();
        System.out.println("Popolamento schedulato completato. Feed presenti: " + feedService.countFeeds());
    }

}
