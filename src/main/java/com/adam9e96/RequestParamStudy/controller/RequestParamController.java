package com.adam9e96.RequestParamStudy.controller;

import com.adam9e96.RequestParamStudy.form.Form;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * 컨트롤러 클래스 예제.
 * <p>
 * 사용자로부터 입력을 받아 확인 화면으로 전달하는 역할을 합니다.
 */
@Controller
public class RequestParamController {

    /*
     * Form 사용 X
     */
    /**
     * 입력 화면을 표시하는 메서드.
     * <p>
     * "entry" 뷰를 반환합니다.
     *
     * @return 입력 폼을 표시하는 뷰 이름 (entry.html)
     */
    @GetMapping("show")
    public String showView() {
        // 반환 값으로 뷰 이름을 돌려줌
        return "entry";
    }

    /**
     * 확인 화면을 표시하는 메서드.
     * <p>
     * POST 요청으로 전달받은 name, age, birthday 값을 Model에 담고 "confirm" 뷰로 전달합니다.
     *
     * @param model    뷰에 데이터를 전달하기 위한 Model 객체
     * @param name     사용자가 입력한 이름
     * @param age      사용자가 입력한 나이
     * @param birthday 사용자가 입력한 생년월일 (ISO-8601 형식: yyyy-MM-dd)
     * @return "confirm" 뷰 이름
     */
    @PostMapping("confirm")
    public String confirmView(
            Model model,
            @RequestParam String name,
            @RequestParam Integer age,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate birthday
    ) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("birthday", birthday);

        return "confirm";
    }

    // *=========================================================* //
    /*
     * Form 객체 사용
     */
    /**
     * 입력 화면을 표시하는 메서드.
     * <p>
     * "entry" 뷰를 반환합니다.
     *
     * @param model 뷰에 데이터를 전달하기 위한 Model 객체
     * @return 입력 폼을 표시하는 뷰 이름 (entry.html)
     */
    @GetMapping("show_form")
    public String showViewForm(Model model) {
        // 빈 Form 객체를 model에 담아서 보냄
        model.addAttribute("form", new Form());
        return "entry_form";
    }

    /**
     * 확인 화면을 표시하는 메서드.
     * <p>
     * POST 요청으로 전달받은 Form 객체 값을 Model에 담고 "confirm" 뷰로 전달합니다.
     *
     * @param form  사용자가 입력한 데이터를 담고 있는 Form 객체
     * @return "confirm" 뷰 이름
     */
    @PostMapping("confirm_form")
    public String confirmViewForm(Form form, Model model) {
        // Form 객체를 Model에 담음
        model.addAttribute("form", form);
        return "confirm_form";
    }


}
