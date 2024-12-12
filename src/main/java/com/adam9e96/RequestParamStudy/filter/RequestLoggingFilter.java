package com.adam9e96.RequestParamStudy.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 요청 처리 전후로 요청 파라미터를 로깅하는 필터 클래스.
 * <p>
 * 모든 요청에 대해 한 번씩만 실행되며(HttpServletRequest, HttpServletResponse를 다룰 수 있음),
 * 처리 대상 요청의 파라미터를 로깅하여 디버깅과 모니터링에 활용할 수 있습니다.
 */
@Component
@Slf4j
public class RequestLoggingFilter extends OncePerRequestFilter {

    /**
     * 각 요청에 대해 필터 체인 앞단에서 호출되는 메서드.
     * <p>
     * 여기서 요청 파라미터를 추출하고 로그로 남긴 뒤,
     * 필터 체인의 다음 단계로 제어를 넘깁니다.
     *
     * @param request     현재 처리 중인 HTTP 요청
     * @param response    현재 처리 중인 HTTP 응답
     * @param filterChain 필터 체인 객체
     * @throws ServletException 필터 처리 과정에서 발생하는 서블릿 관련 예외
     * @throws IOException      입출력 예외
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 요청 파라미터 로깅
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((key, values) -> {
            log.info("Parameter: {} = {}", key, Arrays.toString(values));
        });

        // 다음 필터 또는 서블릿으로 요청 전달
        filterChain.doFilter(request, response);
    }
}
