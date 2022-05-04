package me.wup.blog.controller.exceptions;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String filedName;
    private String message;
}
