package com.galvanize.guestbook.repository;

import com.galvanize.guestbook.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
