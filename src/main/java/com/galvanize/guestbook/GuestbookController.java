package com.galvanize.guestbook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("guestbook")
public class GuestbookController {

    @GetMapping
    public List<Object> fetchAll() {
        return new ArrayList<>();
    }
}
