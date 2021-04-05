package com.galvanize.guestbook;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("guestbook")
public class GuestbookController {

    @GetMapping
    public List<Object> fetchAll() {
        return new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postComment(@RequestBody CommentDto commentDto ){

    }
}
