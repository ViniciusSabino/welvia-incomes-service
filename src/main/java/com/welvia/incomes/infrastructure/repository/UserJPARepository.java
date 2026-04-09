package com.welvia.incomes.infrastructure.repository;

import com.welvia.incomes.infrastructure.entitiy.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<UsersEntity, Long> { }