package com.mega.pack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PreObject
{
	private int age;
	private int sex;
	private int education;
	private int marriage;
	private int offernum;
	private int grouptype;
	private int jobtype;
	private int position;
	private int title;
	private int workyear;
	private int income;
	private int evincome;
	private int house;

	public void setAge(String age)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int iage;
		try
		{
			Date date = sdf.parse(age);
			iage = getAgeByBirthday(date);
			if (iage>=16 && iage <= 20)
			{
				this.age = 30;
			}else if (iage>=16 && iage <= 20) {
				this.age = 40;
			}else if (iage>=21 && iage <= 25) {
				this.age = 50;
			}else if (iage>=26 && iage <= 30) {
				this.age = 60;
			}else if (iage>=31 && iage <= 35) {
				this.age = 80;
			}else if (iage>=36 && iage <= 40) {
				this.age = 90;
			}else if (iage>=41 && iage <= 45) {
				this.age = 70;
			}else if (iage>=46 && iage <= 50) {
				this.age = 50;
			}else if (iage>=56) {
				this.age = 40;
			}
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void setSex(String sex)
	{
		if (sex.equals("M"))
		{
			this.sex = 10;
		}
		else {
			this.sex = 20;
		}
	}

	public void setEducation(String education)
	{
		if (education.equals("0"))
		{
			this.education = 90;
		}else if (education.equals("2")) {
			this.education = 60;
		}else if (education.equals("3")) {
			this.education = 30;
		}else if (education.equals("4")) {
			this.education = 10;
		}
	}

	public void setMarriage(String marriage)
	{
		if (marriage.endsWith("0"))
		{
			this.marriage = 30;
		}else if (marriage.endsWith("1")) {
			this.marriage = 50;
		}else if (marriage.endsWith("2")) {
			this.marriage = 40;
		}else if (marriage.endsWith("3")) {
			this.marriage = 20;
		}
	}

	public void setOffernum(String offernum)
	{
//		if (offernum.equals("0-1人"))
//		{
//			this.offernum = 50;
//		}else if (offernum.equals("2-3人"))
//		{
//			this.offernum = 40;
//		}else if (offernum.equals("4人"))
//		{
//			this.offernum = 30;
//		}else if (offernum.equals("4人以上"))
//		{
//			this.offernum = 20;
//		}
		this.offernum = 0;
	}

	public void setGrouptype(String grouptype)
	{
//		if (grouptype.equals("行政机关、事业团体、社会团体"))
//		{
//			this.grouptype = 60;
//		}else if (grouptype.equals("企业"))
//		{
//			this.grouptype = 50;
//		}else if (grouptype.equals("个体"))
//		{
//			this.grouptype = 40;
//		}else if (grouptype.equals("其它"))
//		{
//			this.grouptype = 30;
//		}
		this.grouptype = 0;
	}

	public void setJobtype(String jobtype)
	{
		if (jobtype.equals("0"))
		{
			this.jobtype = 100;
		}else if (jobtype.equals("1"))
		{
			this.jobtype = 90;
		}else if (jobtype.equals("2"))
		{
			this.jobtype = 80;
		}else if (jobtype.equals("3"))
		{
			this.jobtype = 70;
		}else if (jobtype.equals("4"))
		{
			this.jobtype = 60;
		}else if (jobtype.equals("5"))
		{
			this.jobtype = 50;
		}else if (jobtype.equals("6"))
		{
			this.jobtype = 40;
		}else if (jobtype.equals("7"))
		{
			this.jobtype = 30;
		}
	}

	public void setPosition(String position)
	{
		if (position.equals("0"))
		{
			this.position = 80;
		}else if (position.equals("1"))
		{
			this.position = 70;
		}else if (position.equals("2"))
		{
			this.position = 60;
		}else if (position.equals("3"))
		{
			this.position = 50;
		}else if (position.equals("A"))
		{
			this.position = 80;
		}else if (position.equals("B"))
		{
			this.position = 70;
		}else if (position.equals("C"))
		{
			this.position = 60;
		}else if (position.equals("D"))
		{
			this.position = 40;
		}
	}

	public void setTitle(String title)
	{
		if (title.equals("0"))
		{
			this.title = 50;
		}else if (title.equals("1"))
		{
			this.title = 40;
		}else if (title.equals("2"))
		{
			this.title = 30;
		}else if (title.equals("3"))
		{
			this.title =20;
		}
	}

	public void setWorkyear(String workyear)
	{
		int year = Integer.valueOf(workyear);
		if (year>10)
		{
			this.workyear = 60;
		}else if (year>=6 && year<=10) {
			this.workyear = 50;
		}else if (year>=3 && year<=5) {
			this.workyear = 40;
		}else if (year <3) {
			this.workyear = 30;
		}
	}

	public void setIncome(String income)
	{
		long in = Long.valueOf(income)/100;
		if (in<=10000)
		{
			this.income = 50;
		}else if (in>10000 && in<=20000) {
			this.income = 60;
		}else if (in>20000 && in<=30000) {
			this.income = 70;
		}else if (in>30000 && in<=40000) {
			this.income = 80;
		}else if (in>40000 && in<=50000) {
			this.income = 90;
		}else if (in>50000 && in<=60000) {
			this.income = 100;
		}else if (in>60000 && in<=70000) {
			this.income = 110;
		}else if (in>70000 && in<=80000) {
			this.income = 120;
		}else if (in>80000 && in<=90000) {
			this.income = 130;
		}else if (in>90000 && in<=100000) {
			this.income = 140;
		}else if (in>100000) {
			this.income = 150;
		}
	}

	public void setEvincome(String evincome)
	{
//		if (evincome.equals("10000元以下"))
//		{
//			this.evincome = 10;
//		}else if (evincome.equals("10001-20000元")) {
//			this.evincome = 20;
//		}else if (evincome.equals("20001-30000元")) {
//			this.evincome = 30;
//		}else if (evincome.equals("30001-40000元")) {
//			this.evincome = 40;
//		}else if (evincome.equals("40001-50000元")) {
//			this.evincome = 50;
//		}else if (evincome.equals("50001-60000元")) {
//			this.evincome = 60;
//		}else if (evincome.equals("60001-70000元")) {
//			this.evincome = 70;
//		}else if (evincome.equals("70001-80000元")) {
//			this.evincome = 80;
//		}else if (evincome.equals("80001-90000元")) {
//			this.evincome = 90;
//		}else if (evincome.equals("90000元以上")) {
//			this.evincome = 100;
//		}
		this.evincome = 50;
	}

	public void setHouse(String house)
	{
		if (house.equals("0"))
		{
			this.house = 100;
		}else if (house.equals("1"))
		{
			this.house = 80;
		}else if (house.equals("2"))
		{
			this.house = 50;
		}else if (house.equals("3"))
		{
			this.house = 40;
		}else if (house.equals("4"))
		{
			this.house = 20;
		}
	}

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		int score = age
				+sex
				+education
				+marriage
				+offernum
				+grouptype
				+jobtype
				+position
				+title
				+workyear
				+income
				+evincome
				+house;
		return String.valueOf(score);
	}
	
	public static int getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			return -1;
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth 
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth 
				age--;
			}
		}
		return age;
	}
}
