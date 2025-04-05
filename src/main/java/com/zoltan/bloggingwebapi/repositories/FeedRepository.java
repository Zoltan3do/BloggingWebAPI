package com.zoltan.bloggingwebapi.repositories;

import com.zoltan.bloggingwebapi.entities.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedRepository extends JpaRepository<Feed, UUID> {

}
