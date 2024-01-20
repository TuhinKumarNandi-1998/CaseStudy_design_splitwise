package com.example.casestudy_design_splitwise.Repositories;

import com.example.casestudy_design_splitwise.Models.Expense;
import com.example.casestudy_design_splitwise.Models.Group;
import com.example.casestudy_design_splitwise.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long id);
}
