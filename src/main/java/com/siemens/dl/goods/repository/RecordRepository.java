package com.siemens.dl.goods.repository;

import com.siemens.dl.goods.domain.Record;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Record entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

}
