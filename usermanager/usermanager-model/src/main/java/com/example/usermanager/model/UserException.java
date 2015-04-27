package com.example.usermanager.model;

public class UserException extends RuntimeException {

    /**
     * Creates a new instance of <code>UserException</code> without detail
     * message.
     */
    public UserException() {
    }

    /**
     * Constructs an instance of <code>UserException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UserException(String msg) {
        super(msg);
    }
}
