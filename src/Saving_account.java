import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Saving_account extends Account {
	private int zakat;
	private double interest_rate = 0.025;
	static Scanner input = new Scanner(System.in);
	static DecimalFormat ft = new DecimalFormat("#.##");

	public double getInterestrate() {
		return interest_rate;
	}

	public void setInterestrate() {
		System.out.println(" Enter a interest rate");
		double interest=input.nextDouble();
		this.interest_rate=interest;
		System.out.println("New Interest Rate Specified Successfully.\n");
	}

	public void add_interest(String AccountID, int withdraw) {
		int i = array_index(AccountID);
		{
			double interest = interest_rate * withdraw;
			Customer.customer[i].account.setBalance((int) (Customer.customer[i].account.getBalance() - interest));
			Customer.customer[i].T.transaction_records[tr.transaction_count].Transaction_type = "Interest";
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_amount = (int) interest;
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_date = Calendar.getInstance()
					.getTime();
		}
	}

	public boolean withdraw_balance(String accountID) {
		int i = array_index(accountID);
		System.out.println(" Please Input the amount you wish to Withdraw!");
		int withdraw = input.nextInt();
		if (withdraw < Customer.customer[i].account.balance) {
			add_interest(accountID, withdraw);
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

	public double calculate_zakat(String accountID) {
		int i = array_index(accountID);
		if (Customer.customer[i].T.zakat_count == 1) {
			System.out.println(" You have already paid zakat");
			return 0;
		} else if (check_balance(accountID) > 20000) {
			double zakat = ((Customer.customer[i].account.getBalance() * 25.00) / 10.00);
			System.out.println("Zakat :" + zakat);
			Customer.customer[i].T.zakat_count ++;
			return zakat;
		} else
			zakat = 0;
		return zakat;
	}

	void pay_zakat(String accountId) {
		int i = array_index(accountId);
		if (Customer.customer[i].T.zakat_count == 0) {
		double zakat = calculate_zakat(accountId);
		Customer.customer[i].account.setBalance(Customer.customer[i].account.getBalance() - zakat);
		System.out.println(" Zakat Paid!");
		Customer.customer[i].T.transaction_records[tr.transaction_count].Transaction_type = "Zakat";
		Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_amount = zakat;
		Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_date = Calendar.getInstance()
				.getTime();
		Customer.customer[i].T.transaction_count++;
		
	}
	}
}
