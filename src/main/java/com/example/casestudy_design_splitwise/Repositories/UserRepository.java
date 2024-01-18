package com.example.casestudy_design_splitwise.Repositories;

import com.example.casestudy_design_splitwise.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
}
