package cl.heroes.client.exception;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String code;
    private Object error;
    private String path;

    public ErrorResponse(int statusCode, Date timestamp, String message, String code, String path) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.code = code;
        this.path = path;
    }

    public ErrorResponse(int statusCode, Date timestamp, String message, String code, Object error, String path) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.code = code;
        this.error = error;
        this.path = path;
    }
}