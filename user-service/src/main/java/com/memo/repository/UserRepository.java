package com.memo.repository;



import com.memo.entity.Company;
import com.memo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);
    User findByUsername(String username);
    List<User> findAllByRole_Description(String roleDescription);
    List<User> findAllByCompany(Company company);
    Integer countAllByCompanyAndRole_Description(Company company, String role);
}
