package com.lucas.dslist.controllers;

import com.lucas.dslist.dto.MessageDTO;
import com.lucas.dslist.dto.user.NewUserRequestDTO;
import com.lucas.dslist.dto.user.UpdateUserRequestDTO;
import com.lucas.dslist.dto.user.UserResponseDTO;
import com.lucas.dslist.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<UserResponseDTO> users = userService.findAll();

        return ResponseEntity.ok(users);
    }


    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long userId){
        UserResponseDTO user = userService.findById(userId);

        return ResponseEntity.ok(user);
    }


    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<MessageDTO> deleteById(@PathVariable Long userId){
        userService.deleteById(userId);
        return ResponseEntity.ok(new MessageDTO("Usuário de Id " + userId + " excluído com sucesso!"));
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody NewUserRequestDTO dto){
        UserResponseDTO createdUser = userService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserResponseDTO> update(@Valid @RequestBody UpdateUserRequestDTO dto,
                                                  @PathVariable Long userId){
        UserResponseDTO updatedUser = userService.update(dto, userId);

        return ResponseEntity.ok(updatedUser);
    }
}
