package com.server.deeply.user.controller;

import com.google.gson.Gson;
import com.server.deeply.config.security.JwtTokenUtil;
import com.server.deeply.config.security.TokenProvider;
import com.server.deeply.user.dto.UserRequestDto;
import com.server.deeply.user.dto.UserResponseDto;
import com.server.deeply.user.jpa.User;
import com.server.deeply.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private JwtTokenUtil jwtTokenUtil;


    @BeforeEach
    public void init(RestDocumentationContextProvider restDocumentationContextProvider) {
        gson = new Gson();

//        doReturn("1").when(jwtTokenUtil).parseBearerToken(any());
//        doReturn("yunjinchoi@gamil.com").when(jwtTokenUtil).getEmailFormToken(any());
//        doReturn("ROLE_USER").when(jwtTokenUtil).getRoleFromToken(any());
//        doReturn(true).when(tokenProvider).validateToken(any());
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .apply(documentationConfiguration(restDocumentationContextProvider))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    void 회원단일조회() throws Exception {

        final String URL = "/user/{userId}";
        final Integer userId = 1;

        UserRequestDto param = UserRequestDto.builder()
                .email("cyj@gmail.com")
                .password("1111")
                .build();
        UserResponseDto result = UserResponseDto.builder().email("cyj@gmail.com").password("1111").build();

        doReturn(result).when(userService).findById(any());

        this.mockMvc.perform(
                        RestDocumentationRequestBuilders.get(
                                        URL, userId)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get_user_{userId}",
                        preprocessRequest(prettyPrint()),   // (2)
                        preprocessResponse(prettyPrint()),  // (3)
                        pathParameters(                        // (4)
                                parameterWithName("userId").description("유저아이디")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("유저아이디").optional()
                        ))
                );
    }


//    @Test
    void 회원리스트조회() throws Exception {
        UserRequestDto param = UserRequestDto.builder()
                .email("cyj@gmail.com")
                .password("1111")
                .build();

        List<UserResponseDto> userList = new ArrayList<>();
        UserResponseDto user = UserResponseDto.builder().email("cyj@gmail.com").password("1111").build();
        userList.add(user);
        Sort sort = Sort.by("userID").ascending();
        Pageable pageable = PageRequest.of(1, 10, sort);
        Page<UserResponseDto> result = new PageImpl<>(userList, pageable, 1);


        doReturn(result).when(userService).findAll(any());

        this.mockMvc.perform(post("/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(param)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get_user_list",
                        preprocessRequest(prettyPrint()),   // (2)
                        preprocessResponse(prettyPrint()),  // (3)
                        requestFields(                        // (4)
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀번호")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("content[].id").description("유저 아이디").optional()
                        ))
                );
    }

//    @Test
    void 회원수정() throws Exception {
        UserRequestDto param = UserRequestDto.builder()
                .id(1L)
                .email("cyj@gmail.com")
                .password("1111")
                .build();

        UserResponseDto result = UserResponseDto.builder().email("cyj@gmail.com").password("1111").build();
        doReturn(result).when(userService).updateUser(any());

        this.mockMvc.perform(put("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(param)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post_user",
                        preprocessRequest(prettyPrint()),   // (2)
                        preprocessResponse(prettyPrint()),  // (3)
                        requestFields(                        // (4)
                                fieldWithPath("id").description("유저 아이디"),
                                fieldWithPath("email").description("이메일"),
                                fieldWithPath("password").description("비밀번호")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("id").description("유저 아이디").optional()
                        ))
                );
    }
}