package com.cg.iocr.dao.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.iocr.dao.ClaimRegistrationDao;
import com.cg.iocr.dto.Accounts;
import com.cg.iocr.dto.Claim;
import com.cg.iocr.dto.ClaimDetails;
import com.cg.iocr.dto.ClaimQuestions;
import com.cg.iocr.dto.Policy;
import com.cg.iocr.dto.UserRole;
import com.cg.iocr.exception.ClaimRegException;

public class ClaimRegistrationDaoTest {
	static ClaimRegistrationDao claimDao = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		claimDao = new ClaimRegistrationDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		claimDao = null;
	}

	@Test
	public void testValidateUserPass() {
		String username = "sdfghjk";
		int count = 0;
		try {
			count = claimDao.validateUser(username);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, count);
	}

	@Test
	public void testValidateUserFail() {
		String username = "sdfghjk";
		int count = 0;
		try {
			count = claimDao.validateUser(username);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(1, count);
	}

	@Test
	public void testValidatePasswordInDatabasePass() {
		String password = "agenta";
		String userName = "akhil";
		int count = 0;
		try {
			count = claimDao.validatePasswordInDatabase(userName, password);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(1, count);
	}

	@Test
	public void testValidatePasswordInDatabaseFail() {
		String password = "agents";
		String userName = "sravani";
		int count = 0;
		try {
			count = claimDao.validatePasswordInDatabase(userName, password);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(0, count);
	}

	@Test
	public void testGetRoleCodePass() {
		String userName = "sravani";
		String roleCode = null;
		try {
			roleCode = claimDao.getRoleCode(userName);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals("Agent", roleCode);
	}

	@Test
	public void testGetRoleCodeFail() {
		String userName = "sravani";
		String roleCode = null;
		try {
			roleCode = claimDao.getRoleCode(userName);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals("agent", roleCode);
	}

	@Test
	public void testInsertNewProfilePass() {
		
		Random rand = new Random();
		int nameRand = rand.nextInt(1000);
		StringBuilder name = new StringBuilder("mayank");
		name = name.append(Integer.toString(nameRand));

		String newName = name.toString();
		
		UserRole userRole = new UserRole(newName, "agents", "Agent");
		int count = 0;
		try {
			count = claimDao.insertNewProfile(userRole);
			assertEquals(1, count);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testInsertNewProfileFail() {
		UserRole userRole = new UserRole("harekrushna", "agent", "Agent");
		int count = 0;
		try {
			count = claimDao.insertNewProfile(userRole);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, count);
	}

	@Test
	public void testGetAllPolicyBasedOnAgentPass() {
		String agentUserName = "akhil";
		List<Policy> policies = new ArrayList<Policy>();
		try {
			policies = claimDao.getAllPolicyBasedOnAgent(agentUserName);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

		assertNotNull(policies);

	}

	@Test
	public void testGetAllPolicyBasedOnAgentFail() {
		String agentUserName = "raghu";
		List<Policy> policies = new ArrayList<Policy>();
		try {
			policies = claimDao.getAllPolicyBasedOnAgent(agentUserName);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

		assertNotNull(policies);

	}

	@Test
	public void testGetPolicyListPass() {
		String userName = "akhil";
		List<Policy> policies = new ArrayList<Policy>();
		try {
			policies = claimDao.getPolicyList(userName);
			assertNotNull(policies);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetPolicyListFail() {
		String userName = "12345";
		List<Policy> policies = new ArrayList<Policy>();
		try {
			policies = claimDao.getPolicyList(userName);
			assertNotNull(policies);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetNoOfClaimsPass() {
		int claimCount = 0;
		try {
			claimCount = claimDao.getNoOfClaims(1000000110l);
			assertEquals(0, claimCount);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testGetNoOfClaimsFail() {
		int claimCount = 0;
		try {
			claimCount = claimDao.getNoOfClaims(1000000110l);
			assertNotEquals(1, claimCount);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testInsertNewClaimFail() {
		long claimNumber = 0;
		Claim claim = new Claim(10000000l, "met with am accident", "location", "city", "State", 123456,
				"Home Insurance", 1000000560l);
		try {
			claimNumber = claimDao.insertNewClaim(claim);
			assertNotEquals(1111000000l, claimNumber);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testInsertNewClaimPass() {
		long claimNumber = 0;
		Claim claim = new Claim(1110000000l, "met with am accident", "location", "city", "State", 123456,
				"Home Insurance", 1000000560l);
		try {
			claimNumber = claimDao.insertNewClaim(claim);
			assertNotEquals(1111000030l, claimNumber);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetQuestionsBasedOnClaimTypePass() {
		List<ClaimQuestions> claimQuestions = new ArrayList<>();
		try {
			claimQuestions = claimDao.getQuestionsBasedOnClaimType("Home Insurance");
			assertNotNull(claimQuestions);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetQuestionsBasedOnClaimTypeFail() {
		List<ClaimQuestions> claimQuestions = new ArrayList<>();
		try {
			claimQuestions = claimDao.getQuestionsBasedOnClaimType("Home");
			assertNotNull(claimQuestions);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testInsertClaimDetailsPass() {
		int result = 0;
		ClaimDetails claimDetails = new ClaimDetails(1111000020l, 1001, "answer");
		try {
			result = claimDao.insertClaimDetails(claimDetails);
			assertEquals(1, result);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testInsertClaimDetailsFail() {
		int result = 0;
		ClaimDetails claimDetails = new ClaimDetails(1111000020l, 1001, "answer");
		try {
			result = claimDao.insertClaimDetails(claimDetails);
			assertNotEquals(0, result);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetClaimPass() {
		Claim claim = new Claim();
		try {
			claim = claimDao.getClaim(11000019l);
			
			assertNull(claim);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	
	
	@Test
	public void testGetClaimFail() {
		Claim claim = new Claim();
		try {
			claim = claimDao.getClaim(110000l);
			assertNull(claim);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	

	

	@Test
	public void testGetAllClaimsOnPolicyNumberPass() {

		List<Claim> claims = new ArrayList<Claim>();
		try {
			claims = claimDao.getAllClaimsOnPolicyNumber(1000000560l);
			assertNotNull(claims);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetAllClaimsOnPolicyNumberFail() {

		List<Claim> claims = new ArrayList<Claim>();
		try {
			claims = claimDao.getAllClaimsOnPolicyNumber(100000050l);
			assertNotNull(claims);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetClaimDetailsPass() {
		List<ClaimDetails> claimDetails = new ArrayList<ClaimDetails>();
		try {
			claimDetails = claimDao.getClaimDetails(1111000000l);
			assertNotNull(claimDetails);

		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testGetClaimDetailsFail() {
		List<ClaimDetails> claimDetails = new ArrayList<ClaimDetails>();
		try {
			claimDetails = claimDao.getClaimDetails(111100000l);
			assertNotNull(claimDetails);

		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testGetNamePass() {
		String name = null;
		try {
			name = claimDao.getName("VISHAL");
			assertEquals("VISHAL", name);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetNameFail() {
		String name = null;
		try {
			name = claimDao.getName("VISHAL");
			assertNotEquals("Vishal", name);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetAllUserPass() {

		List<UserRole> allUsers = new ArrayList<UserRole>();
		try {
			allUsers = claimDao.getAllUser("Admin");
			assertNotNull(allUsers);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetAllUserFail() {

		List<UserRole> allUsers = new ArrayList<UserRole>();
		try {
			allUsers = claimDao.getAllUser("admin");
			assertNull(allUsers);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
	}

	@Test
	public void testGetAllAccountsPass() {

		List<Accounts> allAccounts = new ArrayList<Accounts>();
		try {
			allAccounts = claimDao.getAllAccounts("Admin");
			assertNotNull(allAccounts);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testGetAllAccountsFail() {

		List<Accounts> allAccounts = new ArrayList<Accounts>();
		try {
			allAccounts = claimDao.getAllAccounts("admin");
			assertNull(allAccounts);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

	}
	
	@Test

	public void testDeleteAUserFail() {

		int result = 0;
		try {
			result = claimDao.deleteAUser("sidddhant");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, result);
	}

	@Test
	public void testDeleteAUserPass() {

		UserRole userRole = new UserRole("sukuta", "agentk", "Agent");

		int result = 0;
		try {
			claimDao.insertNewProfile(userRole);
			result = claimDao.deleteAUser("sukuta");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

		assertEquals(1, result);
	}

	@Test
	public void testDeleteAPolicyFail() {

		int result = 0;
		try {
			result = claimDao.deleteAPolicy(99999);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, result);

	}

	@Test
	public void testDeleteAAccountFail() {
		int result = 0;
		try {
			result = claimDao.deleteAAccount(88888);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, result);
	}

	@Test
	public void testDeleteAClaim() {

		int result = 0;
		try {
			result = claimDao.deleteAClaim(77777);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, result);

	}

}
