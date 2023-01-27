package com.es.mindata.heroeschallenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.es.mindata.heroeschallenge.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findOneByUsername(String username);
}
