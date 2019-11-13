package com.tq.generator.util;

/**
 * @author Tian Qi
 * 2019/11/11
 **/
public class ParameterNullException extends Exception {


    public ParameterNullException (){

    }

    public ParameterNullException (String message){
        super(message);
    }

    public ParameterNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterNullException(Throwable cause) {
        super(cause);
    }

}
