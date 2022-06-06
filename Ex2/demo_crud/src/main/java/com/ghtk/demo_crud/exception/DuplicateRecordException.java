package com.ghtk.demo_crud.exception;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String mess) {
        super(mess);
    }
}
