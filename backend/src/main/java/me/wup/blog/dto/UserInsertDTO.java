package me.wup.blog.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO extends UserDTO {
        private static final long serialVersionUID = 1L;


        @NotBlank(message  = "Required password field!")
        private String password;

        UserInsertDTO(){
                super();
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
}
