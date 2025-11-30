package com.lucas.dslist.models;

import com.lucas.dslist.dto.user.NewUserRequestDTO;
import com.lucas.dslist.dto.user.UserResponseDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;


@Getter
@Setter()
@EqualsAndHashCode
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private Boolean active = true;

    public User (UserResponseDTO dto){
        BeanUtils.copyProperties(dto, this);
    }

    public User(NewUserRequestDTO dto){
        BeanUtils.copyProperties(dto, this);
    }
}
