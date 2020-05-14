package com.dms.entity;

import java.util.List;

public class Dormitory {
	private String dorid;// 寝室ID
	private Building building;// 所属楼栋
	private String bid;// 所属楼栋
	private Integer number; // 能住人数
	private Integer nownumber;// 现住人数
	private String num;// 寝室号
	private Integer integral;
	private String img;
	private List<Position>list;

	public List<Position> getList() {
		return list;
	}

	public void setList(List<Position> list) {
		this.list = list;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getDorid() {
		return dorid;
	}

	public void setDorid(String dorid) {
		this.dorid = dorid;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getNownumber() {
		return nownumber;
	}

	public void setNownumber(Integer nownumber) {
		this.nownumber = nownumber;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}
