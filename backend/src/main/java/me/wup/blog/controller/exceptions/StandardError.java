package me.wup.blog.controller.exceptions;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private List<FieldMessage> listErrors = new ArrayList<>();

    public void addError (String fieldName, String message){
        listErrors.add(new FieldMessage(fieldName, message));
    }
}
