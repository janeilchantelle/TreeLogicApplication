package com.keyin.TreeLogic.repository;

import com.keyin.TreeLogic.model.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRecordRepository extends JpaRepository<TreeRecord, Long> {
}
