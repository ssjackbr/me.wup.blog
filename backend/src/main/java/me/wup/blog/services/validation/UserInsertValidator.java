package me.wup.blog.services.validation;

import lombok.RequiredArgsConstructor;
import me.wup.blog.controller.exceptions.FieldMessage;
import me.wup.blog.dto.UserInsertDTO;
import me.wup.blog.entities.User;
import me.wup.blog.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    private final UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User userToValidateEmail = userRepository.findByEmail(dto.getEmail());
        if (userToValidateEmail != null) {
            list.add(new FieldMessage("email", "This email alread in database!"));
        }

        User userToValidateNickName = userRepository.findByNickName(dto.getNickName());
        if (userToValidateNickName != null) {
            list.add(new FieldMessage("nickName", "This nickName alread in database!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFiledName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}