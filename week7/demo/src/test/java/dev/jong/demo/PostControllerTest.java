package dev.jong.demo;

import dev.jong.demo.dto.PostDto;
import dev.jong.demo.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;
//    Controller가 Service를 주입 받는다면
//    실제 bean에 등록된 친구들을 이런식으로 쓸 수 있음


    @Test
    public void readPost() throws Exception{
        // given 어떤 데이터가 준비가 되어있다.
        final Long id = (long)10;
        PostDto testDto = new PostDto();
        testDto.setId(id);
        testDto.setTitle("Unit Title");
        testDto.setContent("Unit Content");
        testDto.setWriter("unit");

        given(postService.readPost(id)).willReturn(testDto);
        // postService는 test내에서 동작하지 않는다
        // Mockito lib으로 postService와 같은 동작을 하게 설정

        // when 어떤 행위가 일어났을때 (함수 호출 등)
        final ResultActions actions = mockMvc.perform(get("/post/{id}", id))
                .andDo(print());

        // then 어떤 결과가 올것인지
        actions.andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.title", is("Unit Title")), // is import 수동으로
                jsonPath("$.content", is("Unit Content")),
                jsonPath("$.writer", is("unit"))
        );
    }

    @Test
    public void readPostAll() throws Exception{
        //given
        PostDto postFirst = new PostDto();
        PostDto postSecond = new PostDto();

        postFirst.setTitle("title 1");
        postFirst.setContent("content 1");
        postFirst.setWriter("writer 1");

        postSecond.setTitle("title 2");
        postSecond.setContent("content 2");
        postSecond.setWriter("writer 2");

        List<PostDto> postDtoList = Arrays.asList(postFirst, postSecond);

        given(postService.readPostAll()).willReturn(postDtoList);

        //when
        final ResultActions actions = mockMvc.perform(get("/post"))
                .andDo(print());

        //then
        actions.andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                jsonPath("$", hasSize(postDtoList.size()))
        );
    }
}