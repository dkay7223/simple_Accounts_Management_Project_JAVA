import java.util.Calendar;
import java.util.Scanner;

public class Manager extends User {
	static Scanner input = new Scanner(System.in);
	String password = "1234";
	
	protected void ask_manager_details() {
		this.setName(ask_name());
		this.setAddress(ask_address());
		this.setPhone_no(ask_phoneNumber());
	}

	protected void manager_details() {
		System.out.println("Name : " + this.getName());
		System.out.println("Address : " + this.getAddress());
		System.out.println("Phone Number : " + this.getPhone_no());
	}

	protected boolean password_authentication() {
		System.out.println(" Please Enter your Password");
		String passcode = input.next();
		if (passcode.equals(password)) {
			System.out.println(" Login succesful!");
			return true;
		} else
			System.out.println(" Password incorrect");
		return false;
	}

	protected boolean change_password() {
		if (password_authentication() == true) {
			System.out.println(" Enter a new password!");
			String password = input.next();
			this.password = password;
			System.out.println(" Password changed");
			return true;
		} else {
			System.out.println("Incorrect password!");
			return false;
		}
	}


	protected boolean create_account() {
		// TODO Auto-generated method stub
		System.out.println(" Press 1: For Current Account");
		System.out.println(" Press 2: For Savings Account");
		System.out.println(" Press 0: To return to main menu");
		int choice = input.nextInt();
		String name = ask_name();
		String phone = ask_phoneNumber();
		String cnic = ask_CNIC();
		String Address = ask_address();
		String PIN = ask_PIN();
		if (check_account_status(cnic) == true) {
			System.out.println(" Account was not created succesfully!");
			return false;
		} else {

			switch (choice) {
			case 1: {

				Account acc = new Account(cnic, PIN, "Current");
				Customer customer = new Customer(name, phone, cnic, Address, acc);
				Customer.customer[Customer.customer_count] = (Customer) customer;
				System.out.println(" Account succesfully created!");
				System.out.println(" Your account Number is: "
						+ Customer.customer[Customer.customer_count].getAccount().getAccountID());
				Customer.customer[Customer.customer_count].T.transaction_count=0;
				
				Customer.customer_count++;
				view_account_details(cnic);
				return true;
				
			}
			case 2: {

				Account acc = new Account(cnic, PIN, "Savings");
				Customer customer = new Customer(name, phone, cnic, Address, acc);
				Customer.customer[Customer.customer_count] = (Customer) customer;
				System.out.println(" Account succesfully created!");
				System.out.println(" Your account Number is: "
						+ Customer.customer[Customer.customer_count].getAccount().getAccountID());
				Customer.customer[Customer.customer_count].T.transaction_count=0;
				Customer.customer_count++;
				view_account_details(cnic);
				return true;

			}

			case 0:
				return false;

			default:
				System.out.println(" Enter a valid choice!");
				create_account();
			}
		}

		view_account_details(cnic);
		System.out.println("Account was not created succesfully!");
		return false;

	}

	private boolean check_account_status(String accountID) {
		// TODO Auto-generated method stub
		for (int i = 0; i < Customer.customer_count; i++) {
			if (accountID.equals(Customer.customer[i].account.getAccountID())) {
				System.out.println("\nThis account exists.");
				view_account_details(accountID);
				return true;
			}
		}
		return false;
	}

	protected void view_account_details(String accountID) {
		System.out.println(" \n");
		for (int i = 0; i < Customer.customer_count; i++) {
			if (Customer.customer[i].account.getAccountID().equals(accountID)) {
				System.out.println("\n\tName: " + Customer.customer[i].getName() + "\n\tAccount Type: "
						+ Customer.customer[i].getAccount().getAccount_type() + "\n\tAccountID: "
						+ Customer.customer[i].getAccount().getAccountID() + "\n\tCreated on: "
						+ Customer.customer[i].getAccount().getCreation_date() + "\n\tBalance: "
						+ Customer.customer[i].getAccount().getBalance());
				System.out.println("\n");
			} else
				;
		}
	}

	protected boolean delete_account() {
		// TODO Auto-generated method stub
		System.out.println(" Enter the ID of the account you want to delete!");
		String accID = input.next();
		check_account_status(accID);
		System.out.println("Press 1: To confirm Delete");
		System.out.println("Press 2: To exit without deleting");
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			for (int i = 0; i < Customer.customer_count; i++) {
				if (Customer.customer[i].account.getAccountID().equalsIgnoreCase(accID)) {
					Customer.customer[i].account.setAccountID("Account Not Available!");
					Customer.customer[i].account.setExisting_status("Deleted!");
					Customer.customer[i].account.setPINcode("@DELETED");
					Customer.customer[i].account.setDelete_date(Calendar.getInstance().getTime());
					Customer.deleted_customer_count++;
					System.out.println(" \nAccount succesfully Deleted!\n");
					return true;
				}
			}
		case 2:
			return false;
		default:
			System.out.println("Please Enter a valid choice!");
			delete_account();
		}
		return true;
	}

	protected void viewAllCustomers() {
		System.out.println("\n\n");
		System.out.println("  Name" + "\t   Phone number" + "\t   Account Type" + "\t   Account ID" + "\t   Balance"
				+ "\t   Creation Date" + "\t\t   Existing Status");
		for (int i = 0; i < Customer.customer_count; i++) {
			System.out.println(
					(i + 1) + ". " + Customer.customer[i].getName() + "\t     " + Customer.customer[i].getPhone_no()
							+ "\t     " + Customer.customer[i].getAccount().getAccount_type() + "\t    "
							+ Customer.customer[i].getAccount().getAccountID() + "\t\t    "
							+ Customer.customer[i].getAccount().getBalance() + "\t\t"
							+ Customer.customer[i].getAccount().getCreation_date() + "\t   "
							+ Customer.customer[i].getAccount().getExisting_status());
		}
	}

	protected void forget_PIN() {
		// TODO Auto-generated method stub
		System.out.println(" Enter the accountID!");
		String accID = input.next();
		view_account_details(accID);
		for (int i = 0; i < Customer.customer_count; i++) {
			if (Customer.customer[i].account.getAccountID().equalsIgnoreCase(accID))
				System.out.println("PIN: " + Customer.customer[i].account.getPINcode());
		}
	}

	protected String ask_name() {
		System.out.println(" Please Enter the name: ");
		String Input_Name = input.next();
		input.nextLine();
		return Input_Name;
	}

	protected String ask_CNIC() {
		System.out.println(" Please Enter your CNIC without dashes!: ");
		String Input_CNIC = input.next();
		return Input_CNIC;
	}
	
	protected String ask_accountID() {
		System.out.println(" Please Enter your accountID without dashes!: ");
		String Input_CNIC = input.next();
		return Input_CNIC;
	}
	
	protected String ask_phoneNumber() {
		System.out.println(" Please Enter your Phone Number: ");
		String Input_phoneNo = input.next();
		return Input_phoneNo;
	}

	protected String ask_address() {
		System.out.println(" Please Enter you address as per Format:");
		System.out.println(" House# aaa, Street# bbb, Sector/Town: ccc, City: ddd");
		String Input_address = input.next();
		input.nextLine();
		return Input_address;
	}

	protected String ask_PIN() {
		System.out.println(" Please Enter a PIN code for your account!");
		String PIN = input.next();
		input.nextLine();
		return PIN;
	}
}
