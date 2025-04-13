package com.zoltan.bloggingwebapi.services;

import com.zoltan.bloggingwebapi.entities.BlogPost;
import com.zoltan.bloggingwebapi.entities.User;
import com.zoltan.bloggingwebapi.entities.enums.PostCategories;
import com.zoltan.bloggingwebapi.exceptions.NotFoundException;
import com.zoltan.bloggingwebapi.payloads.BlogPostDTO;
import com.zoltan.bloggingwebapi.repositories.BlogPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogPostService {
    @Autowired
    private BlogPostRepo bprepo;

    public Page<BlogPost> findWithFilters(PostCategories category, String title, Pageable pageable) {
        Specification<BlogPost> specs = ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (category != null) {
            specs = specs.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category));
        }
        if (title != null) {
            specs = specs.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), title));
        }
        return bprepo.findAll(specs, pageable);
    }

    public BlogPost findById(UUID id) {
        return bprepo.findById(id).orElseThrow(() -> new NotFoundException("Il blog post con id " + id + "non è stata trovata"));
    }

    public BlogPost save(BlogPostDTO body, User user) {
        BlogPost bp = new BlogPost(body.category(), body.title(), body.content(), user);
        return this.bprepo.save(bp);
    }

    public BlogPost updateBlogPost(UUID id, BlogPostDTO body) {
        BlogPost bp = findById(id);
        if (body.category() != null) bp.setCategory(body.category());
        if (body.title() != null) bp.setTitle(body.title());
        if (body.content() != null) bp.setContent(body.content());

        bp.setCover("https://ui-avatars.com/api/?name=" + bp.getTitle().trim() + "+" + bp.getCategory());

        return bprepo.save(bp);
    }

    public void deleteBlogPost(UUID id) {
        BlogPost bp = this.findById(id);
        bprepo.delete(bp);
    }


}
