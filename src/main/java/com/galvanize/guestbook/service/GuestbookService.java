package com.galvanize.guestbook.service;

import com.galvanize.guestbook.dto.CommentDto;
import com.galvanize.guestbook.repository.CommentRepository;
import com.galvanize.guestbook.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GuestbookService {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDto> getComments() {
        return commentRepository.findAll().stream()
                .map(commentEntity -> {
                    return new CommentDto(
                            commentEntity.getName(),
                            commentEntity.getComment());
                }).collect(Collectors.toList());
    }

    public void addComment(CommentDto commentDto) {
        commentRepository.save(new CommentEntity(
                commentDto.getName(),commentDto.getComment()
        ));
    }
}