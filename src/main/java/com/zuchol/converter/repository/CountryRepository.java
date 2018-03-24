package com.zuchol.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zuchol.converter.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>  {

}
