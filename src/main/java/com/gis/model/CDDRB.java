package com.gis.model;

import java.io.Serializable;

public class CDDRB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String value;
	private String code;
	
	private int circle_id;
	private String circle_nm;
	private String compartment;
	private String circle_cod;
	private int district_id;
	private String district_nm;
	private String district_cod;
	private int division_id;
	private String division_nm;
	private String division_cod;
	private int range_id;
	private String range_nm;
	private String range_cod;
	private int beat_id;
	private String beat_nm;
	private String beat_cod;
	private String source;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCircle_id() {
		return circle_id;
	}
	public void setCircle_id(int circle_id) {
		this.circle_id = circle_id;
	}
	public String getCircle_nm() {
		return circle_nm;
	}
	public void setCircle_nm(String circle_nm) {
		this.circle_nm = circle_nm;
	}
	public String getCircle_cod() {
		return circle_cod;
	}
	public void setCircle_cod(String circle_cod) {
		this.circle_cod = circle_cod;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public String getDistrict_nm() {
		return district_nm;
	}
	public void setDistrict_nm(String district_nm) {
		this.district_nm = district_nm;
	}
	public String getDistrict_cod() {
		return district_cod;
	}
	public void setDistrict_cod(String district_cod) {
		this.district_cod = district_cod;
	}
	public int getDivision_id() {
		return division_id;
	}
	public void setDivision_id(int division_id) {
		this.division_id = division_id;
	}
	public String getDivision_nm() {
		return division_nm;
	}
	public void setDivision_nm(String division_nm) {
		this.division_nm = division_nm;
	}
	public String getDivision_cod() {
		return division_cod;
	}
	public void setDivision_cod(String division_cod) {
		this.division_cod = division_cod;
	}
	public int getRange_id() {
		return range_id;
	}
	public void setRange_id(int range_id) {
		this.range_id = range_id;
	}
	public String getRange_nm() {
		return range_nm;
	}
	public void setRange_nm(String range_nm) {
		this.range_nm = range_nm;
	}
	public String getRange_cod() {
		return range_cod;
	}
	public void setRange_cod(String range_cod) {
		this.range_cod = range_cod;
	}
	public int getBeat_id() {
		return beat_id;
	}
	public void setBeat_id(int beat_id) {
		this.beat_id = beat_id;
	}
	public String getBeat_nm() {
		return beat_nm;
	}
	public void setBeat_nm(String beat_nm) {
		this.beat_nm = beat_nm;
	}
	public String getBeat_cod() {
		return beat_cod;
	}
	public void setBeat_cod(String beat_cod) {
		this.beat_cod = beat_cod;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCompartment() {
		return compartment;
	}
	public void setCompartment(String compartment) {
		this.compartment = compartment;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}