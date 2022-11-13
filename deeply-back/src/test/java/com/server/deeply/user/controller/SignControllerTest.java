package com.server.deeply.user.controller;

import com.google.gson.Gson;
import com.server.deeply.config.security.TokenProvider;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.jpa.User;
import com.server.deeply.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
class SignControllerTest {

    private MockMvc mockMvc;

    private Gson gson;

    @InjectMocks
    private SignController target;

    @Mock
    private UserService userService;

    @Mock
    private TokenProvider tokenProvider;

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

    @Test
    @DisplayName("회원가입")
    void 회원가입() throws Exception {
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
                .andDo(document("post_auth_singup",
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

    @Test
    @DisplayName("로그인")
    void login() throws Exception {

        User user = User.builder()
                .email("cyj@gmail.com")
                .password("1111")
                .build();
        String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5dW5qaW4yMUBobWFpbC5jb20iLCJpc3MiOiJkZW1vIGFwcCIsImlhdCI6MTY2ODMwOTE0MiwiZXhwIjoxNjY4Mzk1NTQyfQ.d8CsN8YXJNOJOkVfTiOAbB6NsKsuUo8Oo__YUkGqSiM2RRdVQo_tcIhpw44sqyNzwoJdJsgfENymCLC5n_p6dw";
        doReturn(user).when(userService).getByCredentials(any(),any(),any());
        doReturn(token).when(tokenProvider).create(any());

        this.mockMvc.perform(post("/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post_auth_singup",
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