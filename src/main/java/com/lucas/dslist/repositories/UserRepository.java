package com.lucas.dslist.repositories;

import com.lucas.dslist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
