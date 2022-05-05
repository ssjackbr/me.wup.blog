package me.wup.blog.services.validation;

import lombok.RequiredArgsConstructor;
import me.wup.blog.controller.exceptions.FieldMessage;
import me.wup.blog.dto.UserUpdateDTO;
import me.wup.blog.entities.User;
import me.wup.blog.repositories.UserRepository;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    private final UserRepository userRepository;

    private final HttpServletRequest request;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {


        var userVar = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long userId = Long.parseLong(userVar.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        User userToValidate = userRepository.findByEmail(dto.getEmail());
        if (userToValidate != null && userToValidate.getId() != userId) {
            list.add(new FieldMessage("email", "This email alread in database!"));
        }

        User userToValidateNickName = userRepository.findByNickName(dto.getNickName());
        if (userToValidateNickName != null && userToValidateNickName.getId() != userId) {
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