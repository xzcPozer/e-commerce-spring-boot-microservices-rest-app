package com.sharf.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlreadyExistException extends RuntimeException {
    private final String msg;
}
