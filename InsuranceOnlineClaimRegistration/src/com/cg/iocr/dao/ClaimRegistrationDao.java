package com.cg.iocr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.iocr.dto.Accounts;
import com.cg.iocr.dto.Claim;
import com.cg.iocr.dto.ClaimDetails;
import com.cg.iocr.dto.ClaimQuestions;
import com.cg.iocr.dto.Policy;
import com.cg.iocr.dto.UserRole;
import com.cg.iocr.exception.ClaimRegException;
import com.cg.iocr.query.QuerryMapper;
import com.cg.iocr.utility.JdbcUtility;
import com.lambdaworks.crypto.SCryptUtil;

public class ClaimRegistrationDao implements IClaimRegistrationDao {

	PreparedStatement statement = null;
	ResultSet resultSet = null;

	static Logger logger = Logger.getLogger(ClaimRegistrationDao.class);

	/**
	 * METHOD NAME: validateUser RETURN TYPE: Integer,returns user count
	 * ARGUMENT:String,user name DESCRIPTION: It takes user name,checks of number of
	 * user exist in data base with same username AUTHOR:CAPGEMINI DATE:25-09-2019
	 */

	@Override
	public int validateUser(String userName) throws ClaimRegException {
		logger.info("Validate a user name");
		Connection connection = null;
		connection = JdbcUtility.getConnection();
		logger.info("connection established");
		int userCount = 0;

		try {
			statement = connection.prepareStatement(QuerryMapper.GET_USERNAME);
			statement.setString(1, userName.toLowerCase());
			resultSet = statement.executeQuery();
			resultSet.next();
			userCount = resultSet.getInt(1);
			logger.debug("data from data base is fetched");

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new ClaimRegException("Problem in fetching users data from table");
		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection validateUser");
				connection.close();
				logger.debug("after closing connection validateUser");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}
		
		logger.debug("returning user count");
		return userCount;
	}

	/**
	 * METHOD NAME: validatePasswordInDatabase 
	 * RETURN TYPE: Integer,returns 0 or 1
	 * ARGUMENT:String (user name) 
	 * DESCRIPTION: It takes user name,validate password in data base.Returns 1 if validated. 
	 * AUTHOR:CAPGEMINI 
	 * DATE:25-09-2019
	 */
	@Override
	public int validatePasswordInDatabase(String userName, String password) throws ClaimRegException {
		logger.info("validate password in data base.Returns 1 if validated. ");
		Connection connection = null;
		connection = JdbcUtility.getConnection();
		logger.info("connection established");
		String getPassword = "";
		try {
			statement = connection.prepareStatement(QuerryMapper.GET_PASSWORD);
			statement.setString(1, userName);
			resultSet = statement.executeQuery();
			resultSet.next();
			getPassword = resultSet.getString(1);
			logger.info("password fetched");

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new ClaimRegException("Problem in fetching password data from table");
		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection validatePasswordInDatabase");
				connection.close();
				logger.debug("after closing connection validatePasswordInDatabase");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}

		logger.info("Validating");
		if (SCryptUtil.check(password, getPassword)) {
			return 1;
		} else
			return 0;
		
		
	}

	/**
	 * METHOD NAME: getRoleCode RETURN TYPE: String, returns role code
	 * ARGUMENT:String,user name DESCRIPTION: It takes user name and returns role
	 * code of that user. AUTHOR:CAPGEMINI DATE:25-09-2019
	 */

	@Override
	public String getRoleCode(String userName) throws ClaimRegException {
		logger.info("returns role code");
		Connection connection = null;
		connection = JdbcUtility.getConnection();
		logger.info("connection established");
		String roleCode = "";
		try {
			statement = connection.prepareStatement(QuerryMapper.GET_ROLECODE);
			statement.setString(1, userName);
			resultSet = statement.executeQuery();
			resultSet.next();
			roleCode = resultSet.getString(1);
			logger.debug("data from data base is fetched");

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new ClaimRegException("Problem in fetching rolecode data from table");
		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getRoleCode");
				connection.close();
				logger.debug("after closing connection getRoleCode");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}
		
		logger.info("returning role code");
		return roleCode;
	}

	/**
	 * METHOD NAME: insertNewProfile RETURN TYPE: Integer, returns 0 or 1
	 * ARGUMENT:Object of UserRole DESCRIPTION: It takes new UserRole object and
	 * insert same into database AUTHOR:CAPGEMINI DATE:25-09-2019
	 */
	@Override
	public int insertNewProfile(UserRole userRole) throws ClaimRegException {
		logger.info("insert a new profile into data base");
		Connection connection = null;
		connection = JdbcUtility.getConnection();
		int count = 0;
		String hashPassword;
		hashPassword = SCryptUtil.scrypt(userRole.getPassword(), 2, 1, 1);
		try {
			statement = connection.prepareStatement(QuerryMapper.INSERT_QUERY);
			logger.debug("statement created");
			statement.setString(1, userRole.getUserName());
			statement.setString(2, hashPassword);
			statement.setString(3, userRole.getRoleCode());
			count = statement.executeUpdate();
			connection.commit();
			logger.info("data inserted");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new ClaimRegException("problem while creating statement object");
		} finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection insertNewProfile");
				connection.close();
				logger.debug("after closing connection insertNewProfile");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing connection");
			}
		}
		
		logger.debug("data inserted");
		return count;
	}

	/**
	 * METHOD NAME: getAllPolicyBasedOnAgent RETURN TYPE: List of Policy object
	 * ARGUMENT:String, agent user name DESCRIPTION: It takes agent user
	 * name,returns all policy associated with that agent. AUTHOR:CAPGEMINI
	 * DATE:25-09-2019
	 */

	@Override
	public List<Policy> getAllPolicyBasedOnAgent(String agentUserName) throws ClaimRegException {
		logger.debug("Get all policy based on agent username");
		Connection connection = null;
		connection = JdbcUtility.getConnection();

		List<Policy> policies = new ArrayList<Policy>();

		try {
			statement = connection.prepareStatement(QuerryMapper.GET_AGENT_DETAILS);
			logger.debug("statement created");
			statement.setString(1, agentUserName);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				long policyNumber = resultSet.getLong("POLICY_NUMBER");
				double policyPremium = resultSet.getDouble("POLICY_PREMIUM");
				long accountNumber = resultSet.getLong("ACCOUNT_NUMBER");

				int claimCount = getNoOfClaims(policyNumber);

				Policy policy = new Policy(policyNumber, policyPremium, accountNumber, claimCount);

				policies.add(policy);
			}

			connection.commit();
			logger.info("data inserted");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new ClaimRegException("Problem in fetching from data base in get all policy by agent" + e);
		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getAllPolicyBasedOnAgent");
				connection.close();
				logger.debug("after closing connection getAllPolicyBasedOnAgent");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}
		
		logger.debug("data from data base is fetched");
		logger.debug("returning all policy based on agent username");
		return policies;
	}

	/**
	 * METHOD NAME: getPolicyList RETURN TYPE: List of Policy object
	 * ARGUMENT:String, Insured user name DESCRIPTION: It takes agent user
	 * name,returns all policy associated with that insured person. AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */

	@Override
	public List<Policy> getPolicyList(String username) throws ClaimRegException {
		logger.debug("Get all policy based on by username");
		Connection connection = null;
		logger.info("Policy List display");

		List<Policy> policies = new ArrayList<>();

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.SELECT_POLICY_BY_USERNAME_QUERY);
			statement.setString(1, username);

			resultSet = statement.executeQuery();

			logger.info("Policy from database is fetched");

			while (resultSet.next()) {

				long policyNumber = resultSet.getLong("POLICY_NUMBER");

				double policyPremium = resultSet.getDouble("POLICY_PREMIUM");

				long accountNumber = resultSet.getLong("ACCOUNT_NUMBER");

				int claimCount = getNoOfClaims(policyNumber);

				Policy policy = new Policy(policyNumber, policyPremium, accountNumber, claimCount);
				policies.add(policy);

			}

			logger.info("All policy list");

		} catch (SQLException e) {

			throw new ClaimRegException("ERROR with SELECT ALL STATEMENT");

		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getPolicyList");
				connection.close();
				logger.debug("after closing connection getPolicyList");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}
		
		logger.debug("returning all policy based by username");
		return policies;
	}

	/**
	 * METHOD NAME: getNoOfClaims RETURN TYPE: integer ARGUMENT:long DESCRIPTION: It
	 * takes policy number, returns number of claim registered with that policy
	 * number. AUTHOR:CAPGEMINI DATE:26-09-2019
	 */

	public int getNoOfClaims(long policyNo) throws ClaimRegException {
		logger.debug("Get number of claims ");
		PreparedStatement statement2 = null;
		Connection connection2 = null;
		ResultSet resultSet2 = null;
		connection2 = JdbcUtility.getConnection();
		int claimCount = 0;

		try {
			statement2 = connection2.prepareStatement(QuerryMapper.COUNT_NUMBER_OF_CLAIMS);
			statement2.setLong(1, policyNo);

			resultSet2 = statement2.executeQuery();

			logger.info("claims count from database is fetched");

			while (resultSet2.next()) {

				claimCount = resultSet2.getInt("COUNT(CLAIM_NUMBER)");
			}

			logger.info("All policy list");

		} catch (SQLException e) {

			throw new ClaimRegException("ERROR with SELECT count of claims STATEMENT");

		} finally {
			try {
				logger.debug("before closing result set");
				resultSet2.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement2.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getQuestionsBasedOnClaimType");
				connection2.close();
				logger.debug("after closing connection getQuestionsBasedOnClaimType");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}
		
		logger.debug("Returning number of claims ");
		return claimCount;

	}

	/**
	 * METHOD NAME: insertNewClaim RETURN TYPE: long ARGUMENT:Object of Claim
	 * DESCRIPTION: It takes Claim object, insert into data base and returns new
	 * claim number. AUTHOR:CAPGEMINI DATE:26-09-2019
	 */
	@Override
	public long insertNewClaim(Claim claim) throws ClaimRegException {
		logger.debug("Inserting a new claim into data base");
		Connection connection = null;
		connection = JdbcUtility.getConnection();
		long claimNumber = 0;
		try {

			statement = connection.prepareStatement(QuerryMapper.INSERT_INTO_CLAIM_QUERY);
			logger.debug("statement created");
			statement.setLong(1, claim.getClaimNumber());
			statement.setString(2, claim.getClaimReason());
			statement.setString(3, claim.getAccidentLocation());
			statement.setString(4, claim.getAccidentCity());
			statement.setString(5, claim.getAccidentState());
			statement.setInt(6, claim.getAccidentZipCode());
			statement.setString(7, claim.getClaimType());
			statement.setLong(8, claim.getPolicyNumber());
			statement.executeUpdate();
			connection.commit();
			logger.info("data inserted");

			statement = connection.prepareStatement(QuerryMapper.SELECT_CURRENT_CLAIM_NUMBER);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				claimNumber = resultSet.getLong(1);
			}

			claimNumber = claimNumber + claim.getClaimNumber();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new ClaimRegException("problem while creating statement object");
		} finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection insertNewClaim");
				connection.close();
				logger.debug("after closing connection insertNewClaim");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing connection");
			}
		}
		
		logger.debug("A new claim into data base is inserted");
		return claimNumber;
	}

	/**
	 * METHOD NAME: getQuestionsBasedOnClaimType RETURN TYPE: Object of
	 * ClaimQuestions ARGUMENT:String (claim types) DESCRIPTION: It takes claim
	 * type, returns all questions associated with that claim type. AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	@Override
	public List<ClaimQuestions> getQuestionsBasedOnClaimType(String claimType) throws ClaimRegException {
		logger.debug("Fetching questions based on claim type");
		Connection connection = null;
		logger.info("Answering Claim Questions");

		List<ClaimQuestions> questions = new ArrayList<>();
		ClaimQuestions claimQuestion = null;
		int questionId;
		String question;
		String answer1;
		String answer2;
		String answer3;

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.SELECT_QUESTIONS_BY_CLAIM_TYPE);
			statement.setString(1, claimType);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				questionId = resultSet.getInt("QUESTION_ID");
				question = resultSet.getString("QUESTION");
				answer1 = resultSet.getString("ANSWER_1");
				answer2 = resultSet.getString("ANSWER_2");
				answer3 = resultSet.getString("ANSWER_3");
				claimQuestion = new ClaimQuestions(claimType, questionId, question, answer1, answer2, answer3);
				questions.add(claimQuestion);
			}
		} catch (SQLException e) {
			logger.error("ERROR while Answering Claim Questions");
			throw new ClaimRegException("ERROR while Answering Claim Questions");

		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getQuestionsBasedOnClaimType");
				connection.close();
				logger.debug("after closing connection getQuestionsBasedOnClaimType");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}

		logger.debug("Questions based on claim type fetched and returning to caller");
		return questions;
	}

	/**
	 * METHOD NAME: insertClaimDetails RETURN TYPE: Integer (0 or 1) ARGUMENT:Object
	 * of ClaimDetails DESCRIPTION: It takes object of ClaimDetails which contains
	 * details claim question and answer. Object is inserted into database.
	 * AUTHOR:CAPGEMINI DATE:26-09-2019
	 */

	@Override
	public int insertClaimDetails(ClaimDetails claimDetails) throws ClaimRegException {
		logger.debug("Inserting new claim details");
		Connection connection = null;
		logger.info("Inserting Claim Details");
		int result;

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.INSERT_CLAIM_DETAILS);
			statement.setLong(1, claimDetails.getClaimNumber());
			statement.setInt(2, claimDetails.getQuestionId());
			statement.setString(3, claimDetails.getAnswer());
			result = statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			throw new ClaimRegException("Error while inserting claim details");
		} finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection insertClaimDetails");
				connection.close();
				logger.debug("after closing connection insertClaimDetails");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing connection");
			}
		}

		logger.debug("New claim details is inserted");
		return result;
	}

	/**
	 * METHOD NAME: getClaim RETURN TYPE: Claim Object ARGUMENT:Long (claim number)
	 * DESCRIPTION: It takes object claim number, and returns claim object
	 * associated with that claim number. AUTHOR:CAPGEMINI DATE:27-09-2019
	 */

	@Override
	public Claim getClaim(long claimNumber) throws ClaimRegException {
		logger.info("Fetching a claim based on claim number");
		Connection connection = null;
		Claim claim = null;

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.SELECT_A_CLAIM_ON_CLAIM_NUMBER);
			logger.debug("statement created");
			statement.setLong(1, claimNumber);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				claimNumber = resultSet.getLong("CLAIM_NUMBER");
				String claimReason = resultSet.getString("CLAIM_REASON");
				String accidentLocation = resultSet.getString("ACCIDENT_LOCATION_STREET");
				String accidentCity = resultSet.getString("ACCIDENT_CITY");
				String accidentState = resultSet.getString("ACCIDENT_STATE");
				int accidentZipCode = resultSet.getInt("ACCIDENT_ZIP");
				String claimType = resultSet.getString("CLAIM_TYPE");
				long policyNumber = resultSet.getLong("POLICY_NUMBER");
				claim = new Claim(claimNumber, claimReason, accidentLocation, accidentCity, accidentState,
						accidentZipCode, claimType, policyNumber);

			}
			connection.commit();
			logger.info("data inserted");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new ClaimRegException("problem while creating statement object");
		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getClaim");
				connection.close();
				logger.debug("after closing connection getClaim");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}
		
		logger.info("A claim based on claim numbercis fetched and returned to caller");
		return claim;
	}

	/**
	 * METHOD NAME: getAllClaimsOnPolicyNumber RETURN TYPE: List of Claim
	 * ARGUMENT:Long (policy number) DESCRIPTION: It takes object policy number, and
	 * returns all claims object associated with that policy number.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */

	@Override
	public List<Claim> getAllClaimsOnPolicyNumber(long policyNumber) throws ClaimRegException {
		logger.info("Fetching all claims based on policy number");
		Connection connection = null;
		List<Claim> claims = new ArrayList<Claim>();

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.SELECT_ALL_CLAIMS_ON_POLICY_NUMBER);
			logger.debug("statement created");
			statement.setLong(1, policyNumber);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long claimNumber = resultSet.getLong("CLAIM_NUMBER");
				String claimType = resultSet.getString("CLAIM_TYPE");
				policyNumber = resultSet.getLong("POLICY_NUMBER");

				Claim claim = new Claim(claimNumber, claimType, policyNumber);
				claims.add(claim);

			}
			connection.commit();
			logger.info("data inserted");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new ClaimRegException("problem while selecting all claims on policy number statement object");
		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getAllClaimsOnPolicyNumber");
				connection.close();
				logger.debug("after closing connection getAllClaimsOnPolicyNumber");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}
		
		logger.info("returning all claims based on policy number");
		return claims;
	}

	/**
	 * METHOD NAME: getAllPolicyList RETURN TYPE: List of Policy ARGUMENT:Void
	 * DESCRIPTION: Returns all policy stored in data base. AUTHOR:CAPGEMINI
	 * DATE:27-09-2019
	 */
	@Override
	public List<Policy> getAllPolicyList() throws ClaimRegException {
		
		logger.info("Fetching all policy");
		Connection connection = null;
		logger.info("Policy List display");

		List<Policy> policies = new ArrayList<>();

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.SELECT_ALL_POLICIES);

			resultSet = statement.executeQuery();

			logger.info("Policy from database is fetched");

			while (resultSet.next()) {

				long policyNumber = resultSet.getLong("POLICY_NUMBER");

				double policyPremium = resultSet.getDouble("POLICY_PREMIUM");

				long accountNumber = resultSet.getLong("ACCOUNT_NUMBER");

				int claimCount = getNoOfClaims(policyNumber);

				Policy policy = new Policy(policyNumber, policyPremium, accountNumber, claimCount);
				policies.add(policy);

			}

			logger.info("All policy list");

		} catch (SQLException e) {

			throw new ClaimRegException("ERROR with SELECT ALL STATEMENT");

		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getAllPolicyList");
				connection.close();
				logger.debug("after closing connection getAllPolicyList");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}
		
		logger.info("Returning all policy");
		return policies;

	}

	/**
	 * METHOD NAME:getClaimDetails RETURN TYPE: List of ClaimDetails ARGUMENT: Long
	 * (claim number) DESCRIPTION: It takes claim number, returns claim details
	 * associated with that claim number. AUTHOR:CAPGEMINI DATE:27-09-2019
	 */

	@Override
	public List<ClaimDetails> getClaimDetails(long claimNumber) throws ClaimRegException {
		logger.info("Fetching all claim getails based on claim number");
		Connection connection = null;
		logger.info("Get all claim details");

		List<ClaimDetails> claimDetails = new ArrayList<ClaimDetails>();

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.SELECT_CLAIM_DETAILS_ON_CLAIM_NUMBER);
			statement.setLong(1, claimNumber);
			resultSet = statement.executeQuery();

			logger.info("Question from database is fetched");

			while (resultSet.next()) {

				String question = resultSet.getString(1);

				String answer = resultSet.getString(2);

				ClaimDetails claimDetail = new ClaimDetails(answer, question);
				claimDetails.add(claimDetail);

			}

			logger.info("All policy list");

		} catch (SQLException e) {

			throw new ClaimRegException("ERROR with SELECT ALL STATEMENT");

		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getClaimDetails");
				connection.close();
				logger.debug("after closing connection getClaimDetails");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}

		logger.info("Returning all claim getails based on claim number");
		return claimDetails;
	}

	/**
	 * METHOD NAME:getName RETURN TYPE: String (insured name) ARGUMENT: String (user
	 * name of insured person) DESCRIPTION: It takes insured user name, returns
	 * insured person name. AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public String getName(String userName) throws ClaimRegException {
		logger.info("Fetching Insured name based on username");
		Connection connection = null;
		logger.info("get Name by username");

		String name = null;

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.SELECT_GET_INSURED_NAME_BY_USERNAME);
			statement.setString(1, userName);
			resultSet = statement.executeQuery();

			logger.info("Name from database is fetched");

			while (resultSet.next()) {

				name = resultSet.getString(1);
			}

			logger.info("name from data base fetched");

		} catch (SQLException e) {

			throw new ClaimRegException("ERROR with select name by username sql");

		} finally {
			try {
				logger.debug("before closing result set");
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing result set");
			}

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing statement");
			}
			try {
				logger.debug("before closing connection getName");
				connection.close();
				logger.debug("after closing connection getName");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problem while closing connection");
			}
		}

		if (name == null) {
			name = userName.toUpperCase();
		}
		
		logger.info("Returning Insured name based on username");
		return name;
	}

	/**
	 * METHOD NAME:getAllUser RETURN TYPE: List of UserRole Object ARGUMENT: String
	 * (role code) DESCRIPTION: It returns all user data(user name, role code).
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public List<UserRole> getAllUser(String adminRoleCode) throws ClaimRegException {
		logger.info("Fetching all user on admin role code");
		Connection connection = null;
		logger.info("get all user");
		List<UserRole> allUsers = new ArrayList<UserRole>();

		if (adminRoleCode.equals("Admin")) {

			connection = JdbcUtility.getConnection();

			try {
				statement = connection.prepareStatement(QuerryMapper.SELECT_ALL_USER_DETAILS);

				resultSet = statement.executeQuery();

				logger.info("All User from database is fetched");

				while (resultSet.next()) {

					String userName = resultSet.getString(1);
					String roleCode = resultSet.getString(2);

					UserRole userRole = new UserRole(userName, roleCode);

					allUsers.add(userRole);

				}

				logger.info("users from data base fetched");

			} catch (SQLException e) {

				throw new ClaimRegException("ERROR with selecting all user from sql data base");

			} finally {
				try {
					logger.debug("before closing result set");
					resultSet.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
					throw new ClaimRegException("problem while closing result set");
				}

				try {
					logger.debug("before closing statement");
					statement.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
					throw new ClaimRegException("problem while closing statement");
				}
				try {
					logger.debug("before closing connection getAllUser");
					connection.close();
					logger.debug("after closing connection getAllUser");
				} catch (SQLException e) {
					logger.error(e.getMessage());
					throw new ClaimRegException("problem while closing connection");
				}
			}

		} else {
			allUsers = null;

		}
		
		logger.info("Returning all user on admin role code");
		return allUsers;
	}

	/**
	 * METHOD NAME:deleteAUser RETURN TYPE: integer 0 or 1 ARGUMENT: String (user
	 * name to delete) DESCRIPTION: It deletes user which is passed to it.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public int deleteAUser(String userNameToDelete) throws ClaimRegException {
		logger.info("Deleting a user from data base");
		Connection connection = null;
		logger.info("Deleting a Profile");
		int result;

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.DELETE_A_USER);
			statement.setString(1, userNameToDelete);
			result = statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			throw new ClaimRegException("Error while deleting a profile from data base");
		} finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection deleteAUser");
				connection.close();
				logger.debug("after closing connection deleteAUser");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing connection");
			}
		}
		
		logger.info("A user from data base is deleted");
		return result;
	}

	/**
	 * METHOD NAME:getAllAccounts RETURN TYPE: List of Accounts Object ARGUMENT:
	 * String (role code) DESCRIPTION: It returns all accounts details.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public List<Accounts> getAllAccounts(String adminRoleCode) throws ClaimRegException {
		logger.info("Retreiving  all accounts from data base");
		Connection connection = null;
		logger.info("get all accounts");

		List<Accounts> allAccounts = new ArrayList<Accounts>();

		if (adminRoleCode.equals("Admin")) {

			connection = JdbcUtility.getConnection();

			try {
				statement = connection.prepareStatement(QuerryMapper.SELECT_ALL_ACCOUNT_DETAILS);

				resultSet = statement.executeQuery();

				logger.info("All User from database is fetched");

				while (resultSet.next()) {

					long accountNumber = resultSet.getLong(1);
					String insuredName = resultSet.getString(2);
					String insuredStreet = resultSet.getString(3);
					String insuredCity = resultSet.getString(4);
					String insuredState = resultSet.getString(5);
					String insuredZipCode = resultSet.getString(6);
					String bussinessSegment = resultSet.getString(7);
					String userName = resultSet.getString(8);
					String agentUserName = resultSet.getString(9);

					Accounts accounts = new Accounts(accountNumber, insuredName, insuredStreet, insuredCity,
							insuredState, insuredZipCode, bussinessSegment, userName, agentUserName);

					allAccounts.add(accounts);
					

				}

				logger.info("users from data base fetched");

			} catch (SQLException e) {

				throw new ClaimRegException("ERROR with selecting all user from sql data base");

			} finally {
				try {
					logger.debug("before closing result set");
					resultSet.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
					throw new ClaimRegException("problem while closing result set");
				}

				try {
					logger.debug("before closing statement");
					statement.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
					throw new ClaimRegException("problem while closing statement");
				}
				try {
					logger.debug("before closing connection getAllAccounts");
					connection.close();
					logger.debug("after closing connection getAllAccounts");
				} catch (SQLException e) {
					logger.error(e.getMessage());
					throw new ClaimRegException("problem while closing connection");
				}
			}

		} else {
			allAccounts = null;

		}
		
		logger.info("Returning  all accounts from data base");
		return allAccounts;
	}

	/**
	 * METHOD NAME:deleteAAccount RETURN TYPE: Integer 0 or 1 ARGUMENT: long
	 * (account number) DESCRIPTION: It deletes account having account number passed
	 * to it. AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public int deleteAAccount(long accountNumber) throws ClaimRegException {
		logger.info("Deleting a account from data base");
		Connection connection = null;
		logger.info("Deleting a Profile");
		int result;

		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.DELETE_A_ACCOUNT);
			statement.setLong(1, accountNumber);
			result = statement.executeUpdate();
			connection.commit();
			logger.debug("data from data base is fetched");
		} catch (SQLException e) {
			throw new ClaimRegException("Error while deleting a account from data base");
		} finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection deleteAAccount");
				connection.close();
				logger.debug("after closing connection deleteAAccount");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing connection");
			}
		}
		
		logger.info("Deleting a account from data base:Deleted");
		return result;

	}

	/**
	 * METHOD NAME:deleteAPolicy RETURN TYPE: Integer 0 or 1 ARGUMENT: long (policy
	 * number) DESCRIPTION: It deletes policy having policy number passed to it.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public int deleteAPolicy(long policyNumber) throws ClaimRegException {
		logger.info("Deleting a  policy from data base");
		Connection connection = null;
		logger.info("Deleting a Policy");
		int result;
		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.DELETE_A_POLICY);
			statement.setLong(1, policyNumber);
			result = statement.executeUpdate();
			connection.commit();
			logger.debug("data from data base is fetched");
		} catch (SQLException e) {
			throw new ClaimRegException("Error while deleting a policy from data base");
		} finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection deleteAPolicy");
				connection.close();
				logger.debug("after closing connection deleteAPolicy");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing connection");
			}
		}

		logger.info("A policy from data base is deleted");
		return result;

	}

	/**
	 * METHOD NAME:deleteAClaim RETURN TYPE: Integer 0 or 1 ARGUMENT: long (claim
	 * number) DESCRIPTION: It deletes claim having claim number passed to it.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public int deleteAClaim(long claimNumber) throws ClaimRegException {
		logger.info("Deleting a  claim from data base");
		Connection connection = null;
		logger.info("Deleting a Policy");
		int result;
		connection = JdbcUtility.getConnection();

		try {
			statement = connection.prepareStatement(QuerryMapper.DELETE_A_CLAIM);
			statement.setLong(1, claimNumber);
			result = statement.executeUpdate();
			connection.commit();
			logger.debug("data from data base is fetched");
		} catch (SQLException e) {
			throw new ClaimRegException("Error while deleting a claim from data base");
		} finally {

			try {
				logger.debug("before closing statement");
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing statement");
			}
			try {
				logger.debug("before closing connection deleteAClaim");
				connection.close();
				logger.debug("after closing connection deleteAClaim");
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new ClaimRegException("problme while closing connection");
			}
		}

		logger.info("A claim from data base is deleted");
		return result;

	}

}
