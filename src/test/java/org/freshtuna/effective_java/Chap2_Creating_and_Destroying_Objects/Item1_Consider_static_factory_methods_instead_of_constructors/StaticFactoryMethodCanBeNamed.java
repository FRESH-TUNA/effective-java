package org.freshtuna.effective_java.Chap2_Creating_and_Destroying_Objects.Item1_Consider_static_factory_methods_instead_of_constructors;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StaticFactoryMethodCanBeNamed {
    private enum ErrorCode {
        PASSWORD_CONFORM_FAILED("패스워드가 일치하지 않습니다", "A_001");

        private final String message;
        private final String code;

        ErrorCode(String message, String code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return this.message;
        }

        public String getCode() {
            return this.code;
        }
    }

    private static class ErrorResponse {
        private String message;
        private String code;

        private ErrorResponse(String message, String code) {
            this.message = message;
            this.code = code;
        }

        public static ErrorResponse of(String message, String code) {
            return new ErrorResponse(message, code);
        }

        public static ErrorResponse ofMessage(String message) {
            return new ErrorResponse(message, null);
        }

        public static ErrorResponse ofCode(String code) {
            return new ErrorResponse(null, code);
        }

        public static ErrorResponse ofErrorCode(ErrorCode code) {
            return new ErrorResponse(code.getMessage(), code.getCode());
        }

        public String getMessage() {
            return this.message;
        }

        public String getCode() {
            return this.code;
        }
    }

    /**
     * 정적 펙토리 메소드는 다양한 형태의 객체를 받아서 생성을 할수 있다.
     * 따라서 클라이언트입장에서 팩토리에 입력으로 전달하는 인자와 생성되는 객체의 필드의 이름이 겹치지 않을수 있다.
     * ErrorResponse.ofErrorCode 의 경우 입력은 ErrorCode 지만
     * ErrorResponse의 필드인 message, code와 다르다.
     */
    @DisplayName("정적 펙토리 메소드는 다양한 형태의 객체를 받아서 생성을 할수 있다.")
    @Test
    public void test() {
        ErrorResponse error1 = ErrorResponse.of("같은 이메일을 가진 계정이 존재합니다",  "A_002");
        ErrorResponse error2 = ErrorResponse.ofErrorCode(ErrorCode.PASSWORD_CONFORM_FAILED);

        assertThat(error1.getCode()).isEqualTo("A_002");
        assertThat(error2.getCode()).isEqualTo("A_001");

        assertThat(error1.getMessage()).isEqualTo("같은 이메일을 가진 계정이 존재합니다");
        assertThat(error2.getMessage()).isEqualTo("패스워드가 일치하지 않습니다");
    }

    @DisplayName("정적 펙토리 메소드는 이름을 가질수 있어, 같은 파라미터의 다른 생성자를 만들수 있다.")
    @Test
    public void test2() {
        ErrorResponse error1 = ErrorResponse.ofMessage("message");
        ErrorResponse error2 = ErrorResponse.ofCode("A_003");

        assertThat(error1.getMessage()).isEqualTo("message");
        assertThat(error2.getCode()).isEqualTo("A_003");
    }
}
