package me.wup.blog.controller.exceptions;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError extends StandardError {

    private List<FieldMessage> listErrors = new ArrayList<>();

    public void addError (String fieldName, String message){
        listErrors.add(new FieldMessage(fieldName, message));
    }
}
