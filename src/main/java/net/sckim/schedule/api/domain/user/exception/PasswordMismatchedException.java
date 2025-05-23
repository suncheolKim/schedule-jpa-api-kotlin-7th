package net.sckim.schedule.api.domain.user.exception;

public class PasswordMismatchedException extends RuntimeException {
    public PasswordMismatchedException(String msg) {
        super(msg);
    }
}
