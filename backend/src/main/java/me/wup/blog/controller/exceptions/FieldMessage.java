package me.wup.blog.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String filedName;
    private String message;
}
