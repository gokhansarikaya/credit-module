package com.credit.module.loan.service.application.exception.handler;

import com.credit.module.application.handler.ErrorDTO;
import com.credit.module.application.handler.GlobalExceptionHandler;
import com.credit.module.loan.service.domain.LoanDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class LoanGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {LoanDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(LoanDomainException loanDomainException) {
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(loanDomainException.getMessage())
                .build();
    }

/*    @ResponseBody
    @ExceptionHandler(value = {LoanDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(LoanDomainException loanDomainException) {
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(loanDomainException.getMessage())
                .build();
    }*/
}
