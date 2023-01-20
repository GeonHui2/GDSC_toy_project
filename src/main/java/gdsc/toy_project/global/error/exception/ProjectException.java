package gdsc.toy_project.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ProjectException extends RuntimeException{

    private ErrorCode errorCode;
}
