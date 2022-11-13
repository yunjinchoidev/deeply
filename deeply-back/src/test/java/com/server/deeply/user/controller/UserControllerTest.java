package com.server.deeply.user.controller;

import com.google.gson.Gson;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, MockitoExtension.class})
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 9000)
class UserControllerTest {

    private MockMvc mockMvc;

    private Gson gson;

    @InjectMocks
    private UserController target;

    @Mock
    private UserServiceImpl userService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void init(RestDocumentationContextProvider restDocumentationContextProvider) {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    //    @Test
    void 회원단일조회() throws Exception {
        UserRequestDto param = UserRequestDto.builder()
                .email("cyj@gmail.com")
                .password("1111")
                .build();

        doReturn(1L).when(userService).saveUser(any());

        this.mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(param)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("get_user_{id}",
                        preprocessRequest(prettyPrint()),   // (2)
                        preprocessResponse(prettyPrint()),  // (3)
                        requestFields(                        // (4)
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀번호")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("유저 아이디").optional()
                        ))
                );
    }


    //@Test
    void 회원리스트조회() throws Exception {
        UserRequestDto param = UserRequestDto.builder()
                .email("cyj@gmail.com")
                .password("1111")
                .build();

        doReturn(1L).when(userService).saveUser(any());

        this.mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(param)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("get_user_list",
                        preprocessRequest(prettyPrint()),   // (2)
                        preprocessResponse(prettyPrint()),  // (3)
                        requestFields(                        // (4)
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀번호")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("유저 아이디").optional()
                        ))
                );
    }
}