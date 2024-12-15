package com.adam9e96.RequestParamStudy.controller;

import jdk.jfr.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RequestParamControllerIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(RequestParamControllerIntegrationTest.class);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context; // WebApplicationContext 주입


    /**
     * /show 경로에 GET 요청을 보낼 때 컨트롤러가 정상적으로 작동하는지 테스트
     * <p>
     * 검증 내용:
     * 1. HTTP 응답 상태 코드가 200(OK) 인지
     * 2. 반환되는 뷰 이름이 "entry" 인지
     */
    @DisplayName("GET /show 요청 시 entry 뷰 반환")
    @Test
    void testShowView() throws Exception {
        final String url = "/show";
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("entry"));
    }

    /**
     * /confirm 경로에 POST 요청을 보낼 때 RequestParam 으로 받은 name, age, birthday 파라미터를
     * Model 에 담아서 뷰에 전달하는지 테스트
     * 검증 내용:
     * 1. HTTP 응답 상태 코드가 200(OK) 인지
     * 2. 뷰 이름이 "confirm"인지
     * 3. Model 에 name 이라는 속성이 "홍길동" 으로 세팅되었는지
     * 4. Model 에 age 라는 속성이 25로 세팅되었는지
     * 5. Model 에 birthday 라는 속성이 2024-12-12 날짜로 세팅되었는지(LocalDate.of(2024,12,12))
     */
    @DisplayName("POST /confirm 요청 시 Model 객체에 값 세팅 및 confirm 뷰 반환")
    @Test
    void testConfirmView() throws Exception {
        final String url = "/confirm";
        mockMvc.perform(post(url)
                        .param("name", "홍길동")
                        .param("age", "25")
                        .param("birthday", "2024-12-12")) // ISO-8601 형식
                .andExpect(status().isOk())
                .andExpect(view().name("confirm"))
                .andExpect(model().attribute("name", "홍길동"))
                .andExpect(model().attribute("age", 25))
                .andExpect(model().attribute("birthday", LocalDate.of(2024, 12, 12)));
    }

    /**
     * /show_form 경로에 GET 요청 시 폼 바인딩을 위한 Form 객체를 Model 에 담고
     * "entry_form" 뷰를 반환하는지 테스트
     * <p>
     * 검증 내용:
     * 1. HTTP 응답 상태 코드 200(OK)
     * 2. 뷰 이름이 "entry_form" 인지
     * 3. Model 에 form 속성이 존재하는지(빈 Form 객체가 전달되는지)
     */
    @DisplayName("GET /show_form 요청 시 entry_form 뷰 반환 및 모델에 form 객체 존재")
    @Test
    void testShowViewForm() throws Exception {
        final String url = "/show_form";
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("entry_form"))
                .andExpect(model().attributeExists("form"));
    }

    /**
     * /confirm_form 경로에 POST 요청 시 Form 객체로 파라미터가 바인딩되어 Model 에 올바르게 세팅되고,
     * "confirm_form" 뷰로 반환되는지 테스트
     * <p>
     * 검증 내용:
     * 1. HTTP 응답 상태 코드 200(OK)
     * 2. 뷰 이름이 "confirm_form" 인지
     * 3. Model에 form 속성이 존재하는지
     * 4. form 객체의 name 필드가 "이순신" 인지
     * 5. form 객체의 age 필드가 30 인지
     * 6. form 객체의 birthday 필드가 1980-12-25 로 세팅되었는지
     */
    @DisplayName("POST /confirm_form 요청 시 Form 객체 바인딩 및 confirm_form 뷰 반환")
    @Test
    void testConfirmViewForm() throws Exception {
        final String url = "/confirm_form";
        mockMvc.perform(post(url)
                        .param("name", "이순신")
                        .param("age", "30")
                        .param("birthday", "1980-12-25"))
                .andExpect(status().isOk())
                .andExpect(view().name("confirm_form"))
                .andExpect(model().attributeExists("form"))
                .andExpect(model().attribute("form",
                        org.hamcrest.Matchers.hasProperty("name", is("이순신"))))
                .andExpect(model().attribute("form",
                        org.hamcrest.Matchers.hasProperty("age", is(30))))
                .andExpect(model().attribute("form",
                        org.hamcrest.Matchers.hasProperty("birthday", is(LocalDate.of(1980, 12, 25)))));
    }

    /**
     * WebApplicationContext 가 정상적으로 로딩되었는지, 그리고 특정 빈(requestParamController) 이
     * 로딩되었는지 테스트
     * <p>
     * 검증 내용:
     * 1. context.getBeanDefinitionCount()이 0보다 큰지(빈이 정상 로딩되었음을 의미)
     * 2. requestParamController 이라는 빈이 컨텍스트에 존재하는지
     */
    @DisplayName("WebApplicationContext를 이용해 빈 존재 여부 테스트")
    @Test
    void testWebApplicationContext() {
        // 예: 특정 빈이 로딩되었는지 확인
        assert context.getBeanDefinitionCount() > 0;
        // 실제 필요한 빈이 있는지
        assert context.containsBean("requestParamController");
    }

    /**
     * Content-Type 를 검증할때는 정확하게 지정해줘야하는데
     * UTF-8 을 검증하려면 text/html;charset=UTF-8
     */
    @DisplayName("content 타입 검증")
    @Test
    public void testContentType() throws Exception {
        final String url = "/show_form";
        mockMvc.perform(get("/show_form"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("entry_form"));

    }
}
