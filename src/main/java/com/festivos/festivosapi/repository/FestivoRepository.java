package com.festivos.festivosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.festivos.festivosapi.entity.FestivoEntity;

public interface FestivoRepository extends JpaRepository<FestivoEntity, Long> {

}