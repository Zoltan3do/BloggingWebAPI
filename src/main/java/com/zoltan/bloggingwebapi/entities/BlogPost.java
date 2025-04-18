package com.zoltan.bloggingwebapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zoltan.bloggingwebapi.entities.enums.PostCategories;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.UUID;

@Entity
@Table(name = "blog_posts")
@ToString
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PostCategories category;

    private String title;
    private String cover;

    @Column(length = 2000)
    private String content;
    private int readingTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public BlogPost(){}

    public BlogPost(PostCategories category, String title, String content, User user) {
        this.category = category;
        this.title = title;
        this.cover = "https://ui-avatars.com/api/?name=" + title.trim() + "+" + category;
        this.content = content;
        this.readingTime =  Math.round( (float) content.length() / 10);
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public PostCategories getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public String getContent() {
        return content;
    }

    public Integer getReadingTime() {
        return readingTime;
    }

    public User getUser() {
        return user;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCategory(PostCategories category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReadingTime(int readingTime) {
        this.readingTime = readingTime;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
