package com.galvanize.guestbook.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.dto.CommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureRestDocs
public class GuestbookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getNoEntriesTest() throws Exception {
        mockMvc.perform(get("/guestbook").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(0));
    }

    @Test
    void postOneEntryTest() throws Exception{
        CommentDto input = new CommentDto("John", "Hi,there");
        mockMvc.perform(
                post("/guestbook")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andDo(document("Add Comment"));;
    }

    @Test
    void getOneEntryTest() throws Exception{
        CommentDto input = new CommentDto("John", "Hi,there");
        mockMvc.perform(
                post("/guestbook")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc.perform(get("/guestbook")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("Comments", responseFields(
                        fieldWithPath("[0].name").description("Name"),
                        fieldWithPath("[0].comment").description("Comment"))))
                .andReturn();
        String commentDtoString = mvcResult.getResponse().getContentAsString();
        List<CommentDto> returnedCommentDto = objectMapper.readValue(commentDtoString, new TypeReference<ArrayList<CommentDto>>(){});
        assertEquals(Arrays.asList(input),returnedCommentDto);

    }

    @Test
    void getManyEntriesTest() throws Exception{
        CommentDto input1 = new CommentDto("John", "Hi,there");
        CommentDto input2 = new CommentDto("ABC", "XYZ");

        mockMvc.perform(
                post("/guestbook")
                        .content(objectMapper.writeValueAsString(input1))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(
                post("/guestbook")
                        .content(objectMapper.writeValueAsString(input2))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult mvcResult = mockMvc.perform(get("/guestbook")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String commentDtoString = mvcResult.getResponse().getContentAsString();
        List<CommentDto> returnedCommentDto = objectMapper.readValue(commentDtoString, new TypeReference<ArrayList<CommentDto>>(){});
        assertEquals(Arrays.asList(input1, input2),returnedCommentDto);

    }
}
