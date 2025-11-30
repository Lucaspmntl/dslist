package com.lucas.dslist.services;

import com.lucas.dslist.dto.user.NewUserRequestDTO;
import com.lucas.dslist.dto.user.UpdateUserRequestDTO;
import com.lucas.dslist.dto.user.UserResponseDTO;
import com.lucas.dslist.exceptions.ResourceNotFoundException;
import com.lucas.dslist.models.User;
import com.lucas.dslist.repositories.GameListRepository;
import com.lucas.dslist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameListRepository gameListRepository;


    public List<UserResponseDTO> findAll(){
        List<User> list = userRepository.findAll();

        return list.stream()
                .map(UserResponseDTO::new)
                .toList();
    }


    public UserResponseDTO findById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id " + userId));

        return new UserResponseDTO(user);
    }


    public void deleteById(Long userId){
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id " + userId));

        gameListRepository.deleteByUserId(userId);
        userRepository.deleteById(userId);
    }


    public UserResponseDTO create(NewUserRequestDTO dto){
        User createdUser = userRepository.save(new User(dto));

        return new UserResponseDTO(createdUser);
    }


    public UserResponseDTO update(UpdateUserRequestDTO dto, Long userId){
        User updatedUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id " + userId));

        if (dto.getDescription() != null)
            updatedUser.setDescription(dto.getDescription());

        if (dto.getEmail() != null)
            updatedUser.setEmail(dto.getEmail());

        if (dto.getNickname() != null)
            updatedUser.setNickName(dto.getNickname());

        if (dto.getFirstName() != null)
            updatedUser.setFirstName(dto.getFirstName());

        if (dto.getLastName() != null)
            updatedUser.setLastName(dto.getLastName());

        if (dto.getFullName() != null)
            updatedUser.setFullName(dto.getFullName());

        userRepository.save(updatedUser);
        return new UserResponseDTO(updatedUser);
    }
}
