package com.codeit.sprint.team3.backend.auth.application.port.in;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterUserCommandTest {

    String name = "junha";
    String email = "junha@gmail.com";
    String password = "123456";
    String nickname = "jun";
    String description = "hello";

    @Test
    @DisplayName("이름이 공백일 때 예외가 발생해야 한다.")
    void test1() {
        assertThrows(ConstraintViolationException.class, () -> {
            new RegisterUserCommand(
                    "",
                    email,
                    password,
                    nickname,
                    description);
        });
    }

    @Test
    @DisplayName("이메일 형식이 틀릴 때 예외가 발생해야 한다.")
    void test2() {
        assertThrows(ConstraintViolationException.class, () -> {
            new RegisterUserCommand(
                    name,
                    "wrong email",
                    password,
                    nickname,
                    description);
        });
        assertThrows(ConstraintViolationException.class, () -> {
            new RegisterUserCommand(
                    name,
                    "wrong@",
                    password,
                    nickname,
                    description);
        });
    }

    @Test
    @DisplayName("비밀번호가 공백일 때 예외가 발생해야 한다.")
    void test3() {
        assertThrows(ConstraintViolationException.class, () -> {
            new RegisterUserCommand(
                    name,
                    email,
                    " ",
                    nickname,
                    description);
        });
    }

    @Test
    @DisplayName("닉네임이 공백일 때 예외가 발생해야 한다.")
    void test4() {
        assertThrows(ConstraintViolationException.class, () -> {
            new RegisterUserCommand(
                    name,
                    email,
                    password,
                    " ",
                    description);
        });
    }

    @Test
    @DisplayName("정상 입력은 객체를 생성해야한다.")
    void test5() {
        RegisterUserCommand command =
                new RegisterUserCommand(
                    name,
                    email,
                    password,
                    nickname,
                    description
                );

        assertEquals(command.getName(), name);
        assertEquals(command.getEmail(), email);
        assertEquals(command.getPassword(), password);
        assertEquals(command.getNickname(), nickname);
        assertEquals(command.getDescription(), description);
    }


}