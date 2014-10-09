package com.mega.pack;

import java.io.Serializable;
import java.util.List;

public class Card implements Serializable
{
	private String Name;//����
	private String Code;//6λ����
	private String Media;//����
	private String Group;//��Ʒ��֯
	private String CardGrade;//�ȼ�
	private String CardBranch;//������
	private String CardBz;//��Ƭ����
	private String CardDetails;//��Ƭ����
	private String Double_Cur;//�Ƿ�˫���ֿ�
	private String PROD_KIND;//8λ����
	private String ColorCard;//���տ���ʶ
	private String CardSingleCode;//��������ʶ
	private String StudentCard;//ѧ������ʶ    1:��ѧ����  0:ѧ����  
	private String LowLevel;//�Ƿ�ɽ���    1:��   0:��
	private String[] CardLayout;//������Ϣ
	private String Base64String;//ͼƬBASE64��Ϣ
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
