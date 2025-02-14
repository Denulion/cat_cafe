package lt.techin.dto;

import lt.techin.model.User;

import java.util.ArrayList;

public class UserRequestMapper {


    public static User toUser(UserRequestDTO userRequestDTO) {
        User user = new User();

        user.setUsername(userRequestDTO.username());
        user.setPassword(userRequestDTO.password());
        user.setRoles(new ArrayList<>(RoleMapper.toRoleListFromDTO(userRequestDTO.roles())));

        return user;
    }
}
