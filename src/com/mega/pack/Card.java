package com.mega.pack;

import java.io.Serializable;
import java.util.List;

public class Card implements Serializable
{
	private String Name;//名称
	private String Code;//6位代码
	private String Media;//介质
	private String Group;//产品组织
	private String CardGrade;//等级
	private String CardBranch;//发卡行
	private String CardBz;//卡片币种
	private String CardDetails;//卡片介绍
	private String Double_Cur;//是否双币种卡
	private String PROD_KIND;//8位编码
	private String ColorCard;//彩照卡标识
	private String CardSingleCode;//联名卡标识
	private String StudentCard;//学生卡标识    1:非学生卡  0:学生卡  
	private String LowLevel;//是否可降档    1:是   0:否
	private String[] CardLayout;//版面信息
	private String Base64String;//图片BASE64信息
	public Card(){}
	
	
	public String getStudentCard()
	{
		return StudentCard;
	}


	public void setStudentCard(String studentCard)
	{
		StudentCard = studentCard;
	}


	public String getLowLevel()
	{
		return LowLevel;
	}


	public void setLowLevel(String lowLevel)
	{
		LowLevel = lowLevel;
	}


	public String getCardBz()
	{
		return CardBz;
	}


	public void setCardBz(String cardBz)
	{
		CardBz = cardBz;
	}


	public String getDouble_Cur()
	{
		return Double_Cur;
	}


	public void setDouble_Cur(String double_Cur)
	{
		Double_Cur = double_Cur;
	}


	public String getPROD_KIND()
	{
		return PROD_KIND;
	}


	public void setPROD_KIND(String pROD_KIND)
	{
		PROD_KIND = pROD_KIND;
	}


	public String getColorCard()
	{
		return ColorCard;
	}


	public void setColorCard(String colorCard)
	{
		ColorCard = colorCard;
	}


	public String getCardSingleCode()
	{
		return CardSingleCode;
	}


	public void setCardSingleCode(String cardSingleCode)
	{
		CardSingleCode = cardSingleCode;
	}


	public String getName()
	{
		return Name;
	}
	public void setName(String name)
	{
		Name = name;
	}
	public String getCode()
	{
		return Code;
	}
	public void setCode(String code)
	{
		Code = code;
	}
	public String getMedia()
	{
		return Media;
	}
	public void setMedia(String media)
	{
		Media = media;
	}
	public String getGroup()
	{
		return Group;
	}
	public void setGroup(String group)
	{
		Group = group;
	}
	public String getCardGrade()
	{
		return CardGrade;
	}
	public void setCardGrade(String cardGrade)
	{
		CardGrade = cardGrade;
	}
	public String getCardBranch()
	{
		return CardBranch;
	}
	public void setCardBranch(String cardBranch)
	{
		CardBranch = cardBranch;
	}
	public String getCardDetails()
	{
		return CardDetails;
	}
	public void setCardDetails(String cardDetails)
	{
		this.CardDetails = cardDetails;
	}


	public String[] getCardLayout()
	{
		return CardLayout;
	}


	public void setCardLayout(String[] cardLayout)
	{
		CardLayout = cardLayout;
	}


	public String getBase64String()
	{
		return Base64String;
	}


	public void setBase64String(String base64String)
	{
		Base64String = base64String;
	}
}
