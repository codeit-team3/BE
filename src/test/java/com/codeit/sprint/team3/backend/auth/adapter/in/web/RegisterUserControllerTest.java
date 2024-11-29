package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserCommand;
import com.codeit.sprint.team3.backend.auth.application.port.in.RegisterUserUseCase;
import com.codeit.sprint.team3.backend.common.security.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RegisterUserController.class)
@Import(SecurityConfig.class)
class RegisterUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterUserUseCase registerUserUseCase;

    @DisplayName("정상 회원가입 요청은 201 반환")
    @Test
    void validRegister() throws Exception {
        String requestBody = """
            {
                "name": "junha",
                "email": "junha@gmail.com",
                "password": "123456",
                "nickName": "jun",
                "description": "A sample user"
            }
        """;
        // Mock 동작 설정
        when((registerUserUseCase).register(Mockito.any(RegisterUserCommand.class))).thenReturn(1L);

        // 테스트 수행
        mockMvc.perform(post("/auths/signup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)) // 요청 본문
                .andExpect(status().isCreated()) // HTTP 201
                .andExpect(jsonPath("$.message").value("사용자 생성 성공"));
    }

    @DisplayName("비정상 회원가입 요청은 400 반환")
    @ParameterizedTest
    @CsvSource({
            "'', junha@gmail.com, 123456, jun, hello", //이름 공백
            "junha, wrong_email, 123456, jun, hello", //이메일 형식 오류
            "junha, wrong@, 123456, jun, hello", //이메일 형식 오류
            "junha, junha@gmail.com, '', jun, hello", //비밀번호 공백
            "junha, junha@gmail.com, 123456, '', hello" //닉네임 공백
    })
    void invalidRegister(String name, String email, String password, String nickName, String description) throws Exception {
        String requestBody = String.format("""
            {
                "name": "%s",
                "email": "%s",
                "password": "%s",
                "nickName": "%s",
                "description": "%s"
            }
        """, name, email, password, nickName, description);
        // Mock 동작 설정
        when((registerUserUseCase).register(Mockito.any(RegisterUserCommand.class))).thenReturn(1L);

        // 테스트 수행
        mockMvc.perform(post("/auths/signup")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest()); // HTTP 400;
    }


}