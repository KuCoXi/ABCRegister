package com.mega.pack;

public class CardMsg
{
	private String APP_NUM;//申请编号
	private String CARD_NAME;//卡片中文名
	private boolean ISSTUDENT;//是否学生卡
	
	private String APPFORM_TYPE; //申请表记录资料类型
	private String APPFORM_CON; //申请表记录资料组成部分
	private String APPINFO_TYPE; //申请信息类型
	private String NET_NODE_NO; //受理机构编号
	private String PROD_KIND; //申请产品代码
	private String CARD_MARK; //品牌
	private String CARD_TYPE; //卡种
	private String CARD_KIND; //卡类
	private String NORMAL_CARD; //若申请卡种未获批准，是否同意申办其他等级贷记卡
	private String DOUBLE_CUR; //是否申请双币种卡
	
	private String APP_LMT;//拟申请额度（人民币）
	private String COLOR_CARD;//彩照卡标志
	private String EMPLOYEE;//是否本行员工
	private String APP_CUR;//申请币种
	private String CUST_NAME;//姓名（中文）
	private String LAST_NAME;//姓（拼音）
	private String FIRST_NAME;//名（拼音）
	private String CUST_SEX;//性别
	private String BIRTHDAY;//出生日期
	private String MARR_STAT;//婚姻状况
	
	private String NATIONALITY;//国籍
	private String FAMI_MEMBER;//家庭人口
	private String CERT_TYPE;//证件类型
	private String CERT_NO;//证件号码
	private String CERT_NAME;//其他（证件）名称
	private String EDU_DEGR;//教育程度
	private String PRE_ADDR;//现住址
	private String PRE_CITY_CODE;//现住址城市代码
	private String PRE_POST;//现住址邮编
	private String PRE_YEARS;//现住址居住年限
	
	private String PRE_ZONE_NO;//现住址电话（区号）
	private String PRE_PHONE;//现住址电话号码
	private String PRE_MOBILE;//移动电话号码
	private String EMAIL;//电子信箱
	private String PRE_REG_ADDR;//户籍地址是否同现住址
	private String REG_ADDR;//户籍地址
	private String REG_POST;//户籍地址邮编
	private String PER_INCOME;//个人年收入
	private String AVE_INCOME;//家庭人均年收入
	private String HOU_STAT;//住宅状况
	
	private String HOU_MON_LOAN;//按揭贷款月还款
	private String CAR_NO;//车牌号
	private String CAR_TYPE;//品牌
	private String CAR_AGE;//车龄
	private String CAR_MON_LOAN;//若为贷款购车，月还款
	private String COMP_NAME;//单位全称
	private String COMP_ADDR;//单位地址
	private String COMP_CITY_CODE;//单位城市代码
	private String COMP_POST;//单位邮编
	private String COMP_ZONE_NO;//单位电话区号
	
	private String COMP_PHONE;//单位电话号码
	private String COMP_CHAR;//单位性质
	private String TRADE_KIND;//-行业类别
	private String TECH_GRADE;//职称
	private String TECH_POSI;//职务
	private String WORK_YEAR;//现单位工作年限
	private String REMAIN_QUES;//预留问题
	private String REMAIN_ANS;//预留答案
	private String GET_CARD_MODE;//领卡方式
	private String POST_ADDR;//邮寄地址
	
	private String LM_NAME;//联系人姓名（中文）
	private String LM_SEX;//联系人性别
	private String LM_COMP;//联系人工作单位
	private String LM_COMP_ZONENO;//联系人单位电话（区号）
	private String LM_COMP_PHONE;//联系人单位电话号码
	private String LM_PRE_ADDR;//-联系人现住址
	private String LM_PRE_ZONENO;//-联系人现住址电话区号
	private String LM_PRE_PHONE;//-联系人现住址电话号码
	private String LM_MOBILE;//联系人移动电话号码
	private String RELATION_BET;//与申请人关系
	
	private String CARD_SINGLE_CODE;//-联名卡标识码
	private String APP_SOURCE;//申请表来源
	private String EXAM_NODE;//调查方式
	private String EXAM_NODE_NO;//受理机构代码
	private String CUST_MANAGER;//客户经理代码
	private String SALES_CODE;//促销代码
	private String REPAY_CARD;//还款卡号
	private String REPAY_MARK;//-还款方式
	private String CURRENCY;//约定还款本外币标志
	private String START_DATE;//-还款起始日期
	
	private String EXPIRE_DATE;//还款终止日期
	private String INPUT_OPERATOR;//-资料录入员
	private String INPUT_DATE;//-资料录入日期
	private String PREP2;//-参加1＋N活动的营销人编号
	private String LAYOUT_FLAG;//版面信息
	private String MEMBER_ID;//-会员编号
	private String PRE2;//固定转存金额
	private String PRE4;//账单方式
	private String ORGA;//单位组织机构代码
	private String REF_INFO;//推荐人信息
	 
	private String CAREER;//职业
	private String ABCCARD;//是否已有我行贷记卡
	private String HUKOU_RELNAME;//户籍联系人姓名（中文）
	private String HUKOU_RELSEX;//户籍联系人性别
	private String HUKOU_RELATION;//户籍联系人与申请人关系
	private String HUKOU_RELZONENO;//户籍联系人电话区号
	private String HUKOU_RELPHONE;//-户籍联系人电话号码
	private String HUKOU_RELMOBILE;//户籍联系人手机
	private String MANAGER_MOBILE;//营销人手机
	private String agentPerson;//受理人
	 
	private String PROCESSER_MOBILE;//受理人员手机
	private String PROCESSER_ZONENO;//受理人员电话区号
	private String PROCESSER_PHONE;//受理人员电话号码
	private String CHANNEL_ID;//渠道代码
	private String ACT_ID;//-活动代码
	private String ASSURE_TYPE;//-一般授信额度担保方式
	private String ASSURE_LMT;//一般授信宽度担保金额
	private String BUDGET_PROD;//专项分期产品代码
	private String BUDGET_LMT;//专项分期限额申请额度
	private String BUDGET_ASSURE_TYPE;//分期限额担保方式
	 
	private String FILLER;//备用域

	public String getAPPFORM_TYPE()
	{
		return APPFORM_TYPE;
	}

	public void setAPPFORM_TYPE(String aPPFORM_TYPE)
	{
		APPFORM_TYPE = aPPFORM_TYPE;
	}

	public String getAPPFORM_CON()
	{
		return APPFORM_CON;
	}

	public void setAPPFORM_CON(String aPPFORM_CON)
	{
		APPFORM_CON = aPPFORM_CON;
	}

	public String getAPPINFO_TYPE()
	{
		return APPINFO_TYPE;
	}

	public void setAPPINFO_TYPE(String aPPINFO_TYPE)
	{
		APPINFO_TYPE = aPPINFO_TYPE;
	}

	public String getNET_NODE_NO()
	{
		return NET_NODE_NO;
	}

	public void setNET_NODE_NO(String nET_NODE_NO)
	{
		NET_NODE_NO = nET_NODE_NO;
	}

	public String getPROD_KIND()
	{
		return PROD_KIND;
	}

	public void setPROD_KIND(String pROD_KIND)
	{
		PROD_KIND = pROD_KIND;
	}

	public String getCARD_MARK()
	{
		return CARD_MARK;
	}

	public void setCARD_MARK(String cARD_MARK)
	{
		CARD_MARK = cARD_MARK;
	}

	public String getCARD_TYPE()
	{
		return CARD_TYPE;
	}

	public void setCARD_TYPE(String cARD_TYPE)
	{
		CARD_TYPE = cARD_TYPE;
	}

	public String getCARD_KIND()
	{
		return CARD_KIND;
	}

	public void setCARD_KIND(String cARD_KIND)
	{
		CARD_KIND = cARD_KIND;
	}

	public String getNORMAL_CARD()
	{
		return NORMAL_CARD;
	}

	public void setNORMAL_CARD(String nORMAL_CARD)
	{
		NORMAL_CARD = nORMAL_CARD;
	}

	public String getDOUBLE_CUR()
	{
		return DOUBLE_CUR;
	}

	public void setDOUBLE_CUR(String dOUBLE_CUR)
	{
		DOUBLE_CUR = dOUBLE_CUR;
	}

	public String getAPP_LMT()
	{
		return APP_LMT;
	}

	public void setAPP_LMT(String aPP_LMT)
	{
		APP_LMT = aPP_LMT;
	}

	public String getCOLOR_CARD()
	{
		return COLOR_CARD;
	}

	public void setCOLOR_CARD(String cOLOR_CARD)
	{
		COLOR_CARD = cOLOR_CARD;
	}

	public String getEMPLOYEE()
	{
		return EMPLOYEE;
	}

	public void setEMPLOYEE(String eMPLOYEE)
	{
		EMPLOYEE = eMPLOYEE;
	}

	public String getAPP_CUR()
	{
		return APP_CUR;
	}

	public void setAPP_CUR(String aPP_CUR)
	{
		APP_CUR = aPP_CUR;
	}

	public String getCUST_NAME()
	{
		return CUST_NAME;
	}

	public void setCUST_NAME(String cUST_NAME)
	{
		CUST_NAME = cUST_NAME;
	}

	public String getLAST_NAME()
	{
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME)
	{
		LAST_NAME = lAST_NAME;
	}

	public String getFIRST_NAME()
	{
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME)
	{
		FIRST_NAME = fIRST_NAME;
	}

	public String getCUST_SEX()
	{
		return CUST_SEX;
	}

	public void setCUST_SEX(String cUST_SEX)
	{
		CUST_SEX = cUST_SEX;
	}

	public String getBIRTHDAY()
	{
		return BIRTHDAY;
	}

	public void setBIRTHDAY(String bIRTHDAY)
	{
		BIRTHDAY = bIRTHDAY;
	}

	public String getMARR_STAT()
	{
		return MARR_STAT;
	}

	public void setMARR_STAT(String mARR_STAT)
	{
		MARR_STAT = mARR_STAT;
	}

	public String getNATIONALITY()
	{
		return NATIONALITY;
	}

	public void setNATIONALITY(String nATIONALITY)
	{
		NATIONALITY = nATIONALITY;
	}

	public String getFAMI_MEMBER()
	{
		return FAMI_MEMBER;
	}

	public void setFAMI_MEMBER(String fAMI_MEMBER)
	{
		FAMI_MEMBER = fAMI_MEMBER;
	}

	public String getCERT_TYPE()
	{
		return CERT_TYPE;
	}

	public void setCERT_TYPE(String cERT_TYPE)
	{
		CERT_TYPE = cERT_TYPE;
	}

	public String getCERT_NO()
	{
		return CERT_NO;
	}

	public void setCERT_NO(String cERT_NO)
	{
		CERT_NO = cERT_NO;
	}

	public String getCERT_NAME()
	{
		return CERT_NAME;
	}

	public void setCERT_NAME(String cERT_NAME)
	{
		CERT_NAME = cERT_NAME;
	}

	public String getEDU_DEGR()
	{
		return EDU_DEGR;
	}

	public void setEDU_DEGR(String eDU_DEGR)
	{
		EDU_DEGR = eDU_DEGR;
	}

	public String getPRE_ADDR()
	{
		return PRE_ADDR;
	}

	public void setPRE_ADDR(String pRE_ADDR)
	{
		PRE_ADDR = pRE_ADDR;
	}

	public String getPRE_CITY_CODE()
	{
		return PRE_CITY_CODE;
	}

	public void setPRE_CITY_CODE(String pRE_CITY_CODE)
	{
		PRE_CITY_CODE = pRE_CITY_CODE;
	}

	public String getPRE_POST()
	{
		return PRE_POST;
	}

	public void setPRE_POST(String pRE_POST)
	{
		PRE_POST = pRE_POST;
	}

	public String getPRE_YEARS()
	{
		return PRE_YEARS;
	}

	public void setPRE_YEARS(String pRE_YEARS)
	{
		PRE_YEARS = pRE_YEARS;
	}

	public String getPRE_ZONE_NO()
	{
		return PRE_ZONE_NO;
	}

	public void setPRE_ZONE_NO(String pRE_ZONE_NO)
	{
		PRE_ZONE_NO = pRE_ZONE_NO;
	}

	public String getPRE_PHONE()
	{
		return PRE_PHONE;
	}

	public void setPRE_PHONE(String pRE_PHONE)
	{
		PRE_PHONE = pRE_PHONE;
	}

	public String getPRE_MOBILE()
	{
		return PRE_MOBILE;
	}

	public void setPRE_MOBILE(String pRE_MOBILE)
	{
		PRE_MOBILE = pRE_MOBILE;
	}

	public String getEMAIL()
	{
		return EMAIL;
	}

	public void setEMAIL(String eMAIL)
	{
		EMAIL = eMAIL;
	}

	public String getPRE_REG_ADDR()
	{
		return PRE_REG_ADDR;
	}

	public void setPRE_REG_ADDR(String pRE_REG_ADDR)
	{
		PRE_REG_ADDR = pRE_REG_ADDR;
	}

	public String getREG_ADDR()
	{
		return REG_ADDR;
	}

	public void setREG_ADDR(String rEG_ADDR)
	{
		REG_ADDR = rEG_ADDR;
	}

	public String getREG_POST()
	{
		return REG_POST;
	}

	public void setREG_POST(String rEG_POST)
	{
		REG_POST = rEG_POST;
	}

	public String getPER_INCOME()
	{
		return PER_INCOME;
	}

	public void setPER_INCOME(String pER_INCOME)
	{
		PER_INCOME = pER_INCOME;
	}

	public String getAVE_INCOME()
	{
		return AVE_INCOME;
	}

	public void setAVE_INCOME(String aVE_INCOME)
	{
		AVE_INCOME = aVE_INCOME;
	}

	public String getHOU_STAT()
	{
		return HOU_STAT;
	}

	public void setHOU_STAT(String hOU_STAT)
	{
		HOU_STAT = hOU_STAT;
	}

	public String getHOU_MON_LOAN()
	{
		return HOU_MON_LOAN;
	}

	public void setHOU_MON_LOAN(String hOU_MON_LOAN)
	{
		HOU_MON_LOAN = hOU_MON_LOAN;
	}

	public String getCAR_NO()
	{
		return CAR_NO;
	}

	public void setCAR_NO(String cAR_NO)
	{
		CAR_NO = cAR_NO;
	}

	public String getCAR_TYPE()
	{
		return CAR_TYPE;
	}

	public void setCAR_TYPE(String cAR_TYPE)
	{
		CAR_TYPE = cAR_TYPE;
	}

	public String getCAR_AGE()
	{
		return CAR_AGE;
	}

	public void setCAR_AGE(String cAR_AGE)
	{
		CAR_AGE = cAR_AGE;
	}

	public String getCAR_MON_LOAN()
	{
		return CAR_MON_LOAN;
	}

	public void setCAR_MON_LOAN(String cAR_MON_LOAN)
	{
		CAR_MON_LOAN = cAR_MON_LOAN;
	}

	public String getCOMP_NAME()
	{
		return COMP_NAME;
	}

	public void setCOMP_NAME(String cOMP_NAME)
	{
		COMP_NAME = cOMP_NAME;
	}

	public String getCOMP_ADDR()
	{
		return COMP_ADDR;
	}

	public void setCOMP_ADDR(String cOMP_ADDR)
	{
		COMP_ADDR = cOMP_ADDR;
	}

	public String getCOMP_CITY_CODE()
	{
		return COMP_CITY_CODE;
	}

	public void setCOMP_CITY_CODE(String cOMP_CITY_CODE)
	{
		COMP_CITY_CODE = cOMP_CITY_CODE;
	}

	public String getCOMP_POST()
	{
		return COMP_POST;
	}

	public void setCOMP_POST(String cOMP_POST)
	{
		COMP_POST = cOMP_POST;
	}

	public String getCOMP_ZONE_NO()
	{
		return COMP_ZONE_NO;
	}

	public void setCOMP_ZONE_NO(String cOMP_ZONE_NO)
	{
		COMP_ZONE_NO = cOMP_ZONE_NO;
	}

	public String getCOMP_PHONE()
	{
		return COMP_PHONE;
	}

	public void setCOMP_PHONE(String cOMP_PHONE)
	{
		COMP_PHONE = cOMP_PHONE;
	}

	public String getCOMP_CHAR()
	{
		return COMP_CHAR;
	}

	public void setCOMP_CHAR(String cOMP_CHAR)
	{
		COMP_CHAR = cOMP_CHAR;
	}

	public String getTRADE_KIND()
	{
		return TRADE_KIND;
	}

	public void setTRADE_KIND(String tRADE_KIND)
	{
		TRADE_KIND = tRADE_KIND;
	}

	public String getTECH_GRADE()
	{
		return TECH_GRADE;
	}

	public void setTECH_GRADE(String tECH_GRADE)
	{
		TECH_GRADE = tECH_GRADE;
	}

	public String getTECH_POSI()
	{
		return TECH_POSI;
	}

	public void setTECH_POSI(String tECH_POSI)
	{
		TECH_POSI = tECH_POSI;
	}

	public String getWORK_YEAR()
	{
		return WORK_YEAR;
	}

	public void setWORK_YEAR(String wORK_YEAR)
	{
		WORK_YEAR = wORK_YEAR;
	}

	public String getREMAIN_QUES()
	{
		return REMAIN_QUES;
	}

	public void setREMAIN_QUES(String rEMAIN_QUES)
	{
		REMAIN_QUES = rEMAIN_QUES;
	}

	public String getREMAIN_ANS()
	{
		return REMAIN_ANS;
	}

	public void setREMAIN_ANS(String rEMAIN_ANS)
	{
		REMAIN_ANS = rEMAIN_ANS;
	}

	public String getGET_CARD_MODE()
	{
		return GET_CARD_MODE;
	}

	public void setGET_CARD_MODE(String gET_CARD_MODE)
	{
		GET_CARD_MODE = gET_CARD_MODE;
	}

	public String getPOST_ADDR()
	{
		return POST_ADDR;
	}

	public void setPOST_ADDR(String pOST_ADDR)
	{
		POST_ADDR = pOST_ADDR;
	}

	public String getLM_NAME()
	{
		return LM_NAME;
	}

	public void setLM_NAME(String lM_NAME)
	{
		LM_NAME = lM_NAME;
	}

	public String getLM_SEX()
	{
		return LM_SEX;
	}

	public void setLM_SEX(String lM_SEX)
	{
		LM_SEX = lM_SEX;
	}

	public String getLM_COMP()
	{
		return LM_COMP;
	}

	public void setLM_COMP(String lM_COMP)
	{
		LM_COMP = lM_COMP;
	}

	public String getLM_COMP_ZONENO()
	{
		return LM_COMP_ZONENO;
	}

	public void setLM_COMP_ZONENO(String lM_COMP_ZONENO)
	{
		LM_COMP_ZONENO = lM_COMP_ZONENO;
	}

	public String getLM_COMP_PHONE()
	{
		return LM_COMP_PHONE;
	}

	public void setLM_COMP_PHONE(String lM_COMP_PHONE)
	{
		LM_COMP_PHONE = lM_COMP_PHONE;
	}

	public String getLM_PRE_ADDR()
	{
		return LM_PRE_ADDR;
	}

	public void setLM_PRE_ADDR(String lM_PRE_ADDR)
	{
		LM_PRE_ADDR = lM_PRE_ADDR;
	}

	public String getLM_PRE_ZONENO()
	{
		return LM_PRE_ZONENO;
	}

	public void setLM_PRE_ZONENO(String lM_PRE_ZONENO)
	{
		LM_PRE_ZONENO = lM_PRE_ZONENO;
	}

	public String getLM_PRE_PHONE()
	{
		return LM_PRE_PHONE;
	}

	public void setLM_PRE_PHONE(String lM_PRE_PHONE)
	{
		LM_PRE_PHONE = lM_PRE_PHONE;
	}

	public String getLM_MOBILE()
	{
		return LM_MOBILE;
	}

	public void setLM_MOBILE(String lM_MOBILE)
	{
		LM_MOBILE = lM_MOBILE;
	}

	public String getRELATION_BET()
	{
		return RELATION_BET;
	}

	public void setRELATION_BET(String rELATION_BET)
	{
		RELATION_BET = rELATION_BET;
	}

	public String getCARD_SINGLE_CODE()
	{
		return CARD_SINGLE_CODE;
	}

	public void setCARD_SINGLE_CODE(String cARD_SINGLE_CODE)
	{
		CARD_SINGLE_CODE = cARD_SINGLE_CODE;
	}

	public String getAPP_SOURCE()
	{
		return APP_SOURCE;
	}

	public void setAPP_SOURCE(String aPP_SOURCE)
	{
		APP_SOURCE = aPP_SOURCE;
	}

	public String getEXAM_NODE()
	{
		return EXAM_NODE;
	}

	public void setEXAM_NODE(String eXAM_NODE)
	{
		EXAM_NODE = eXAM_NODE;
	}

	public String getEXAM_NODE_NO()
	{
		return EXAM_NODE_NO;
	}

	public void setEXAM_NODE_NO(String eXAM_NODE_NO)
	{
		EXAM_NODE_NO = eXAM_NODE_NO;
	}

	public String getCUST_MANAGER()
	{
		return CUST_MANAGER;
	}

	public void setCUST_MANAGER(String cUST_MANAGER)
	{
		CUST_MANAGER = cUST_MANAGER;
	}

	public String getSALES_CODE()
	{
		return SALES_CODE;
	}

	public void setSALES_CODE(String sALES_CODE)
	{
		SALES_CODE = sALES_CODE;
	}

	public String getREPAY_CARD()
	{
		return REPAY_CARD;
	}

	public void setREPAY_CARD(String rEPAY_CARD)
	{
		REPAY_CARD = rEPAY_CARD;
	}

	public String getREPAY_MARK()
	{
		return REPAY_MARK;
	}

	public void setREPAY_MARK(String rEPAY_MARK)
	{
		REPAY_MARK = rEPAY_MARK;
	}

	public String getCURRENCY()
	{
		return CURRENCY;
	}

	public void setCURRENCY(String cURRENCY)
	{
		CURRENCY = cURRENCY;
	}

	public String getSTART_DATE()
	{
		return START_DATE;
	}

	public void setSTART_DATE(String sTART_DATE)
	{
		START_DATE = sTART_DATE;
	}

	public String getEXPIRE_DATE()
	{
		return EXPIRE_DATE;
	}

	public void setEXPIRE_DATE(String eXPIRE_DATE)
	{
		EXPIRE_DATE = eXPIRE_DATE;
	}

	public String getINPUT_OPERATOR()
	{
		return INPUT_OPERATOR;
	}

	public void setINPUT_OPERATOR(String iNPUT_OPERATOR)
	{
		INPUT_OPERATOR = iNPUT_OPERATOR;
	}

	public String getINPUT_DATE()
	{
		return INPUT_DATE;
	}

	public void setINPUT_DATE(String iNPUT_DATE)
	{
		INPUT_DATE = iNPUT_DATE;
	}

	public String getPREP2()
	{
		return PREP2;
	}

	public void setPREP2(String pREP2)
	{
		PREP2 = pREP2;
	}

	public String getLAYOUT_FLAG()
	{
		return LAYOUT_FLAG;
	}

	public void setLAYOUT_FLAG(String lAYOUT_FLAG)
	{
		LAYOUT_FLAG = lAYOUT_FLAG;
	}

	public String getMEMBER_ID()
	{
		return MEMBER_ID;
	}

	public void setMEMBER_ID(String mEMBER_ID)
	{
		MEMBER_ID = mEMBER_ID;
	}

	public String getPRE2()
	{
		return PRE2;
	}

	public void setPRE2(String pRE2)
	{
		PRE2 = pRE2;
	}

	public String getPRE4()
	{
		return PRE4;
	}

	public void setPRE4(String pRE4)
	{
		PRE4 = pRE4;
	}

	public String getORGA()
	{
		return ORGA;
	}

	public void setORGA(String oRGA)
	{
		ORGA = oRGA;
	}

	public String getREF_INFO()
	{
		return REF_INFO;
	}

	public void setREF_INFO(String rEF_INFO)
	{
		REF_INFO = rEF_INFO;
	}

	public String getCAREER()
	{
		return CAREER;
	}

	public void setCAREER(String cAREER)
	{
		CAREER = cAREER;
	}

	public String getABCCARD()
	{
		return ABCCARD;
	}

	public void setABCCARD(String aBCCARD)
	{
		ABCCARD = aBCCARD;
	}

	public String getHUKOU_RELNAME()
	{
		return HUKOU_RELNAME;
	}

	public void setHUKOU_RELNAME(String hUKOU_RELNAME)
	{
		HUKOU_RELNAME = hUKOU_RELNAME;
	}

	public String getHUKOU_RELSEX()
	{
		return HUKOU_RELSEX;
	}

	public void setHUKOU_RELSEX(String hUKOU_RELSEX)
	{
		HUKOU_RELSEX = hUKOU_RELSEX;
	}

	public String getHUKOU_RELATION()
	{
		return HUKOU_RELATION;
	}

	public void setHUKOU_RELATION(String hUKOU_RELATION)
	{
		HUKOU_RELATION = hUKOU_RELATION;
	}

	public String getHUKOU_RELZONENO()
	{
		return HUKOU_RELZONENO;
	}

	public void setHUKOU_RELZONENO(String hUKOU_RELZONENO)
	{
		HUKOU_RELZONENO = hUKOU_RELZONENO;
	}

	public String getHUKOU_RELPHONE()
	{
		return HUKOU_RELPHONE;
	}

	public void setHUKOU_RELPHONE(String hUKOU_RELPHONE)
	{
		HUKOU_RELPHONE = hUKOU_RELPHONE;
	}

	public String getHUKOU_RELMOBILE()
	{
		return HUKOU_RELMOBILE;
	}

	public void setHUKOU_RELMOBILE(String hUKOU_RELMOBILE)
	{
		HUKOU_RELMOBILE = hUKOU_RELMOBILE;
	}

	public String getMANAGER_MOBILE()
	{
		return MANAGER_MOBILE;
	}

	public void setMANAGER_MOBILE(String mANAGER_MOBILE)
	{
		MANAGER_MOBILE = mANAGER_MOBILE;
	}

	public String getAgentPerson()
	{
		return agentPerson;
	}

	public void setAgentPerson(String agentPerson)
	{
		this.agentPerson = agentPerson;
	}

	public String getPROCESSER_MOBILE()
	{
		return PROCESSER_MOBILE;
	}

	public void setPROCESSER_MOBILE(String pROCESSER_MOBILE)
	{
		PROCESSER_MOBILE = pROCESSER_MOBILE;
	}

	public String getPROCESSER_ZONENO()
	{
		return PROCESSER_ZONENO;
	}

	public void setPROCESSER_ZONENO(String pROCESSER_ZONENO)
	{
		PROCESSER_ZONENO = pROCESSER_ZONENO;
	}

	public String getPROCESSER_PHONE()
	{
		return PROCESSER_PHONE;
	}

	public void setPROCESSER_PHONE(String pROCESSER_PHONE)
	{
		PROCESSER_PHONE = pROCESSER_PHONE;
	}

	public String getCHANNEL_ID()
	{
		return CHANNEL_ID;
	}

	public void setCHANNEL_ID(String cHANNEL_ID)
	{
		CHANNEL_ID = cHANNEL_ID;
	}

	public String getACT_ID()
	{
		return ACT_ID;
	}

	public void setACT_ID(String aCT_ID)
	{
		ACT_ID = aCT_ID;
	}

	public String getASSURE_TYPE()
	{
		return ASSURE_TYPE;
	}

	public void setASSURE_TYPE(String aSSURE_TYPE)
	{
		ASSURE_TYPE = aSSURE_TYPE;
	}

	public String getASSURE_LMT()
	{
		return ASSURE_LMT;
	}

	public void setASSURE_LMT(String aSSURE_LMT)
	{
		ASSURE_LMT = aSSURE_LMT;
	}

	public String getBUDGET_PROD()
	{
		return BUDGET_PROD;
	}

	public void setBUDGET_PROD(String bUDGET_PROD)
	{
		BUDGET_PROD = bUDGET_PROD;
	}

	public String getBUDGET_LMT()
	{
		return BUDGET_LMT;
	}

	public void setBUDGET_LMT(String bUDGET_LMT)
	{
		BUDGET_LMT = bUDGET_LMT;
	}

	public String getFILLER()
	{
		return FILLER;
	}

	public void setFILLER(String fILLER)
	{
		FILLER = fILLER;
	}

	public String getAPP_NUM()
	{
		return APP_NUM;
	}

	public void setAPP_NUM(String aPP_NUM)
	{
		APP_NUM = aPP_NUM;
	}

	public String getCARD_NAME()
	{
		return CARD_NAME;
	}

	public void setCARD_NAME(String cARD_NAME)
	{
		CARD_NAME = cARD_NAME;
	}

	public String getBUDGET_ASSURE_TYPE()
	{
		return BUDGET_ASSURE_TYPE;
	}

	public void setBUDGET_ASSURE_TYPE(String bUDGET_ASSURE_TYPE)
	{
		BUDGET_ASSURE_TYPE = bUDGET_ASSURE_TYPE;
	}

	public boolean isISSTUDENT()
	{
		return ISSTUDENT;
	}

	public void setISSTUDENT(boolean iSSTUDENT)
	{
		ISSTUDENT = iSSTUDENT;
	}
	
}
