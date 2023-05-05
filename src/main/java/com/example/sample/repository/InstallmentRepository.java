package com.example.sample.repository;

import com.example.sample.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Integer> {
    List<Installment> findByParentId(int parentId);
}
