package com.zoltan.bloggingwebapi.controllers;

import com.zoltan.bloggingwebapi.entities.BlogPost;
import com.zoltan.bloggingwebapi.entities.User;
import com.zoltan.bloggingwebapi.entities.enums.PostCategories;
import com.zoltan.bloggingwebapi.exceptions.BadRequestException;
import com.zoltan.bloggingwebapi.exceptions.UnauthorizedException;
import com.zoltan.bloggingwebapi.payloads.BlogPostDTO;
import com.zoltan.bloggingwebapi.payloads.validationGroups.Create;
import com.zoltan.bloggingwebapi.payloads.validationGroups.Update;
import com.zoltan.bloggingwebapi.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class BlogPostController {

    @Autowired
    private BlogPostService bps;

    @GetMapping("/filtered")
    public Page<BlogPost> getBlogPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) PostCategories category,
            @RequestParam(required = false) String title
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bps.findWithFilters(category, title, pageable);
    }

    @GetMapping("/{id}")
    public BlogPost findBlogPostById(@PathVariable UUID id) {
        return bps.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost savePost(@RequestBody @Validated(Create.class) BlogPostDTO body, BindingResult validationResult, @AuthenticationPrincipal User currentUser) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". "));
            throw new BadRequestException("Payload error: " + message);
        }
        return bps.save(body, currentUser);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable UUID id, @AuthenticationPrincipal User currentUser) {
        BlogPost bp = bps.findById(id);
        if (bp.getUser().getId() == currentUser.getId()) {
            bps.deleteBlogPost(id);
        }
    }

    @PutMapping("/{blogId}")
    public BlogPost updateBlogPost(@PathVariable UUID blogId, @RequestBody @Validated(Update.class) BlogPostDTO body, BindingResult validationResult, @AuthenticationPrincipal User currentUser) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        BlogPost bp = bps.findById(blogId);
        if (bp.getUser().getId() == currentUser.getId()) {
            return bps.updateBlogPost(blogId, body);
        } else throw new UnauthorizedException("Non puoi modificare un post non tuo !");
    }


}
