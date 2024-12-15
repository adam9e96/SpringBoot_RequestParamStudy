package com.adam9e96.RequestParamStudy.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    @NonNull
    private String name;

    @NonNull
    private Integer age;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NonNull
    private LocalDate birthday;

}