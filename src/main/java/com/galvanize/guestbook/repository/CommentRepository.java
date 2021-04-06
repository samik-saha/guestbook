package com.galvanize.guestbook.repository;

import com.galvanize.guestbook.repository.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
