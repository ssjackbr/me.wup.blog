package me.wup.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.wup.blog.services.validation.UserInsertValid;
import me.wup.blog.services.validation.UserUpdateValid;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
@UserUpdateValid
public class UserUpdateDTO extends UserDTO {
        private static final long serialVersionUID = 1L;

}
