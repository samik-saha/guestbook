package com.galvanize.guestbook.controller;

import com.galvanize.guestbook.dto.CommentDto;
import com.galvanize.guestbook.service.GuestbookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("guestbook")
public class GuestbookController {
    GuestbookService guestbookService;

    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @GetMapping
    public List<CommentDto> fetchAll() {
        return guestbookService.getComments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postComment(@RequestBody CommentDto commentDto ){
        guestbookService.addComment(commentDto);
    }
}
