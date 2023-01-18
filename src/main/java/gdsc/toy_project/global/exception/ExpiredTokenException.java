package gdsc.toy_project.global.exception;

import gdsc.toy_project.global.error.exception.ProjectException;

public class ExpiredTokenException extends ProjectException {

    private static final String MESSAGE = "인증이 필요합니다.";

    public ExpiredTokenException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
