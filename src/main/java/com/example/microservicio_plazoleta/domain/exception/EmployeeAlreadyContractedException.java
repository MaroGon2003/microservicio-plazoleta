package com.example.microservicio_plazoleta.domain.exception;

public class EmployeeAlreadyContractedException extends RuntimeException{

    public EmployeeAlreadyContractedException(String message){
        super(message);
    }

}
