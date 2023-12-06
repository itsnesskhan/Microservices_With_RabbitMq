package com.all.fraud;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FaraudCheckRepository extends JpaRepository<FraudCheckHistory, Integer> {

}
