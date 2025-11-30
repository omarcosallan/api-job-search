package dev.marcos.api_job_search.mapper;

import dev.marcos.api_job_search.dto.user.UserRequestDTO;
import dev.marcos.api_job_search.dto.user.UserResponseDTO;
import dev.marcos.api_job_search.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDTO dto);
    UserResponseDTO toDTO(User entity);
}
