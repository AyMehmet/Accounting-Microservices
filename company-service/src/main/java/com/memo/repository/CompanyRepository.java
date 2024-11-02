package com.memo.repository;



import com.memo.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyByTitle(String title);

    boolean existsByTitle(String title);

    boolean existsByIdIs(Long companyId);

}
