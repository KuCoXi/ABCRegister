package com.mega.pack;

import java.io.Serializable;

import android.R.integer;

public class Person implements Serializable
{
	/**
	 * 姓名
	 */
	private String peopleName;

	/**
	 * 性别
	 */
	private String peopleSex;

	/**
	 * 民族
	 */
	private String peopleNation;

	/**
	 * 出生日期
	 */
	private String peopleBirthday;

	/**
	 * 住址
	 */
	private String peopleAddress;

	/**
	 * 身份证号
	 */
	private String peopleIDCode;

	/**
	 * 签发机关
	 */
	private String department;

	/**
	 * 有效期限：开始
	 */
	private String startDate;

	/**
	 * 有效期限：结束
	 */
	private String endDate;

	/**
	 * 身份证头像
	 */
	private byte[] photo;
	private String base64;

	/**
	 * 没有解析成图片的数据大小一般为1024字节
	 */
	private byte[] headImage;

	/**
	 * 三代证指纹模板数据，正常位1024，如果为null，说明为二代证，没有指纹模板数据
	 */
	private byte[] model;
	
	private int errcode = -1;
	
	private String errmsg;

	public String getErrmsg()
	{
		return errmsg;
	}

	public void setErrmsg(String errmsg)
	{
		this.errmsg = errmsg;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getPeopleSex() {
		return peopleSex;
	}

	public void setPeopleSex(String peopleSex) {
		this.peopleSex = peopleSex;
	}

	public String getPeopleNation() {
		return peopleNation;
	}

	public void setPeopleNation(String peopleNation) {
		this.peopleNation = peopleNation;
	}

	public String getPeopleBirthday() {
		return peopleBirthday;
	}

	public void setPeopleBirthday(String peopleBirthday) {
		this.peopleBirthday = peopleBirthday;
	}

	public String getPeopleAddress() {
		return peopleAddress;
	}

	public void setPeopleAddress(String peopleAddress) {
		this.peopleAddress = peopleAddress;
	}

	public String getPeopleIDCode() {
		return peopleIDCode;
	}

	public void setPeopleIDCode(String peopleIDCode) {
		this.peopleIDCode = peopleIDCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getHeadImage() {
		return headImage;
	}

	public void setHeadImage(byte[] headImage) {
		this.headImage = headImage;
	}

	public byte[] getModel() {
		return model;
	}

	public void setModel(byte[] model) {
		this.model = model;
	}

	public String getBase64()
	{
		return base64;
	}

	public void setBase64(String base64)
	{
		this.base64 = base64;
	}

	public int getErrcode()
	{
		return errcode;
	}

	public void setErrcode(int errcode)
	{
		this.errcode = errcode;
	}


}
