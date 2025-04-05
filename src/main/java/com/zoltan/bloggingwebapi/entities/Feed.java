package com.zoltan.bloggingwebapi.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Entity
@Table(name = "feeds")
@ToString
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String title;

    @Column(length = 2000)
    private String description;

    private String link;
    private LocalDate publishedDate;
    private String source;

    public Feed(){}

    public Feed(String title,String description,String link,LocalDate publishedDate, String source){
        this.title = title;
        this.description = description;
        this.link = link;
        this.publishedDate = publishedDate;
        this.source = source;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
