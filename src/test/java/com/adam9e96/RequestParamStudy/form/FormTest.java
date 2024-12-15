package com.adam9e96.RequestParamStudy.form;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FormTest {

    @DisplayName("Setter/Getter 정상 동작 테스트")
    @Test
    void testFormSetAndGetFields() {
        // given
        Form form = new Form();
        String expectedName = "홍길동";
        Integer expectedAge = 25;
        LocalDate expectedBirthday = LocalDate.of(1990, 1, 1);

        // when
        form.setName(expectedName);
        form.setAge(expectedAge);
        form.setBirthday(expectedBirthday);

        // then
        assertEquals(expectedName, form.getName());
        assertEquals(expectedAge, form.getAge());
        assertEquals(expectedBirthday, form.getBirthday());
    }

    @DisplayName("기본생성자 사용 시 필드가 null인지 확인")
    @Test
    void testDefaultConstructorFieldsAreNull() {
        Form form = new Form();

        assertThat(form.getName()).isNull();
        assertThat(form.getAge()).isNull();
        assertThat(form.getBirthday()).isNull();
    }


    @DisplayName("모든 필드를 포함하는 생성자로 객체 생성")
    @Test
    void testAllArgsConstructor() {
        String name = "이순신";
        Integer age = 30;
        LocalDate birthday = LocalDate.of(1980, 12, 25);

        Form form = new Form(name, age, birthday);
        assertEquals(name, form.getName());
        assertEquals(age, form.getAge());
        assertEquals(birthday, form.getBirthday());
    }
}
