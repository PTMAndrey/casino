package com.usv.casino.exceptions;

public class CrudOperationException extends RuntimeException{
    public CrudOperationException(String mesaj){
        super(mesaj);
    }
}
