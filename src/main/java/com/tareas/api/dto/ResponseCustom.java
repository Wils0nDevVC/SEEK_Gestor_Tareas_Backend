package com.tareas.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCustom<T> {
    private int state;
    private T value;
    private String message;

    public ResponseCustom() {}

    public ResponseCustom(T value, int state, String message) {
        this.value = value;
        this.state = state;
        this.message = message;
    }

    public static <T> ResponseCustom<T> success(T value, String message) {
        return new ResponseCustom<>(value, 200, message);
    }

    public static <T> ResponseCustom<T> error(String message, int state) {
        return new ResponseCustom<>(null, state, message);
    }
}
