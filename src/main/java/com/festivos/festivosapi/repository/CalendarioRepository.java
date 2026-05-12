package com.festivos.festivosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.festivos.festivosapi.entity.CalendarioEntity;

@Repository
public interface CalendarioRepository extends JpaRepository<CalendarioEntity, Long> {

}