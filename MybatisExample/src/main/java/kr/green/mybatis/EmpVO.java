package kr.green.mybatis;

/*
EMPLOYEE_ID	int(11)	NO	PRI		
FIRST_NAME	varchar(20)	YES			
LAST_NAME	varchar(25)	NO			
EMAIL	varchar(25)	NO	UNI		
PHONE_NUMBER	varchar(20)	YES			
HIRE_DATE	date	NO			
JOB_ID	varchar(10)	NO	MUL		
SALARY	double(22,0)	YES			
COMMISSION_PCT	double(22,0)	YES			
MANAGER_ID	int(11)	YES	MUL		
DEPARTMENT_ID	int(11)	YES	MUL		
 */
public class EmpVO {
	private int EMPLOYEE_ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String EMAIL;
	private String PHONE_NUMBER;
	private String JOB_ID;

	public int getEMPLOYEE_ID() {
		return EMPLOYEE_ID;
	}

	public void setEMPLOYEE_ID(int eMPLOYEE_ID) {
		EMPLOYEE_ID = eMPLOYEE_ID;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}

	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}

	public String getJOB_ID() {
		return JOB_ID;
	}

	public void setJOB_ID(String jOB_ID) {
		JOB_ID = jOB_ID;
	}

	@Override
	public String toString() {
		return "EmpVO [EMPLOYEE_ID=" + EMPLOYEE_ID + ", FIRST_NAME=" + FIRST_NAME + ", LAST_NAME=" + LAST_NAME
				+ ", EMAIL=" + EMAIL + ", PHONE_NUMBER=" + PHONE_NUMBER + ", JOB_ID=" + JOB_ID + "]";
	}

}
