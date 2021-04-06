package com.galvanize.guestbook;

import com.galvanize.guestbook.repository.CommentRepository;
import com.galvanize.guestbook.entity.CommentEntity;
import com.galvanize.guestbook.service.GuestbookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuestbookServiceTest {
    @Mock
    CommentRepository mockCommentRepository;

    @InjectMocks
    GuestbookService subject;

    @Test
    void create() {
        CommentDto commentDto = new CommentDto("ABC", "XYZ PQR");
        subject.addComment(commentDto);
        verify(mockCommentRepository).save(
                new CommentEntity("ABC", "XYZ PQR")
        );
    }

    @Test
    void fetchAll(){
        CommentEntity commentEntity = new CommentEntity("ABC", "XYZ PQR");
        CommentEntity commentEntity1 = new CommentEntity("DEF", "XYZ PQR");
        CommentDto commentDto = new CommentDto("ABC", "XYZ PQR");
        CommentDto commentDto1 = new CommentDto("DEF", "XYZ PQR");

        List<CommentEntity> commentEntities = Arrays.asList(commentEntity,commentEntity1);
        when(mockCommentRepository.findAll()).thenReturn(commentEntities);

        List<CommentDto> comments = subject.getComments();
        verify(mockCommentRepository).findAll();
        assertThat(comments, is(equalTo(Arrays.asList(commentDto,commentDto1))));
    }
}
