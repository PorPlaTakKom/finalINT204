package com.example.int204class.repositorys;

import com.example.int204class.entitys.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, String> {
}
