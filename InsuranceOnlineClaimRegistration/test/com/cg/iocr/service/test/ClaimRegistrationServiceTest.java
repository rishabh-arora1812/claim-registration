package com.cg.iocr.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.iocr.dto.Claim;
import com.cg.iocr.dto.ClaimDetails;
import com.cg.iocr.dto.ClaimQuestions;
import com.cg.iocr.dto.Policy;
import com.cg.iocr.dto.UserRole;
import com.cg.iocr.exception.ClaimRegException;
import com.cg.iocr.service.ClaimRegistrationService;
import com.cg.iocr.service.IClaimRegistrationService;

/**
 * @author learning
 *
 */
public class ClaimRegistrationServiceTest {

	static IClaimRegistrationService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		service = new ClaimRegistrationService();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;

	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateUserNameExistInDatabase(java.lang.String)}.
	 */
	@Test
	public void testValidateUserNameExistInDatabasePass() {
		boolean result = false;
		try {
			result = service.validateUserNameExistInDatabase("mayank");

		} catch (ClaimRegException e) {
			assertEquals(false, result);
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testValidateUserNameExistInDatabasePass2() {
		boolean result = false;
		try {
			result = service.validateUserNameExistInDatabase("harekrushna");

		} catch (ClaimRegException e) {
			assertEquals(false, result);
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testValidateUserNameExistInDatabasePass3() {
		boolean result = false;
		try {
			result = service.validateUserNameExistInDatabase("sravani");

		} catch (ClaimRegException e) {
			assertEquals(false, result);
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testValidateUserNameExistInDatabasePass4() {
		boolean result = false;
		try {
			result = service.validateUserNameExistInDatabase("Nihaal");

		} catch (ClaimRegException e) {
			assertEquals(false, result);
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testValidateUserNameExistInDatabaseFail() {
		boolean result = false;
		try {
			result = service.validateUserNameExistInDatabase("Triple H");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	@Test
	public void testValidateUserNameExistInDatabaseFail2() {
		boolean result = false;
		try {
			result = service.validateUserNameExistInDatabase("Anything");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateNewUserName(java.lang.String)}.
	 */
	@Test
	public void testValidateNewUserNamePass() {
		boolean result = false;
		try {
			result = service.validateNewUserName("mmmayank");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testValidateNewUserNameFail() {
		boolean result = false;
		try {
			result = service.validateNewUserName("Shawn Mendes");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	@Test
	public void testValidateNewUserNameFail2() {
		boolean result = false;
		try {
			result = service.validateNewUserName("newuser%@");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	@Test
	public void testValidateNewUserNameFail3() {
		boolean result = false;
		try {
			result = service.validateNewUserName("newuser.\\");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validatePasswordInDatabase(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testValidatePasswordInDatabasePass() {
		boolean result = false;
		try {
			result = service.validatePasswordInDatabase("godofera", "secret");

		} catch (ClaimRegException e) {
			assertEquals(false, result);
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testValidatePasswordInDatabasePass2() {
		boolean result = false;
		try {
			result = service.validatePasswordInDatabase("harekrushna", "agenth");

		} catch (ClaimRegException e) {
			assertEquals(false, result);
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testValidatePasswordInDatabaseFail() {
		boolean result = false;
		try {
			result = service.validatePasswordInDatabase("godofera", "secrets");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getRoleCode(java.lang.String)}.
	 */
	@Test
	public void testGetRoleCodePass() {
		String result = null;
		try {
			result = service.getRoleCode("shivam");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals("Agent", result);
	}

	@Test
	public void testGetRoleCodeFail() {
		String result = null;
		try {
			result = service.getRoleCode("shivam");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals("Insured", result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#insertNewProfile(com.cg.iocr.dto.UserRole)}.
	 */
	@Test
	public void testInsertNewProfilePass() {

		Random rand = new Random();
		int nameRand = rand.nextInt(1000);
		StringBuilder name = new StringBuilder("mayank");
		name = name.append(Integer.toString(nameRand));

		String newName = name.toString();
		int result = 0;
		UserRole userRole = new UserRole(newName, "agentm", "Agent");

		try {
			result = service.insertNewProfile(userRole);
		} catch (ClaimRegException e) {
			assertEquals(0, result);
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testInsertNewProfilePass2() {

		Random rand = new Random();
		int nameRand = rand.nextInt(1000);
		StringBuilder name = new StringBuilder("mayank");
		name = name.append(Integer.toString(nameRand));

		String newName = name.toString();
		int result = 0;
		UserRole userRole = new UserRole(newName, "agentm", "Agent");

		try {
			result = service.insertNewProfile(userRole);
		} catch (ClaimRegException e) {
			assertEquals(0, result);
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testInsertNewProfileFail() {
		int result = 0;
		UserRole userRole = new UserRole("harekrushna", "agents", "Insured");

		try {
			result = service.insertNewProfile(userRole);
		} catch (ClaimRegException e) {
			assertEquals(0, result);
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getAllPolicyBasedOnAgent(java.lang.String)}.
	 */
	@Test
	public void testGetAllPolicyBasedOnAgentPass() {
		List<Policy> policies = new ArrayList<Policy>();
		try {
			policies = service.getAllPolicyBasedOnAgent("mayank");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(policies);
	}

	@Test
	public void testGetAllPolicyBasedOnAgentFail() {
		List<Policy> policies = new ArrayList<Policy>();
		try {
			policies = service.getAllPolicyBasedOnAgent("mayank");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(policies);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getPolicyList(java.lang.String)}.
	 */
	@Test
	public void testGetPolicyListPass() {
		List<Policy> policies = new ArrayList<Policy>();
		try {
			policies = service.getPolicyList("siddhant");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(policies);
	}

	@Test
	public void testGetPolicyListFail() {
		List<Policy> policies = new ArrayList<Policy>();
		try {
			policies = service.getPolicyList("siddhant");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(policies);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validatePassword(java.lang.String)}.
	 */
	@Test
	public void testValidatePasswordPass() {
		boolean result = false;
		try {
			result = service.validatePassword("123456");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testValidatePasswordPass2() {
		boolean result = false;
		try {
			result = service.validatePassword("hkmmkh");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testValidatePasswordFail() {
		boolean result = false;
		try {
			result = service.validatePassword("Azeb2@df");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateClaimReason(java.lang.String)}.
	 */
	@Test
	public void testValidateClaimReasonPass() {
		boolean result = false;
		try {
			result = service.validateClaimReason("My wife is dead");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testValidateClaimReasonFail() {
		boolean result = false;
		try {
			result = service.validateClaimReason("My wife is dead..Thank God :-)");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateAccidentLocation(java.lang.String)}.
	 */
	@Test
	public void testAccidentLocationPass() {
		boolean result = false;
		try {
			result = service.validateAccidentLocation("Hyderabad");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testAccidentLocationFail() {
		boolean result = false;
		try {
			result = service.validateAccidentLocation("@Goa");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateAccidentCity(java.lang.String)}.
	 */
	@Test
	public void testAccidentCityPass() {
		boolean result = false;
		try {
			result = service.validateAccidentCity("Juhia");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testAccidentCityFail() {
		boolean result = false;
		try {
			result = service.validateAccidentCity("Juhia-MetroPolis");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	@Test
	public void testAccidentCityFail2() {
		boolean result = false;
		try {
			result = service.validateAccidentCity("##newbbsr");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateAccidentState(java.lang.String)}.
	 */
	@Test
	public void testAccidentStatePass() {
		boolean result = false;
		try {
			result = service.validateAccidentState("Telangana");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testAccidentStateFail() {
		boolean result = false;
		try {
			result = service.validateAccidentState("Maharashtra & Rajasthan");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateAccidentZip(int)}.
	 */
	@Test
	public void testAccidentZipPass() {
		boolean result = false;
		try {
			result = service.validateAccidentZip(752109);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testAccidentZipFail() {
		boolean result = false;
		try {
			result = service.validateAccidentZip(750461610);
		} catch (ClaimRegException e) {
			assertNotEquals(true, result);
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testAccidentZipFail2() {
		boolean result = false;
		try {
			result = service.validateAccidentZip(0);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateYear(int)}.
	 */
	@Test
	public void testValidateYearPass() {
		boolean result = false;
		try {
			result = service.validateYear(2010);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testValidateYearFail() {
		boolean result = false;
		try {
			result = service.validateYear(2020);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	@Test
	public void testValidateYearFail2() {
		boolean result = false;
		try {
			result = service.validateYear(1001);
		} catch (ClaimRegException e) {
			assertNotEquals(true, result);
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateMoneyValue(long)}.
	 */
	@Test
	public void testValidateMoneyValuePass() {
		boolean result = false;
		try {
			result = service.validateMoneyValue(500);
		} catch (ClaimRegException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testValidateMoneyValueFail() {
		boolean result = false;
		try {
			result = service.validateMoneyValue(-2500);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
			;
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateDate(java.lang.String)}.
	 */
	@Test
	public void testValidateDatePass() {
		boolean result = false;
		try {
			result = service.validateDate("2010-10-12");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testValidateDateFail() {
		boolean result = false;
		try {
			result = service.validateDate("2020-10-12");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	@Test
	public void testValidateDateFail2() {
		boolean result = false;
		try {
			result = service.validateDate("28-09-2019");
		} catch (ClaimRegException e) {
			assertNotEquals(true, result);
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#validateChassisNumber(java.lang.String)}.
	 */
	@Test
	public void testValidateChassisNumberPass() {
		boolean result = false;
		try {
			result = service.validateChassisNumber("A5010BADF");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testValidateChassisNumberPass2() {
		boolean result = false;
		try {
			result = service.validateChassisNumber("12345678");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(true, result);
	}

	@Test
	public void testValidateChassisNumberFail() {
		boolean result = false;
		try {
			result = service.validateChassisNumber("A5010#@");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#insertNewClaim(com.cg.iocr.dto.Claim)}.
	 */
	@Test
	public void testInsertNewClaimPass() {
		long result = 0;
		Claim claim = new Claim(1110000000l, "My house is on fire", "Gachibowli", "Hyderabad", "Telangana", 500300,
				"Home Insurance", 1000000470);
		try {
			result = service.insertNewClaim(claim);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(result);
	}

	@Test
	public void testInsertNewClaimFail() {
		long result = 0;
		Claim claim = new Claim(1110000050l, "Robbery in my house", "Sindhi Camp", "Jaipur", "Rajasthan", 510300,
				"Home Insurance", 1000000470);
		try {
			result = service.insertNewClaim(claim);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getQuestionsBasedOnClaimType(java.lang.String)}.
	 */
	@Test
	public void testGetQuestionsBasedOnClaimTypePass() {

		List<ClaimQuestions> result = new ArrayList<>();

		try {
			result = service.getQuestionsBasedOnClaimType("Home Insurance");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(result);

	}

	@Test
	public void testGetQuestionsBasedOnClaimTypePass2() {

		List<ClaimQuestions> result = new ArrayList<>();

		try {
			result = service.getQuestionsBasedOnClaimType("Motor Insurance");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(result);

	}

	@Test
	public void testGetQuestionsBasedOnClaimTypePass3() {

		List<ClaimQuestions> result = new ArrayList<>();

		try {
			result = service.getQuestionsBasedOnClaimType("Health Insurance");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(result);

	}

	@Test
	public void testGetQuestionsBasedOnClaimTypeFail() {

		List<ClaimQuestions> result = new ArrayList<>();

		try {
			result = service.getQuestionsBasedOnClaimType("No Insurance");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(result);

	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#insertClaimDetails(com.cg.iocr.dto.ClaimDetails)}.
	 */
	@Test
	public void testInsertClaimDetailsPass() {
		int result = 0;
		ClaimDetails claimDetails = new ClaimDetails(1110000050l, 101, "Yes");
		try {
			result = service.insertClaimDetails(claimDetails);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getClaim(long)}.
	 * 
	 */
	@Test
	public void testGetClaimPass() {
		Claim claim = null;
		try {
			claim = service.getClaim(1111000055);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNull(claim);
	}

	@Test
	public void testGetClaimFail() {
		Claim claim = null;
		try {
			claim = service.getClaim(100000005);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNull(claim);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getAllClaimsOnPolicyNumber(long)}.
	 */
	@Test
	public void testGetAllClaimsOnPolicyNumberPass() {
		List<Claim> claim = new ArrayList<>();
		try {
			claim = service.getAllClaimsOnPolicyNumber(1000000470);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(false, claim.isEmpty());
	}

	@Test
	public void testGetAllClaimsOnPolicyNumberFail() {
		List<Claim> claim = new ArrayList<>();
		try {
			claim = service.getAllClaimsOnPolicyNumber(1000000361);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(false, claim.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getAllPolicyList()}.
	 */
	@Test
	public void testGetAllPolicyListPass() {
		List<Policy> policy = null;
		try {
			policy = service.getAllPolicyList();
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

		assertEquals(policy.get(0).getAccountNumber(), 1000000000);
	}

	@Test
	public void testGetAllPolicyListFail() {
		List<Policy> policy = null;
		try {
			policy = service.getAllPolicyList();
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(policy);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getClaimDetails(long)}.
	 */
	@Test
	public void testGetClaimDetailsPass() {
		List<ClaimDetails> claim = new ArrayList<>();
		try {
			claim = service.getClaimDetails(1000000470);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, claim.size());
	}

	@Test
	public void testGetClaimDetailsFail() {
		List<ClaimDetails> claim = new ArrayList<>();
		try {
			claim = service.getClaimDetails(1000000361);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(false, claim.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getName(java.lang.String)}.
	 */
	@Test
	public void testGetNamePass() {
		String result = null;
		try {
			result = service.getName("mayank");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result);
	}

	@Test
	public void testGetNameFail() {
		String result = null;
		try {
			result = service.getName("Maya @");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotNull(null, result);
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#getAllUser(java.lang.String)}.
	 */
	@Test
	public void testGetAllUserPass() {
		List<UserRole> result = new ArrayList<>();
		try {
			result = service.getAllUser("Admin");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(true, result.isEmpty());
	}

	@Test
	public void testGetAllUserFail() {
		List<UserRole> result = new ArrayList<>();
		try {
			result = service.getAllUser("Admin");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertNotEquals(0, result.size());
	}

	/**
	 * Test method for
	 * {@link com.cg.iocr.service.ClaimRegistrationService#deleteAUser(java.lang.String)}.
	 */
	@Test

	public void testDeleteAUserFail() {

		int result = 0;
		try {
			result = service.deleteAUser("sidddhant");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, result);
	}

	@Test
	public void testDeleteAUserPass() {

		UserRole userRole = new UserRole("kunarmmm", "agentk", "Agent");

		int result = 0;
		try {
			service.insertNewProfile(userRole);
			result = service.deleteAUser("kunarmmm");
		} catch (ClaimRegException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(1, result);
	}

	@Test
	public void testDeleteAPolicyFail() {

		int result = 0;
		try {
			result = service.deleteAPolicy(1234);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, result);

	}

	@Test
	public void testDeleteAAccountFail() {
		int result = 0;
		try {
			result = service.deleteAAccount(4321);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, result);
	}

	@Test
	public void testDeleteAClaim() {

		int result = 0;
		try {
			result = service.deleteAClaim(12345);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		assertEquals(0, result);

	}

}