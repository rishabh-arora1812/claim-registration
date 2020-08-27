package com.cg.iocr.query;

public interface QuerryMapper {

	public static final String GET_USERNAME = "SELECT COUNT(*) FROM user_role WHERE LOWER(user_name) = ? ";

	public static final String GET_PASSWORD = "SELECT password FROM user_role WHERE LOWER(user_name) = ?";

	public static final String GET_ROLECODE = "SELECT role_code FROM user_role WHERE LOWER(user_name) = ?";

	public static final String INSERT_QUERY = "INSERT INTO USER_ROLE VALUES(?,?,?)";

	public static final String GET_AGENT_DETAILS = "SELECT p.policy_number, p.policy_premium, p.account_number from policy p inner join accounts a on p.account_number = a.account_number where LOWER(a.agent_user_name) =?";

	public static final String SELECT_POLICY_BY_USERNAME_QUERY = "SELECT p.policy_number, p.policy_premium, p.account_number FROM POLICY p inner join accounts a on p.account_number=a.account_number where LOWER(a.INSURED_USER_NAME) = ?";

	public static final String COUNT_NUMBER_OF_CLAIMS = "SELECT COUNT(CLAIM_NUMBER) FROM CLAIM WHERE POLICY_NUMBER=?";

	public static final String CHECK_POLICY_NUMBER = "SELECT POLICY_NUMBER FROM POLICY";

	public static final String INSERT_INTO_CLAIM_QUERY = "INSERT INTO CLAIM VALUES(CLAIM_SEQ.NEXTVAL+?,?,?,?,?,?,?,?)";

	public static final String SELECT_QUESTIONS_BY_CLAIM_TYPE = "SELECT * FROM CLAIM_QUESTIONS WHERE CLAIM_TYPE=?";

	public static final String INSERT_CLAIM_DETAILS = "INSERT INTO CLAIM_DETAILS VALUES(?,?,?)";
	
	public static final String SELECT_CLAIM_NUMBER="SELECT CLAIM_NUMBER FROM CLAIM WHERE=";
	
	public static final String SELECT_CURRENT_CLAIM_NUMBER="SELECT claim_seq.CURRVAL FROM dual";
	
	public static final String SELECT_A_CLAIM_ON_CLAIM_NUMBER = "SELECT * FROM CLAIM WHERE CLAIM_NUMBER=?";
	
	public static final String SELECT_ALL_CLAIMS_ON_POLICY_NUMBER = "SELECT CLAIM_NUMBER,CLAIM_TYPE,POLICY_NUMBER FROM CLAIM WHERE POLICY_NUMBER=?";
	
	public static final String SELECT_ALL_POLICIES="SELECT * FROM POLICY";
	
	public static final String SELECT_CLAIM_DETAILS_ON_CLAIM_NUMBER="SELECT CQ.QUESTION,CD.ANSWER FROM CLAIM_QUESTIONS CQ INNER JOIN CLAIM_DETAILS CD ON CQ.QUESTION_ID =CD.QUESTION_ID WHERE CLAIM_NUMBER=?";
	
	public static final String SELECT_GET_INSURED_NAME_BY_USERNAME="SELECT INSURED_NAME FROM ACCOUNTS WHERE LOWER(INSURED_USER_NAME)=?"; 
	
	public static final String SELECT_ALL_USER_DETAILS="SELECT USER_NAME,ROLE_CODE FROM USER_ROLE ORDER BY ROLE_CODE"; 
	
	public static final String DELETE_A_USER="DELETE FROM USER_ROLE WHERE USER_NAME=?";
	
	public static final String SELECT_ALL_ACCOUNT_DETAILS="SELECT * FROM ACCOUNTS";
	
	public static final String DELETE_A_ACCOUNT="DELETE FROM ACCOUNTS WHERE ACCOUNT_NUMBER=?";
	
	public static final String DELETE_A_POLICY="DELETE FROM POLICY WHERE POLICY_NUMBER=?";
	
	public static final String DELETE_A_CLAIM="DELETE FROM CLAIM WHERE CLAIM_NUMBER=?";
	
}
