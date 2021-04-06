package com.galvanize.guestbook.service;

import com.galvanize.guestbook.CommentDto;
import com.galvanize.guestbook.repository.CommentRepository;
import com.galvanize.guestbook.repository.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GuestbookService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public void addComment(CommentDto commentDto) {
    }
}