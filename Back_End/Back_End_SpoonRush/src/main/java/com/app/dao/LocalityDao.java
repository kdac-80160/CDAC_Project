package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Locality;

public interface LocalityDao extends JpaRepository<Locality, Long> {

}
