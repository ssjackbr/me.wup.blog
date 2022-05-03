package me.wup.blog.dto;

import lombok.*;
import me.wup.blog.entities.Role;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    public RoleDTO (Role role) {
        id = role.getId();
        authority = role.getAuthority();
    }

}
