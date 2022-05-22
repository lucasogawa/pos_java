package com.ogawalucas.cities.modules.users.repositories;

import com.ogawalucas.cities.modules.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String nome);
}
