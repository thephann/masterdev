package com.ghtk.demo_crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    public ResponseObject() {}

//    public ResponseObject(String status, String message, Object data) {
//        this.status = status;
//        this.message = message;
//        this.data = data;
//    }
}
