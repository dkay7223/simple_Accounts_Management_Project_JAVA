import java.util.*;

public class Account implements AccountOperations {

	private String AccountID;
	private String PINcode;
	private String account_type;
	private String existing_status;
	private Date creation_date;
	private Date delete_date;
	protected double balance;
	static Scanner input = new Scanner(System.in);
	private static int total_customer_count = 0;
	Transaction tr = new Transaction();

	public Account() {

	}

	public Account(String accountID, String pINcode, String account_type) {
		super();
		AccountID = accountID;
		PINcode = pINcode;
		this.balance = 0;
		this.creation_date = Calendar.getInstance().getTime();
		this.account_type = account_type;
		setExisting_status("Existing!");
		

	}

	@Override
	public boolean deposit_amount(String accountID) {
		System.out.println(" Please Input the amount you wish to deposit!");
		int deposit = input.nextInt();
		for (int i = 0; i < Customer.customer_count; i++) {
			if (accountID.equals(Customer.customer[i].account.AccountID)) {
				Customer.customer[i].account.balance += deposit;
				Customer.customer[i].T.transaction_records[tr.transaction_count].Transaction_type = "deposit";
				Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_amount = deposit;
				Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_date = Calendar
						.getInstance().getTime();
				Customer.customer[i].T.transaction_count++;
				System.out.println(" Amount deposited Succesfully!");
				return true;
			}
		}
		return false;
	}
	/*
	 * Transaction_type; transaction_amount; Date transaction_date;
	 * transaction_count;
	 */

	@Override
	public boolean transfer_amount(String accountID) {
		System.out.println(" Please Enter the accountID of the other person!!");
		String otherperson = input.next();
		int payee_index = array_index(accountID);
		if (check_account_status(otherperson) == true) {
			int payed_index = array_index(otherperson);
			System.out.println(" Please Input the amount you wish to Transfer!");
			int T_amount = input.nextInt();
			if (T_amount < check_balance(accountID)) {
				Customer.customer[payee_index].account
						.setBalance(Customer.customer[payee_index].account.getBalance() - T_amount);
				Customer.customer[payee_index].T.Transaction_type = "Transfer";
				Customer.customer[payee_index].T.transaction_amount = T_amount;
				Customer.customer[payee_index].T.transaction_date = Calendar.getInstance().getTime();
				Customer.customer[payee_index].T.transaction_count++;

				Customer.customer[payed_index].account
						.setBalance(Customer.customer[payed_index].account.getBalance() + T_amount);
				Customer.customer[payed_index].T.Transaction_type = "Receive";
				Customer.customer[payed_index].T.transaction_amount = T_amount;
				Customer.customer[payed_index].T.transaction_date = Calendar.getInstance().getTime();
				Customer.customer[payed_index].T.transaction_count++;

			}

		} else
			System.out.println(" The AccountId doesn't Exists!");
		return false;
	}

	protected int array_index(String accountID) {
		for (int i = 0; i < Customer.customer_count; i++) {
			if (accountID.equals(Customer.customer[i].account.AccountID)) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public boolean withdraw_balance(String accountID) {
		int i = array_index(accountID);
		System.out.println(" Please Input the amount you wish to Withdraw!");
		int withdraw = input.nextInt();
		if (withdraw < Customer.customer[i].account.balance) {
			Customer.customer[i].account.balance -= withdraw;
			Customer.customer[i].T.transaction_records[tr.transaction_count].Transaction_type = "Withdraw";
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_amount = withdraw;
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_date = Calendar.getInstance()
					.getTime();
			Customer.customer[i].T.transaction_count++;
			System.out.println(" Amount withdrawn Succesfully!");
			return true;
		} else
			return false;
	}

	public void view_details(String accountno) {
		// TODO Auto-generated method stub
		int i = array_index(accountno);
		System.out.println("\n-------------ACCOUNT STATEMENT-----------------------");
		System.out.println("Account Type        :  " + Customer.customer[i].account.account_type);
		System.out.println("Name                :  " + Customer.customer[i].getName());
		System.out.println("Address             :  " + Customer.customer[i].getAddress());
		System.out.println("Phone               :  " + Customer.customer[i].getPhone_no());
		System.out.println("Account Number      :  " + Customer.customer[i].account.AccountID);
		System.out.println("Date Created        :  " + Customer.customer[i].account.creation_date);
		System.out.println("Balance             :  " + Customer.customer[i].account.balance);
		System.out.println("Status              :  " + Customer.customer[i].account.existing_status);
		System.out.println("------------------------------------------------------\n");

	}

	@Override
	public void bank_statement(String accountID) {
		view_details(accountID);
		int j = array_index(accountID);
		System.out.println("Deposits!");
		System.out.println(" Date\t\t\t\t" + "Amount deposited");
		for (int i = 0; i < Customer.customer[j].T.transaction_count; i++) {
			if (Customer.customer[j].T.transaction_records[i].Transaction_type.equalsIgnoreCase("deposit")) {
				System.out.println(Customer.customer[j].T.transaction_records[i].transaction_date + "\t"
						+ Customer.customer[j].T.transaction_records[i].transaction_amount);

			}
		}
		System.out.println("Transfer!");
		System.out.println(" Date\t\t\t\t" + "Amount transfered");
		for (int i = 0; i < Customer.customer[j].T.transaction_count; i++) {
			if (Customer.customer[j].T.transaction_records[i].Transaction_type.equalsIgnoreCase("transfer")) {
				System.out.println(Customer.customer[j].T.transaction_records[i].transaction_date + "\t"
						+ Customer.customer[j].T.transaction_records[i].transaction_amount);

			}
		}
		System.out.println("withdraw!");
		System.out.println(" Date\t\t\t\t" + "Amount withdrawn");
		for (int i = 0; i < Customer.customer[j].T.transaction_count; i++) {
			if (Customer.customer[j].T.transaction_records[i].Transaction_type.equalsIgnoreCase("withdraw")) {
				System.out.println(Customer.customer[j].T.transaction_records[i].transaction_date + "\t"
						+ Customer.customer[j].T.transaction_records[i].transaction_amount);

			}
		}
		System.out.println("zakat!");
		System.out.println(" Date\t\t\t\t" + "Amount paid as zakat");
		for (int i = 0; i < Customer.customer[j].T.transaction_count; i++) {
			if (Customer.customer[j].T.transaction_records[i].Transaction_type.equalsIgnoreCase("zakat")) {
				System.out.println(Customer.customer[j].T.transaction_records[i].transaction_date + "\t"
						+ Customer.customer[j].T.transaction_records[i].transaction_amount);

			}
		}
		System.out.println("tax!");
		System.out.println(" Date\t\t\t\t" + "Amount paid as tax");
		for (int i = 0; i < Customer.customer[j].T.transaction_count; i++) {
			if (Customer.customer[j].T.transaction_records[i].Transaction_type.equalsIgnoreCase("tax")) {
				System.out.println(Customer.customer[j].T.transaction_records[i].transaction_date + "\t"
						+ Customer.customer[j].T.transaction_records[i].transaction_amount);

			}
		}

	}

	protected boolean check_account_status(String accountID) {
		// TODO Auto-generated method stub
		for (int i = 0; i < Customer.customer_count; i++) {
			if (accountID.equals(Customer.customer[i].account.getAccountID())) {
				System.out.println(Customer.customer[i].account.existing_status);
				return true;
			}
		}
		return false;
	}

	protected void change_credentials(String accountID) {
		int array_index = array_index(accountID);
		System.out.println(" What do wish to update?");
		System.out.println(" Press 1: For Name");
		System.out.println(" Press 2: For Phone Number");
		System.out.println(" Press 3: For Address");
		System.out.println(" Press 4: For PIN");
		System.out.println(" Press 5: To exit!");
		int choice = input.nextInt();
		switch (choice) {
		case 1:
			Customer.customer[array_index].setName(ask_name());
			change_credentials(accountID);
		case 2:
			Customer.customer[array_index].setPhone_no(ask_phoneNumber());
			change_credentials(accountID);
		case 3:
			Customer.customer[array_index].setAddress(ask_address());
			change_credentials(accountID);
		case 4:
			Customer.customer[array_index].account.setPINcode(ask_PIN());
			change_credentials(accountID);
		case 5:
			break;
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

	protected double check_balance(String accountID) {
		// TODO Auto-generated method stub
		for (int i = 0; i < Customer.customer_count; i++) {
			if (accountID.equals(Customer.customer[i].account.getAccountID())) {
				double balance = Customer.customer[i].account.getBalance();
				return balance;
			}
		}
		System.out.println(" Account Doesn't Exist!");
		return 0;
	}

	boolean validate_PIN(String id, String pin) {
		int i = array_index(id);
		if (pin.equals(Customer.customer[i].account.getPINcode())) {
			return true;
		} else
			return false;
	}

	public String getAccountID() {
		return AccountID;
	}

	public void setAccountID(String accountID) {
		AccountID = accountID;
	}

	public String getPINcode() {
		return PINcode;
	}

	public void setPINcode(String pINcode) {
		PINcode = pINcode;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double d) {
		this.balance = d;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public static int getTotal_customer_count() {
		return total_customer_count;
	}

	public static void setTotal_customer_count(int total_customer_count) {
		Account.total_customer_count = total_customer_count;
	}

	public String getExisting_status() {
		return existing_status;
	}

	public void setExisting_status(String existing_status) {
		this.existing_status = existing_status;
	}

	public Date getDelete_date() {
		return delete_date;
	}

	public void setDelete_date(Date delete_date) {
		this.delete_date = delete_date;
	}

}
