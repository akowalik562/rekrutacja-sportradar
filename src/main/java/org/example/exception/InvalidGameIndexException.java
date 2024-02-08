package org.example.exception;

public class InvalidGameIndexException extends RuntimeException {
    public InvalidGameIndexException(int invalidGameIndex) {
        super("Invalid game index: " + invalidGameIndex);
    }
}
