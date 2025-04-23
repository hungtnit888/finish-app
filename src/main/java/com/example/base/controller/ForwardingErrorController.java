package com.example.base.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Controller
public class ForwardingErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    @ResponseBody // Đảm bảo trả về JSON cho các lỗi khác 404
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; // Mặc định lỗi 500
        String message = "An unexpected error occurred";

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            httpStatus = HttpStatus.resolve(statusCode) != null ? HttpStatus.resolve(statusCode) : httpStatus;

            // Nếu là lỗi 404 Not Found
            if (httpStatus == HttpStatus.NOT_FOUND) {
                // Forward đến index.html để SPA xử lý routing
                // Trả về một String đặc biệt mà Spring sẽ hiểu là forward
                // Lưu ý: @ResponseBody sẽ bị bỏ qua khi trả về String "forward:/..."
                return ResponseEntity.ok("forward:/index.html");
            }

            // Cố gắng lấy message lỗi từ request attribute
            Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
            if (errorMessage != null && !errorMessage.toString().isEmpty()) {
                message = errorMessage.toString();
            }
        }

        // Trả về JSON cho các lỗi khác 404
        Map<String, Object> errorBody = Collections.singletonMap("error", message);
        return new ResponseEntity<>(errorBody, httpStatus);
    }

    // getErrorPath() không còn dùng trong Boot 2.3+
    // public String getErrorPath() {
    //     return PATH;
    // }
} 