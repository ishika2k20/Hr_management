package com.nagarro.employeedatabase.repositories;

import com.nagarro.employeedatabase.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
