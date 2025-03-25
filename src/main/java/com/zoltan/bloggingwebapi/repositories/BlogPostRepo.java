package com.zoltan.bloggingwebapi.repositories;

import com.zoltan.bloggingwebapi.entities.BlogPost;
import com.zoltan.bloggingwebapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogPostRepo extends JpaRepository<BlogPost, UUID>, JpaSpecificationExecutor<BlogPost> {

}
