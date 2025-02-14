package lt.techin.dto;

import lt.techin.model.User;

import java.util.List;

public class UserResponseMapper {


    public static List<UserResponseDTO> toUserResponseDTOList(List<User> userList) {
        return userList.stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getUsername(), RoleMapper.toRoleDTOList(user)))
                .toList();
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), RoleMapper.toRoleDTOList(user));
    }
}
