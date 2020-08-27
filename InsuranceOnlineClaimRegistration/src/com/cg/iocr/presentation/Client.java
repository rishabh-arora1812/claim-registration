package com.cg.iocr.presentation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.iocr.dto.Accounts;
import com.cg.iocr.dto.Claim;
import com.cg.iocr.dto.ClaimDetails;
import com.cg.iocr.dto.ClaimQuestions;
import com.cg.iocr.dto.Policy;
import com.cg.iocr.dto.UserRole;
import com.cg.iocr.exception.ClaimRegException;
import com.cg.iocr.service.ClaimRegistrationService;
import com.cg.iocr.service.IClaimRegistrationService;

public class Client {

	static Logger logger = Logger.getLogger(Client.class);
	IClaimRegistrationService service = new ClaimRegistrationService();
	static Scanner scanner = null;
	static int clearScreen = 0;

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		PropertyConfigurator.configure("resources/log4j.properties");
		logger.info("In Main function");

		System.out.println("...................Insurance Online Claim Registration Service................\n");
		String checkNextCycle = null;
		int choice = 0;
		boolean choiceFlag = true;
		Scanner scanner = null;
		Client client = new Client();
		boolean yesFlag = false;

		do {
			logger.info("Login Menu");
			System.out.println("\n...................Login Menu................");
			System.out.println("1.Log In");
			System.out.println("2.Exit");

			System.out.println("\n Enter your choice:");

			do {
				scanner = new Scanner(System.in);
				try {

					choice = scanner.nextInt();

					switch (choice) {
					case 1:
						logger.info("Calling runAllTools() in Main menu");
						client.runAllTools();

						choiceFlag = true;
						break;
					case 2:
						logger.info("System exit from main");
						choiceFlag = true;
						System.out.println("\nThank You! System Exiting.....Done");
						System.exit(0);
						break;
					default:
						System.err.println("Input should be in range of 1-2");
						logger.error("Input should be in range of 1-2 Main Menu");
						choiceFlag = false;
						break;
					}

				} catch (InputMismatchException e) {
					System.err.println("Enter Number Only");
					logger.error("Enter Number Only");
					choiceFlag = false;
				}

			} while (!choiceFlag);

			System.out.println("\n Do u want enter Insurance Online Claim Registration Service (Enter: yes/no):");

			do {

				scanner = new Scanner(System.in);

				try {
					checkNextCycle = scanner.next();
					if (checkNextCycle.equalsIgnoreCase("yes") || checkNextCycle.equalsIgnoreCase("no")) {
						yesFlag = true;
					} else {
						throw new ClaimRegException("Enter either yes or no");
					}

				} catch (ClaimRegException e) {
					yesFlag = false;
					System.err.println(e.getMessage());
					logger.error(e.getMessage());
				}

			} while (!yesFlag);

		} while (checkNextCycle.equalsIgnoreCase("yes"));

		scanner.close();
		logger.info("End of Main function");
	}

	@SuppressWarnings("resource")
	public void runAllTools() {

		logger.info("In  runAllTools()");
		Scanner scanner = null;
		boolean userFlag = false;
		String userName = "";
		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Enter Username");
				String userNameTemprary = scanner.next();
				userName = userNameTemprary.toLowerCase();
				userFlag = service.validateUserNameExistInDatabase(userName);
			} catch (ClaimRegException e) {
				System.err.println(e.getMessage());
				logger.error(e.getMessage());
			}

		} while (!userFlag);

		String password = null;
		boolean passFlag = false;
		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Enter Password");
				password = scanner.nextLine();

				passFlag = service.validatePasswordInDatabase(userName, password);
			} catch (ClaimRegException e) {
				System.err.println(e.getMessage());
				logger.error(e.getMessage());
			}

		} while (!passFlag);

		String roleCode = null;
		try {
			roleCode = service.getRoleCode(userName);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

		logger.info("Successfully Logged In");

		for (clearScreen = 0; clearScreen < 50; clearScreen++) {
			System.out.println("\n");
		}

		switch (roleCode) {
		case "Admin":
			logger.info("Calling runAdminTools() from allTool");
			System.out.println("\nHello Admin :" + userName.toUpperCase() + "\n");
			runAdminTools(userName);
			break;
		case "Agent":
			logger.info("Calling runAgentTools() from allTool");
			System.out.println("\nHello Agent :" + userName.toUpperCase());
			runAgentTools(userName);
			break;
		case "Insured":
			logger.info("Calling runInsuredTool() from allTool");
			runInsuredTool(userName);
			break;
		default:
			System.err.println("Error role code");
		}

	}

	public void runAdminTools(String userName) {
		logger.debug("inside admin tool function");
		String checkNextCycle = null;
		int choice = 0;
		long policyNumber = 0l;
		long claimNumber = 0l;
		boolean choiceFlag = true;
		boolean yesFlag = false;
		boolean logoutFlag = false;
		Scanner scanner = null;

		List<Policy> policies = new ArrayList<>();

		do {

			System.out.println("\n \n ...................Admin Tools Menu................");
			System.out.println("1.Create a New Profile");
			System.out.println("2.View All User");
			System.out.println("3.View All Accounts");
			System.out.println("4.View All Policies");
			System.out.println("5.Create a Claim");
			System.out.println("6.View Claims");
			System.out.println("7.Report Generation");
			System.out.println("8.Delete a user/account/policy/claim");
			System.out.println("9.Logout");
			System.out.println("10.Exit System");
			System.out.println("\nEnter your choice:");

			do {

				try {
					scanner = new Scanner(System.in);
					choice = scanner.nextInt();

					switch (choice) {
					case 1:
						logger.info("Calling createNewUserRole() for admin");
						createNewUserRole();
						choiceFlag = true;
						System.out.println("\n");

						break;

					case 2:
						logger.info("Calling displayAllUser(userName) for admin");
						displayAllUser(userName);
						choiceFlag = true;
						System.out.println("\n");

						break;

					case 3:
						logger.info("Calling displayAllAcounts(userName); for admin");
						displayAllAcounts(userName);
						choiceFlag = true;
						System.out.println("\n");

						break;
					case 4:
						logger.info("Calling displayAllPolicies() for admin");
						policies = displayAllPolicies();
						choiceFlag = true;
						System.out.println("\n");

						break;

					case 5:
						logger.info("Calling createClaim() for admin");
						policies = displayAllPolicies();
						if (policies.isEmpty()) {
							System.err.println("There is no existing policies to create claim");
							choiceFlag = true;
							System.out.println("\n");
						} else {
							policyNumber = choosePolicyFromPolicyList(policies);
							createClaim(policyNumber);
							choiceFlag = true;
							System.out.println("\n");

						}

						break;
					case 6:
						logger.info("Calling viewCalim() for admin");
						policies = displayAllPolicies();
						if (policies.isEmpty()) {
							System.err.println("There is no existing policies to view claim");
							logger.info("There is no existing policies to view claim");
							choiceFlag = true;
							System.out.println("\n");
						} else {

							int checkClaim = checkClaim(policies);

							if (checkClaim == 1) {
								policyNumber = choosePolicyFromListForViewClaim(policies);
								List<Claim> claims = displayAllClaim(policyNumber);
								claimNumber = chooseClaimFromList(claims);
								viewClaim(claimNumber);
								choiceFlag = true;
								System.out.println("\n");

							} else {
								choiceFlag = true;
								System.out.println("\n");
								System.out.println("There is no claim to view");
								logger.info("There is no claim to view");
								
								
							}

						}

						break;
					case 7:
						logger.info("Calling generateReport() for admin");
						policies = displayAllPolicies();
						if (policies.isEmpty()) {
							System.err.println("There is no existing policies to generate report of claim");
							logger.info("There is no existing policies to generate report of claim");
							choiceFlag = true;
							System.out.println("\n");
						} else {

							int checkClaim = checkClaim(policies);

							if (checkClaim == 1) {
								policyNumber = choosePolicyFromListForViewClaim(policies);
								for (clearScreen = 0; clearScreen < 5; clearScreen++) {
									System.out.println("\n");
								}
								List<Claim> claims2 = displayAllClaim(policyNumber);
								claimNumber = chooseClaimFromList(claims2);
								generateReport(claimNumber);
								choiceFlag = true;
								System.out.println("\n");
							} else {
								System.out.println("There is no claim to view");
								logger.info("There is no claim to view");
								choiceFlag = true;
								System.out.println("\n");
							}

						}

						break;
					case 8:
						logger.info("Calling deleteGeneral() for admin");
						deleteGeneral(userName);

						choiceFlag = true;
						break;
					case 9:
						logger.info("Logout from admin");
						logoutFlag = true;
						choiceFlag = true;

						break;
					case 10:
						logger.info("System exit from Admin Tool Menu");
						choiceFlag = true;
						System.out.println("\nThank You! System Exiting.....Done");
						System.exit(0);
						break;

					default:
						System.err.println("Input should be in range of 1-6");
						logger.error("Input should be in range of 1-6");
						choiceFlag = false;
						break;
					}

				} catch (InputMismatchException e) {
					System.err.println("Enter Number Only");
					logger.error("Enter Number Only");
					choiceFlag = false;
				}

			} while (!choiceFlag);

			if (logoutFlag) {
				checkNextCycle = "no";
			} else {
				System.out.println("\n Do u want enter Admin Tool (Enter: yes/no):");

				do {

					scanner = new Scanner(System.in);

					try {
						checkNextCycle = scanner.next();
						if (checkNextCycle.equalsIgnoreCase("yes") || checkNextCycle.equalsIgnoreCase("no")) {
							yesFlag = true;
						} else {
							throw new ClaimRegException("Enter either yes or no");
						}

					} catch (ClaimRegException e) {
						yesFlag = false;
						System.err.println(e.getMessage());
						logger.error(e.getMessage());
					}

				} while (!yesFlag);

			}

			if (checkNextCycle.equalsIgnoreCase("no")) {
				System.out.println("Logged Out Successfully");
				logger.info("Logged Out Successfully");
			}

		} while (checkNextCycle.equalsIgnoreCase("yes"));

	}

	public void runAgentTools(String userName) {

		String checkNextCycle = null;
		int choice = 0;
		boolean choiceFlag = true;
		boolean yesFlag = true;
		boolean logoutFlag = false;
		Scanner scanner = null;
		long policyNumber = 0l;
		long claimNumber = 0l;

		do {
			logger.info("Calling displayAllPoliciesOnAgent() for agent");
			List<Policy> policies = displayAllPoliciesOnAgent(userName);

			System.out.println("\n \n ...................Agent Tools Menu................");
			System.out.println("1.Create a new Claim");
			System.out.println("2.View Claims");
			System.out.println("3.Logout");
			System.out.println("4.Exit System");
			System.out.println("\nEnter your choice:");

			do {

				try {

					scanner = new Scanner(System.in);
					choice = scanner.nextInt();

					switch (choice) {
					case 1:
						logger.info("Calling createClaim() for agent");
						if (policies.isEmpty()) {
							System.err.println("There is no existing policies to create claim");
							logger.info("There is no existing policies to create claim");
							choiceFlag = true;
							System.out.println("\n");
						} else {
							policyNumber = choosePolicyFromPolicyList(policies);
							createClaim(policyNumber);
							choiceFlag = true;
							System.out.println("\n");

						}

						break;
					case 2:

						logger.info("Calling viewClaim() for agent");
						if (policies.isEmpty()) {
							System.err.println("There is no existing policies to view claim");
							logger.info("There is no existing policies to view claim");
							choiceFlag = true;
							System.out.println("\n");
						} else {

							int checkClaim = checkClaim(policies);

							if (checkClaim == 1) {
								policyNumber = choosePolicyFromListForViewClaim(policies);
								for (clearScreen = 0; clearScreen < 5; clearScreen++) {
									System.out.println("\n");
								}
								List<Claim> claims = displayAllClaim(policyNumber);
								claimNumber = chooseClaimFromList(claims);
								viewClaim(claimNumber);
								choiceFlag = true;
							} else {
								System.out.println("There is no claim to view");
								choiceFlag = true;
								System.out.println("\n");
							}

						}

						System.out.println("\n");

						break;
					case 3:
						logger.info("Sytem logout from agent tool");
						logoutFlag = true;
						choiceFlag = true;

						break;
					case 4:
						logger.info("System exit from Agent Tool Menu");
						choiceFlag = true;
						System.out.println("\nThank You! System Exiting.....Done");
						System.exit(0);

					default:
						System.err.println("Input should be in range of 1-3");
						logger.error("Input should be in range of 1-3");
						choiceFlag = false;
						break;
					}

				} catch (InputMismatchException e) {
					System.err.println("Enter Number Only");
					logger.error("Enter Number Only");
					choiceFlag = false;
				}

			} while (!choiceFlag);

			if (logoutFlag) {
				checkNextCycle = "no";
			} else {
				System.out.println("\n Do u want enter Agent Tool (Enter: yes/no):");

				do {

					scanner = new Scanner(System.in);

					try {
						checkNextCycle = scanner.next();
						if (checkNextCycle.equalsIgnoreCase("yes") || checkNextCycle.equalsIgnoreCase("no")) {
							yesFlag = true;
						} else {
							throw new ClaimRegException("Enter either yes or no");
						}

					} catch (ClaimRegException e) {
						yesFlag = false;
						System.err.println(e.getMessage());
					}

				} while (!yesFlag);

			}

			if (checkNextCycle.equalsIgnoreCase("no")) {
				System.out.println("Logged Out Successfully");
				logger.info("Logged Out Successfully");
			}

		} while (checkNextCycle.equalsIgnoreCase("yes"));

	}

	public void runInsuredTool(String userName) {

		String checkNextCycle = null;
		int choice = 0;
		boolean choiceFlag = true;
		boolean yesFlag = true;
		boolean logoutFlag = false;
		Scanner scanner = null;
		long policyNumber = 0l;
		long claimNumber = 0l;

		displayInsuredName(userName);

		do {
			logger.info("Calling displayAllPoliciesOfUserName() for insured");
			List<Policy> policies = displayAllPoliciesOfUserName(userName);
			System.out.println("\n \n ...................Insured Tools Menu................");
			System.out.println("1.Create a new Claim");
			System.out.println("2.View Claims");
			System.out.println("3.Logout");
			System.out.println("4.Exit System");
			System.out.println("\nEnter your choice:");

			do {

				try {
					scanner = new Scanner(System.in);
					choice = scanner.nextInt();

					switch (choice) {
					case 1:
						logger.info("Calling createClaim() for insured");
						if (policies.isEmpty()) {
							System.err.println("There is no existing policies to create claim");
							choiceFlag = true;
							System.out.println("\n");
						} else {
							policyNumber = choosePolicyFromPolicyList(policies);
							createClaim(policyNumber);
							choiceFlag = true;
							System.out.println("\n");

						}

						break;
					case 2:
						logger.info("Calling viewCalim() for insured");

						if (policies.isEmpty()) {
							System.err.println("There is no existing policies to view claim");
							choiceFlag = true;
							System.out.println("\n");
						} else {

							int checkClaim = checkClaim(policies);

							if (checkClaim == 1) {

								policyNumber = choosePolicyFromListForViewClaim(policies);
								for (clearScreen = 0; clearScreen < 5; clearScreen++) {
									System.out.println("\n");
								}
								List<Claim> claims = displayAllClaim(policyNumber);
								claimNumber = chooseClaimFromList(claims);
								viewClaim(claimNumber);
								choiceFlag = true;
								System.out.println("\n");
							} else {
								System.out.println("There is no claim to view");
								choiceFlag = true;
								System.out.println("\n");
							}

						}

						break;
					case 3:
						logger.info("Sytem logout from insuredtool");
						logoutFlag = true;
						choiceFlag = true;

						break;
					case 4:
						logger.info("System exit from Insured Tool Menu");
						choiceFlag = true;
						System.out.println("\nThank You! System Exiting.....Done");
						System.exit(0);

					default:
						System.err.println("Input should be in range of 1-3");
						logger.error("Input should be in range of 1-3");
						choiceFlag = false;
						break;
					}

				} catch (InputMismatchException e) {
					System.err.println("Enter Number Only");
					logger.error("Enter Number Only");
					choiceFlag = false;
				}

			} while (!choiceFlag);

			if (logoutFlag) {
				checkNextCycle = "no";
			} else {
				System.out.println("\n Do u want enter Insured Tool (Enter: yes/no):");

				do {

					scanner = new Scanner(System.in);

					try {
						checkNextCycle = scanner.next();
						if (checkNextCycle.equalsIgnoreCase("yes") || checkNextCycle.equalsIgnoreCase("no")) {
							yesFlag = true;
						} else {
							throw new ClaimRegException("Enter either yes or no");
						}

					} catch (ClaimRegException e) {
						yesFlag = false;
						System.err.println(e.getMessage());
						logger.error(e.getMessage());
					}

				} while (!yesFlag);

			}

			if (checkNextCycle.equalsIgnoreCase("no")) {
				System.out.println("Logged Out Successfully");
				logger.info("Logged Out Successfully");
			}

		} while (checkNextCycle.equalsIgnoreCase("yes"));

	}

	private void displayInsuredName(String userName) {
		
		logger.info("Inside display Insured User name function ");

		String name = null;
		try {
			name = service.getName(userName);
			System.out.println("\nHello Insured: " + name.toUpperCase());
		} catch (ClaimRegException e1) {
			System.err.println(e1.getMessage());
		}
		logger.info("user name displayed");
	}

	private long chooseClaimFromList(List<Claim> claims) {
		int serialNumber = 0;
		long claimNumber = 0l;
		logger.debug("Inside chooseClaimFromList function ");
		boolean serialNoFlag = false;

		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Enter a SERIAL NUMBER of above CLAIM LIST:");

				serialNumber = scanner.nextInt();
				--serialNumber;
				if (serialNumber > claims.size()) {
					throw new ClaimRegException("Kindly enter valid serial number which are listed above");

				} else {
					serialNoFlag = true;
					claimNumber = claims.get(serialNumber).getClaimNumber();

				}

			} catch (InputMismatchException e) {
				System.err.println("Kindly enter Number only");
				serialNoFlag = false;
			} catch (ClaimRegException e) {
				serialNoFlag = false;
				System.err.println(e.getMessage());
			} catch (ArrayIndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");
				logger.error("Kindly enter valid serial number which are listed above");

			} catch (IndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");

			}

		} while (!serialNoFlag);
		
		logger.debug("claim number selected ");

		return claimNumber;

	}

	private long choosePolicyFromPolicyList(List<Policy> policies) {
		logger.debug("Inside choosePolicyFromPolicyList function ");
		int serialNumber = 0;

		boolean serialNoFlag = false;
		long policyNumber = 0l;

		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Enter a SERIAL NUMBER of above POLICY LIST:");
				serialNumber = scanner.nextInt();

				--serialNumber;
				if (serialNumber > policies.size()) {
					throw new ClaimRegException("Kindly enter valid serial number which are listed above");
				} else {
					serialNoFlag = true;
					policyNumber = policies.get(serialNumber).getPolicyNumber();
				}

			} catch (InputMismatchException e) {
				System.err.println("Enter Number only");
				serialNoFlag = false;
			} catch (ClaimRegException e) {
				serialNoFlag = false;
				System.err.println(e.getMessage());
			} catch (ArrayIndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");

			} catch (IndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");

			}

		} while (!serialNoFlag);
		
		
		logger.debug("policy number selected ");
		return policyNumber;

	}

	private long choosePolicyFromListForViewClaim(List<Policy> policies) {
		int serialNumber = 0;
		long policyNumber = 0l;
		
		logger.debug("Inside choosePolicyFromListForViewClaim function ");
		boolean serialNoFlag = false;

		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Enter a SERIAL NUMBER of above POLICY LIST:");
				serialNumber = scanner.nextInt();
				--serialNumber;
				if (serialNumber > policies.size()) {
					throw new ClaimRegException("Kindly enter valid serial number which are listed above");
				} else if (policies.get(serialNumber).getNoOfClaims() == 0) {
					throw new ClaimRegException(
							"No Claims exist with Policy Number:" + policies.get(serialNumber).getPolicyNumber());
				} else {
					serialNoFlag = true;
					policyNumber = policies.get(serialNumber).getPolicyNumber();

				}

			} catch (InputMismatchException e) {
				System.err.println("Enter Number only");
				serialNoFlag = false;
			} catch (ClaimRegException e) {
				serialNoFlag = false;
				System.err.println(e.getMessage());
			} catch (ArrayIndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");

			} catch (IndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");

			}

		} while (!serialNoFlag);
		
		logger.debug("policy number selected ");
		return policyNumber;

	}

	private List<Claim> displayAllClaim(long policyNumber) {
		logger.debug("displaying all claims ");
		int count = 0;
		List<Claim> claims = new ArrayList<Claim>();
		try {
			claims = service.getAllClaimsOnPolicyNumber(policyNumber);

			if (claims.isEmpty()) {
				System.out.println("There is no claim against choosen policy");
				logger.debug("There is no claim against choosen policy ");

			} else {
				String format = String.format("\n\n%-15s %-20s %-20s %-20s", "SERIAL NO", "CLAIM NUMBER", "CLAIM TYPE",
						"POLICY NUMBER");
				System.out.println(format);

				String line = String.format("%-15s %-20s %-20s %-20s", "==========", "============", "===============",
						"================");
				System.out.println(line);

				for (Claim claim : claims) {

					String data = String.format("%-15s %-20s %-20s %-20s", ++count, claim.getClaimNumber(),
							claim.getClaimType(), claim.getPolicyNumber());

					System.out.println(data);
				}

			}

		} catch (ClaimRegException e) {

			System.out.println(e.getMessage());
			logger.error(e.getMessage());
		}
		
		logger.debug("all claims displayed ");
		return claims;
	}

	private void createNewUserRole() {

		System.out.println("\n...............New Profile Creation..............\n");
		logger.debug("In new profile creation function");

		boolean userNameFlag = false;
		String userName = null;
		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Enter a new User Name:");
				userName = scanner.next();
				userNameFlag = service.validateNewUserName(userName);

			} catch (ClaimRegException e1) {
				userNameFlag = false;
				System.err.println(e1.getMessage());
				logger.error(e1.getMessage());

			}

		} while (!userNameFlag);

		String password = null;
		boolean passwordFlag = false;
		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Enter new Password:");
				password = scanner.nextLine();
				passwordFlag = service.validatePassword(password);

			} catch (ClaimRegException e) {
				passwordFlag = false;
				System.err.println(e.getMessage());
				logger.error(e.getMessage());
			}

		} while (!passwordFlag);

		int input = 0;
		boolean inputFlag = true;
		String roleCode = null;
		System.out.println("Select role code for Username:" + userName);
		System.out.println("1. Insured ");
		System.out.println("2. Agent ");
		System.out.println("3. Admin ");

		do {
			scanner = new Scanner(System.in);
			System.out.println("Enter your choice for role code:");

			try {
				input = scanner.nextInt();
				switch (input) {
				case 1:
					roleCode = "Insured";
					inputFlag = true;

					break;
				case 2:

					roleCode = "Agent";
					inputFlag = true;

					break;
				case 3:

					roleCode = "Admin";
					inputFlag = true;

					break;
				default:

					System.err.println("Enter within range of 1-3");
					logger.error("Enter within range of 1-3 in select role code");
					inputFlag = false;
				}

			} catch (InputMismatchException e) {

				System.err.println("Input should be number only");
				logger.error("Input should be number only:select Role Code");

				inputFlag = false;
			}

		} while (!inputFlag);

		UserRole userRole = new UserRole(userName, password, roleCode);

		try {
			service.insertNewProfile(userRole);

			System.out.println("New User Created with:");
			System.out.println("Username:" + userName);
			System.out.println("Role Code:" + roleCode);

			logger.info("New User Created");

		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
			logger.error(e.getMessage());
		}
		
		logger.debug("New profile created");

	}

	public List<Policy> displayAllPoliciesOnAgent(String agentUserName) {
		logger.debug("displaying all policies based on agent username");
		int count = 0;
		List<Policy> policies = new ArrayList<Policy>();
		System.out.println("\n");

		try {
			policies = service.getAllPolicyBasedOnAgent(agentUserName);

		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
			logger.error(e.getMessage());
		}
		if (policies.isEmpty()) {
			System.out.println("There is a no existing policies under you");
			logger.info("There is a no existing policies under you");
		} else {
			String format = String.format("%-15s %-20s %-20s %-20s %-20s", "SERIAL NO", "POLICY NUMBER",
					"POLICY PREMIUM", "ACCOUNT NUMBER", "Number of CLAIM REGISTERED");
			System.out.println(format);

			String line = String.format("%-15s %-20s %-20s %-20s %-20s", "==========", "============",
					"===============", "================", "======================");
			System.out.println(line);

			for (Policy policy : policies) {

				String data = String.format("%-15s %-20s %-20s %-20s %-20s", ++count, policy.getPolicyNumber(),
						policy.getPolicyPremium(), policy.getAccountNumber(), policy.getNoOfClaims());

				System.out.println(data);
			}
		}
		
		logger.debug("all policy diplayed");
		return policies;
	}

	public List<Policy> displayAllPoliciesOfUserName(String userName) {
		logger.debug("displaying all policies based on  username");

		int count = 0;
		List<Policy> policies = new ArrayList<Policy>();
		System.out.println("\n");
		try {
			policies = service.getPolicyList(userName);
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
			logger.error(e.getMessage());
		}

		if (policies.isEmpty()) {
			System.out.println("You don't have any existing policies.");
		} else {
			String format = String.format("%-15s %-20s %-20s %-20s %-20s", "SERIAL NO", "POLICY NUMBER",
					"POLICY PREMIUM", "ACCOUNT NUMBER", "Number of CLAIM REGISTERED");
			System.out.println(format);

			String line = String.format("%-15s %-20s %-20s %-20s %-20s", "==========", "============",
					"===============", "================", "======================");
			System.out.println(line);

			for (Policy policy : policies) {

				String data = String.format("%-15s %-20s %-20s %-20s %-20s", ++count, policy.getPolicyNumber(),
						policy.getPolicyPremium(), policy.getAccountNumber(), policy.getNoOfClaims());

				System.out.println(data);
			}
		}
		logger.debug("all policy diplayed");
		return policies;
	}

	public List<Policy> displayAllPolicies() {
		logger.debug("displaying all policies ");
		int count = 0;
		List<Policy> policies = new ArrayList<Policy>();
		System.out.println("\n");
		try {
			policies = service.getAllPolicyList();
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
			logger.error(e.getMessage());
		}

		if (policies.isEmpty()) {
			System.out.println("You don't have any existing policies.");
		} else {
			String format = String.format("%-15s %-20s %-20s %-20s %-20s", "SERIAL NO", "POLICY NUMBER",
					"POLICY PREMIUM", "ACCOUNT NUMBER", "Number of CLAIM REGISTERED");
			System.out.println(format);

			String line = String.format("%-15s %-20s %-20s %-20s %-20s", "==========", "============",
					"===============", "================", "======================");
			System.out.println(line);

			for (Policy policy : policies) {

				String data = String.format("%-15s %-20s %-20s %-20s %-20s", ++count, policy.getPolicyNumber(),
						policy.getPolicyPremium(), policy.getAccountNumber(), policy.getNoOfClaims());

				System.out.println(data);
			}
		}
		logger.debug("all policy diplayed");
		return policies;
	}

	public void createClaim(long policyNumber) {
		logger.debug("function for creating claim on policy number");

		long claimNumber = 0l;
		String claimReason = null;
		String accidentLocation = null;
		String accidentCity = null;
		String accidentState = null;
		int accidentZipCode = 0;
		String claimType = null;

		boolean claimReasonFlag = false;

		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Please Enter Claim Reason :");
				claimReason = scanner.nextLine();
				claimReasonFlag = service.validateClaimReason(claimReason);
				logger.debug("validating claim reason");

			} catch (ClaimRegException e) {
				System.err.println(e.getMessage());
				claimReasonFlag = false;
				logger.error(e.getMessage());

			}

		} while (!claimReasonFlag);

		boolean accidentLocationFlag = false;

		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Please Enter Accident Location : ");
				accidentLocation = scanner.nextLine();
				accidentLocationFlag = service.validateAccidentLocation(accidentLocation);
				logger.debug("validating accident location");
			} catch (ClaimRegException e) {
				System.err.println(e.getMessage());
				accidentLocationFlag = false;
			}
		} while (!accidentLocationFlag);

		boolean accidentCityFlag = false;

		do {
			scanner = new Scanner(System.in);
			try {
				System.out.println("Please Enter the Accident City : ");
				accidentCity = scanner.nextLine();
				logger.debug("validating accident city");
				accidentCityFlag = service.validateAccidentCity(accidentCity);
			} catch (ClaimRegException e) {
				System.err.println(e.getMessage());
				accidentCityFlag = false;
				logger.error(e.getMessage());

			}
		} while (!accidentCityFlag);

		boolean accidentStateFlag = false;

		do {
			scanner = new Scanner(System.in);
			try {
				System.out.println("Please Enter Accident State : ");
				accidentState = scanner.nextLine();
				logger.debug("validating accident state");
				accidentStateFlag = service.validateAccidentState(accidentState);
			} catch (ClaimRegException e) {
				System.err.println(e.getMessage());
				accidentStateFlag = false;
				logger.error(e.getMessage());
			}
		} while (!accidentStateFlag);

		boolean accidentZipCodeFlag = false;

		do {
			scanner = new Scanner(System.in);
			try {
				System.out.println("Please Enter Accident Zip Code : ");
				accidentZipCode = scanner.nextInt();
				logger.debug("validating accident zip code");
				accidentZipCodeFlag = service.validateAccidentZip(accidentZipCode);
			} catch (ClaimRegException e) {
				System.err.println(e.getMessage());
				accidentZipCodeFlag = false;
				logger.error(e.getMessage());

			} catch (InputMismatchException e) {
				System.err.println("Enter Number Only");
				accidentZipCodeFlag = false;
				logger.error(e.getMessage());
			}

		} while (!accidentZipCodeFlag);

		int choice = 0;
		boolean choiceFlag = true;
		System.out.println(".........Select Claim Type:.........");
		System.out.println("1. Home Insurance ");
		System.out.println("2. Motor Insurance");
		System.out.println("3. Health Insurance ");
		logger.debug("select claim type");
		do {

			System.out.println("Enter your choice:");
			scanner = new Scanner(System.in);

			try {
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					claimType = "Home Insurance";
					claimNumber = 1110000000l;
					choiceFlag = true;

					break;
				case 2:
					claimType = "Motor Insurance";
					claimNumber = 2220000000l;
					choiceFlag = true;

					break;
				case 3:
					claimType = "Health Insurance";
					claimNumber = 3330000000l;
					choiceFlag = true;

					break;
				default:
					System.err.println("Enter within range of 1-3");
					logger.error("Enter within range of 1-3 in select role code");
					choiceFlag = false;
				}

			} catch (InputMismatchException e) {

				System.err.println("Input should be number only");
				logger.error("Input should be number only:select Role Code");
				choiceFlag = false;
			}

		} while (!choiceFlag);

		List<ClaimDetails> claimDetails = new ArrayList<>();
		
		logger.debug("Calling askClaimQuestions(claimType)");
		claimDetails = askClaimQuestions(claimType);

		Claim claim = new Claim(claimNumber, claimReason, accidentLocation, accidentCity, accidentState,
				accidentZipCode, claimType, policyNumber);
		
		
		try {
			claimNumber = service.insertNewClaim(claim);
			System.out.println("Claim is registered with Claim Number: " + claimNumber);
			for (ClaimDetails claimdetail : claimDetails) {
				claimdetail.setClaimNumber(claimNumber);
				logger.debug("inserting claim details");
				service.insertClaimDetails(claimdetail);
			}

		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
			logger.error(e.getMessage());
		}

	}

	private List<ClaimDetails> askClaimQuestions(String claimType) {
		logger.debug("In askClaimQuestions(claimType)");
		List<ClaimQuestions> questions = new ArrayList<>();
		List<ClaimDetails> claimDetails = new ArrayList<>();

		ClaimDetails claimDetail = null;
		String question;
		int questionId;
		String answer = null;
		String answer1;
		String answer2;
		String answer3;
		int choice = 0;
		scanner = new Scanner(System.in);

		try {
			questions = service.getQuestionsBasedOnClaimType(claimType);
			logger.info("question from data base is fetched");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}
		
		

		System.out.println("\nAnswer the following questions for claiming:\n");

		for (ClaimQuestions claimQuestions : questions) {
			question = claimQuestions.getQuestion();
			questionId = claimQuestions.getQuestionId();
			answer1 = claimQuestions.getAnswer1();
			answer2 = claimQuestions.getAnswer2();
			answer3 = claimQuestions.getAnswer3();

			if (answer1 == null) {

				answer = getAnswer(question);

			} else {
				System.out.println(question);
				System.out.println();
				System.out.println("Options:");
				System.out.println("==============");
				System.out.println("1. " + answer1);
				if (answer2 != null)
					System.out.println("2. " + answer2);
				if (answer3 != null)
					System.out.println("3. " + answer3);
				System.out.println();
				System.out.println("Enter your choice:");

				boolean optionFlag = false;

				do {
					scanner = new Scanner(System.in);
					try {
						choice = scanner.nextInt();
						scanner.nextLine();
						if (choice < 1 || choice > 3) {
							throw new ClaimRegException("Kindly enter option within range");
						} else if (choice == 3 && answer3 == null) {
							throw new ClaimRegException("Kindly enter option within range");
						} else {
							optionFlag = true;
						}

					} catch (InputMismatchException e) {
						System.err.println("Enter valid option");
						optionFlag = false;
					} catch (ClaimRegException e) {
						System.err.println(e.getMessage());
						logger.error(e.getMessage());
						optionFlag = false;
					}

				} while (!optionFlag);

				if (choice == 1)
					answer = answer1;
				else if (choice == 2)
					answer = answer2;
				else if (choice == 3)
					answer = answer3;
			}

			claimDetail = new ClaimDetails(questionId, answer);
			claimDetails.add(claimDetail);

		}

		return claimDetails;

	}

	private String getAnswer(String question) {
		int choice = 0;
		boolean answerFlag = false;
		String answer = null;
		
		if (question.contains("Year") || question.contains("year")) {
			choice = 1;
		} else if (question.contains("Distance travelled") || question.contains("Value")
				|| question.contains("Charges")) {
			choice = 2;
		} else if (question.contains("Date") || question.contains("date")) {
			choice = 3;
		} else if (question.contains("Chasis-Number")) {
			choice = 4;
		} else {
			choice = 5;
		}

		switch (choice) {
		case 1:
			logger.info("question type year");
			int year = 0;
			answerFlag = false;
			do {
				scanner = new Scanner(System.in);
				try {
					System.out.println(question);
					year = scanner.nextInt();
					answerFlag = service.validateYear(year);
					logger.info("validates year");
				} catch (ClaimRegException e) {
					System.err.println(e.getMessage());
					answerFlag = false;

				} catch (InputMismatchException e) {
					System.err.println("Kindly enter year in number Only.");
					answerFlag = false;
				}

			} while (!answerFlag);

			answer = Integer.toString(year);

			break;
		case 2:
			logger.info("question type money value");
			long value = 0l;
			answerFlag = false;
			do {
				scanner = new Scanner(System.in);
				try {
					System.out.println(question);
					value = scanner.nextLong();
					answerFlag = service.validateMoneyValue(value);
					logger.info("validates Money Value");

				} catch (InputMismatchException e) {
					System.err.println("Kindly enter value in number Only.");
					answerFlag = false;
				} catch (ClaimRegException e) {
					System.err.println(e.getMessage());
					answerFlag = false;
				}
			} while (!answerFlag);

			answer = Long.toString(value);

			break;

		case 3:
			logger.info("question type date");
			String date = null;
			answerFlag = false;
			do {
				scanner = new Scanner(System.in);
				try {
					System.out.println(question);
					System.out.println("Enter in format(YYYY-MM-dd)");
					date = scanner.next();
					answerFlag = service.validateDate(date);
					logger.info("validate date");

				} catch (ClaimRegException e) {
					System.err.println(e.getMessage());
					answerFlag = false;
				}
			} while (!answerFlag);

			answer = date;

			break;
		case 4:
			logger.info("question type chasis number");
			String chassisNo = null;
			answerFlag = false;
			do {
				scanner = new Scanner(System.in);
				try {
					System.out.println(question);
					chassisNo = scanner.next();
					answerFlag = service.validateChassisNumber(chassisNo);
					logger.info("validate chasis number");

				} catch (ClaimRegException e) {
					System.err.println(e.getMessage());
					answerFlag = false;
				}
			} while (!answerFlag);

			answer = chassisNo;
			System.out.println(answer);

			break;
		default:
			scanner = new Scanner(System.in);
			System.out.println(question);
			answer = scanner.nextLine();
			break;

		}

		return answer;
	}

	public void viewClaim(long claimNumber) {
		
		logger.info("In function view claim");

		Claim claim = null;

		try {
			claim = service.getClaim(claimNumber);
			logger.info("claim from data base fetched");
			System.out.println("\n.................Claim Details................\n");

			System.out.println("Claim Number:" + claim.getClaimNumber());
			System.out.println("Claim Reason:" + claim.getClaimReason());
			System.out.println("Accident Location Street:" + claim.getAccidentLocation());
			System.out.println("Accident City:" + claim.getAccidentCity());
			System.out.println("Accident State:" + claim.getAccidentState());
			System.out.println("Accident Zip Code:" + claim.getAccidentZipCode());
			System.out.println("Claim Type:" + claim.getClaimType());
			System.out.println("Policy Number:" + claim.getPolicyNumber());

		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
			logger.error(e.getMessage());
		}
		logger.info("claim displayed");
		

	}

	private void generateReport(long claimNumber) {
		logger.info("In function generateReport(long claimNumber)");

		List<ClaimDetails> claimDetails = null;
		viewClaim(claimNumber);
		int counter = 0;

		try {
			claimDetails = service.getClaimDetails(claimNumber);
			logger.info("claim details from data base fetched");

			String format = String.format("%-20s %-50s %-20s", "SERIAL NO", "QUESTION", "ANSWER");
			System.out.println(format);

			String line = String.format("%-20s %-50s %-20s", "===========", "============", "===============");
			System.out.println(line);

			for (ClaimDetails claimDetail : claimDetails) {
				String data = String.format("%-20s %-50s %-20s", ++counter, claimDetail.getQuestion(),
						claimDetail.getAnswer());

				System.out.println(data);
			}

		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
			logger.error(e.getMessage());
		}
		logger.info("claim displayed");

	}

	private void deleteGeneral(String userName) {
		logger.info("In function deleteGeneral(String userName)");
		int choice = 0;

		long policyNumber;
		long claimNumber;
		boolean choiceFlag = true;
		List<UserRole> allUsers = null;
		List<Accounts> allAccounts = null;
		List<Policy> policies = null;
		List<Claim> claims = null;
		System.out.println("..........Delete Menu for Admin.........");
		System.out.println("1. Delete a user(Other Admin/Agent/Insured. ");
		System.out.println("2. Delete a account.");
		System.out.println("3. Delete a policy");
		System.out.println("4. Delete a claim");

		do {

			System.out.println("Enter your choice");
			scanner = new Scanner(System.in);

			try {
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					logger.info("calling  deleteAUser(allUsers)");
					allUsers = displayAllUser(userName);
					deleteAUser(allUsers);
					System.out.println("\n After Deletion.........");
					allUsers = displayAllUser(userName);
					choiceFlag = true;
					logger.info("Deleted a user");
					break;
				case 2:
					logger.info("calling  deleteAAccount(allAccounts)");
					allAccounts = displayAllAcounts(userName);
					deleteAAccount(allAccounts);
					System.out.println("\n After Deletion.........");
					allAccounts = displayAllAcounts(userName);
					choiceFlag = true;
					logger.info("Deleted a account");
					break;
				case 3:
					logger.info("calling  deleteAPolicy(policyNumber)");
					policies = displayAllPolicies();
					if (policies.isEmpty()) {
						System.err.println("There is no existing policies to delete");
						choiceFlag = true;
						System.out.println("\n");
					} else {
						policyNumber = choosePolicyFromPolicyList(policies);
						deleteAPolicy(policyNumber);
						System.out.println("\n After Deletion.........");
						policies = displayAllPolicies();
						choiceFlag = true;
						policies = displayAllPolicies();
					}
					
					logger.info("Deleted a policies");
					break;
				case 4:
					logger.info("calling  deleteAClaim(claimNumber)");
					System.out.println("First Select Policy to view claim.");
					policies = displayAllPolicies();
					if (policies.isEmpty()) {
						System.err.println("There is no existing policies to view claim");
						choiceFlag = true;
						System.out.println("\n");
					} else {

						int checkClaim = checkClaim(policies);

						if (checkClaim == 1) {
							policyNumber = choosePolicyFromListForViewClaim(policies);
							claims = displayAllClaim(policyNumber);
							claimNumber = chooseClaimFromList(claims);
							deleteAClaim(claimNumber);
							System.out.println("\n After Deletion.........");
							claims = displayAllClaim(policyNumber);
							choiceFlag = true;
							System.out.println("\n");
						} else {
							System.out.println("There is no claim to view");
							choiceFlag = true;
							System.out.println("\n");
						}

					}
					logger.info("Deleted a claims");
					choiceFlag = true;
					break;
				default:
					System.err.println("Enter within range of 1-4");
					logger.error("Enter within range of 1-4 in select role code");
					choiceFlag = false;
				}

			} catch (InputMismatchException e) {

				System.err.println("Input should be number only");
				logger.error("Input should be number only:select Role Code");
				choiceFlag = false;
			}

		} while (!choiceFlag);

	}

	private void deleteAClaim(long claimNumber) {
		
		logger.info("In   deleteAClaim(claimNumber)");

		System.out.println("On deleting a claim, all claim data will get deleted.");

		viewClaim(claimNumber);

		System.out.println("\n Are you sure want to delete above claim data ?");
		System.out.println("Enter yes or no:");

		String checkToDelete = null;
		boolean yesFlag = false;
		do {

			scanner = new Scanner(System.in);

			try {
				checkToDelete = scanner.next();
				if (checkToDelete.equalsIgnoreCase("yes") || checkToDelete.equalsIgnoreCase("no")) {
					yesFlag = true;
				} else {
					throw new ClaimRegException("Enter either yes or no");
				}

			} catch (ClaimRegException e) {
				yesFlag = false;
				System.err.println(e.getMessage());
			}

		} while (!yesFlag);

		if (checkToDelete.equalsIgnoreCase("yes")) {
			try {
				service.deleteAClaim(claimNumber);
				System.out.println("Message:Policy deleted Successfully");
				logger.info("deleted");

			} catch (ClaimRegException e) {
				System.err.println(e.getMessage());
			}

		}
		
		logger.info("At end of   deleteAClaim(claimNumber)");
	}

	private void deleteAPolicy(long policyNumber) {
		logger.info("In   deleteAPolicy(long policyNumber) ");
		System.out.println("On deleting a policy, all his claims will get deleted.");
		System.out.println("Are you sure want to delete policy having policy number :" + policyNumber + " ?");
		System.out.println("Enter yes or no:");

		String checkToDelete = null;
		boolean yesFlag = false;
		do {

			scanner = new Scanner(System.in);

			try {
				checkToDelete = scanner.next();
				if (checkToDelete.equalsIgnoreCase("yes") || checkToDelete.equalsIgnoreCase("no")) {
					yesFlag = true;
				} else {
					throw new ClaimRegException("Enter either yes or no");
				}

			} catch (ClaimRegException e) {
				yesFlag = false;
				System.err.println(e.getMessage());
			}

		} while (!yesFlag);

		if (checkToDelete.equalsIgnoreCase("yes")) {
			try {
				service.deleteAPolicy(policyNumber);
				System.out.println("Message:Policy deleted Successfully");
				logger.info("deleted");
			} catch (ClaimRegException e) {
				System.err.println(e.getMessage());
			}

		}
		
		logger.info("At end of   delete A policy ");
	}

	private void deleteAAccount(List<Accounts> allAccounts) {
		logger.info("In   delete a account ");
		if (allAccounts.isEmpty()) {
			System.err.println("No profile exist to delete");

		} else {
			long accountNoToDelete = selectAccountToDelete(allAccounts);
			String name = null;

			Iterator<Accounts> iterator = allAccounts.iterator();
			while (iterator.hasNext()) {
				Accounts account = iterator.next();
				if (account.getAccountNumber() == accountNoToDelete) {
					name = account.getInsuredName();
				}
			}

			System.out.println("On deleting a account, all his polices,claims with get deleted.");
			System.out.println("Are you sure want to delete account of :" + name.toUpperCase() + " with account number:"
					+ accountNoToDelete + " ?");
			System.out.println("Enter yes or no:");

			String checkToDelete = null;
			boolean yesFlag = false;
			do {

				scanner = new Scanner(System.in);

				try {
					checkToDelete = scanner.next();
					if (checkToDelete.equalsIgnoreCase("yes") || checkToDelete.equalsIgnoreCase("no")) {
						yesFlag = true;
					} else {
						throw new ClaimRegException("Enter either yes or no");
					}

				} catch (ClaimRegException e) {
					yesFlag = false;
					System.err.println(e.getMessage());
				}

			} while (!yesFlag);

			if (checkToDelete.equalsIgnoreCase("yes")) {
				try {
					service.deleteAAccount(accountNoToDelete);
					System.out.println("Message:Account deleted Successfully");
					logger.info("deleted ");
				} catch (ClaimRegException e) {
					System.err.println(e.getMessage());
				}

			}

		}
		
		logger.info("End of delete a accounts ");

	}

	private long selectAccountToDelete(List<Accounts> allAccounts) {
		
		logger.info("In select a account to delete");
		int serialNumber = 0;
		long accountNoToDelete = 0l;

		boolean serialNoFlag = false;

		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Enter a SERIAL NUMBER of above Account LIST TO DELETE:");
				serialNumber = scanner.nextInt();
				--serialNumber;
				if (serialNumber > allAccounts.size()) {
					throw new ClaimRegException("Kindly enter valid serial number which are listed above");

				} else {
					serialNoFlag = true;
					accountNoToDelete = allAccounts.get(serialNumber).getAccountNumber();

				}

			} catch (InputMismatchException e) {
				System.err.println("Kindly enter in Number only");
				serialNoFlag = false;
			} catch (ClaimRegException e) {
				serialNoFlag = false;
				System.err.println(e.getMessage());
			} catch (ArrayIndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");

			} catch (IndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");

			}

		} while (!serialNoFlag);
		
		logger.info("Account selected");

		return accountNoToDelete;
	}

	private List<Accounts> displayAllAcounts(String userName) {
		
		logger.info("In display all accounts");
		List<Accounts> allAccounts = new ArrayList<Accounts>();

		int counter = 0;

		try {
			allAccounts = service.getAllAccounts("Admin");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

		if (allAccounts.isEmpty()) {
			System.out.println("There is no accounts in system");
		} else {
			String format = String.format("%-10s %-20s %-25s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", "SERIAL NO",
					"ACCOUNT NUMBER", "INSURED NAME", "STREET", "CITY", "STATE", "ZIP CODE", "BUSINESS SEGMENT",
					"USERNAME", "AGENT USERNAME");
			System.out.println(format);

			String line = String.format("%-10s %-20s %-25s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", "==========",
					"==========", "============", "===========", "==========", "============", "===========",
					"==========", "============", "===========");
			System.out.println(line);

			for (Accounts account : allAccounts) {

				String data = String.format("%-10s %-20s %-25s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", ++counter,
						account.getAccountNumber(), account.getInsuredName(), account.getInsuredState(),
						account.getInsuredCity(), account.getInsuredState(), account.getInsuredZipCode(),
						account.getBussinessSegment(), account.getUserName(), account.getAgentUserName());
				System.out.println(data);
			}
		}

		logger.info("All accounts dipslayed");
		return allAccounts;
	}

	private List<UserRole> displayAllUser(String userName) {
		
		logger.info("In display all user");
		int counter = 0;

		List<UserRole> allUsers = new ArrayList<UserRole>();
		try {
			allUsers = service.getAllUser("Admin");
		} catch (ClaimRegException e) {
			System.err.println(e.getMessage());
		}

		Iterator<UserRole> iterator = allUsers.iterator();
		while (iterator.hasNext()) {
			UserRole userRole = iterator.next();
			if (userRole.getUserName().equalsIgnoreCase(userName)) {
				iterator.remove();
			}
		}

		if (allUsers.isEmpty()) {
			System.out.println("There is no other profile");
		} else {
			String format = String.format("%-20s %-20s %-20s", "SERIAL NO", "USER NAME", "ROLE CODE");
			System.out.println(format);

			String line = String.format("%-20s %-20s %-20s", "==========", "============", "===========");
			System.out.println(line);

			for (UserRole userRole : allUsers) {

				String data = String.format("%-20s %-20s %-20s", ++counter, userRole.getUserName(),
						userRole.getRoleCode());
				System.out.println(data);
			}
		}
		
		logger.info("All user displayed");
		return allUsers;
	}

	private void deleteAUser(List<UserRole> allUsers) {
		logger.info("In delete a user");
		
		if (allUsers.isEmpty()) {
			System.err.println("No profile exist to delete");

		} else {
			String userNameToDelete = selectUserRoleToDelete(allUsers);

			System.out.println("On deleting a user, all his account,polices,claims with get deleted.");
			System.out.println("Are you sure want to delete profile of :" + userNameToDelete);
			System.out.println("Enter yes or no:");

			String checkToDelete = null;
			boolean yesFlag = false;
			do {

				scanner = new Scanner(System.in);

				try {
					checkToDelete = scanner.next();
					if (checkToDelete.equalsIgnoreCase("yes") || checkToDelete.equalsIgnoreCase("no")) {
						yesFlag = true;
					} else {
						throw new ClaimRegException("Enter either yes or no");
					}

				} catch (ClaimRegException e) {
					yesFlag = false;
					System.err.println(e.getMessage());
				}

			} while (!yesFlag);

			if (checkToDelete.equalsIgnoreCase("yes")) {
				try {
					service.deleteAUser(userNameToDelete);
					System.out.println("Message:User deleted Successfully");
					logger.info("In deleted");

				} catch (ClaimRegException e) {
					System.err.println(e.getMessage());
				}

			}

		}
		
		logger.info("End of delete a user");

	}

	private String selectUserRoleToDelete(List<UserRole> allUsers) {
		
		logger.info("In selecting a user to delete");

		int serialNumber = 0;
		String userNameToDelete = null;

		boolean serialNoFlag = false;

		do {
			scanner = new Scanner(System.in);

			try {
				System.out.println("Enter a SERIAL NUMBER of above USER ROLE LIST TO DELETE:");
				serialNumber = scanner.nextInt();
				--serialNumber;
				if (serialNumber > allUsers.size()) {
					throw new ClaimRegException("Kindly enter valid serial number which are listed above");

				} else {
					serialNoFlag = true;
					userNameToDelete = allUsers.get(serialNumber).getUserName();

				}

			} catch (InputMismatchException e) {
				System.err.println("Kindly enter in Number only");
				serialNoFlag = false;
			} catch (ClaimRegException e) {
				serialNoFlag = false;
				System.err.println(e.getMessage());
				logger.error(e.getMessage());
			} catch (ArrayIndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");

			} catch (IndexOutOfBoundsException e) {
				serialNoFlag = false;
				System.err.println("Kindly enter valid serial number which are listed above");

			}

		} while (!serialNoFlag);
		
		logger.info("In selecting a user to delete:user selected");

		return userNameToDelete;
	}

	private int checkClaim(List<Policy> policies) {
		
		logger.info("In checkCalim if there is no claim in total data base");
		int count = 0;
		Iterator<Policy> iterator = policies.iterator();
		while (iterator.hasNext()) {
			Policy policy = iterator.next();
			if (policy.getNoOfClaims() > 0) {
				count = 1;
				logger.info("few claims found in data base");
			}

		}
		logger.info("In checkCalim if there is no claim in total data base:Checked");

		return count;
	}

}
