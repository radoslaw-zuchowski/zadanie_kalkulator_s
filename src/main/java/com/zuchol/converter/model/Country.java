package com.zuchol.converter.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="countries")
public class Country implements Serializable {
	private static final long serialVersionUID = 1868223488176660327L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private String currencyCode;
	@Column
	private BigDecimal constantVariable;
	@Column
	private BigDecimal tax;
	
}
