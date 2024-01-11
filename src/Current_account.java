import java.util.Calendar;

public class Current_account extends Account {

	public boolean deposit_amount(String accountID) {
		transaction_fee(accountID);
		if (check_account_status(accountID) == true) {
			int i = array_index(accountID);
			
			System.out.println(" Please Input the amount you wish to deposit!");
			int deposit = input.nextInt();

			Customer.customer[i].account.setBalance(Customer.customer[i].account.getBalance() + deposit);
			Customer.customer[i].T.transaction_records[tr.transaction_count].Transaction_type = "deposit";
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_amount = deposit;
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_date = Calendar.getInstance()
					.getTime();
			Customer.customer[i].T.transaction_count++;
			System.out.println(" Amount deposited Succesfully!");
			return true;
		}
		return false;
	}
	
	public void transaction_fee(String AccountID) {
		int i=array_index(AccountID);
		if (Customer.customer[i].T.transaction_count > 2) {
			Customer.customer[i].account.setBalance(Customer.customer[i].account.getBalance() - 10);
			Customer.customer[i].T.transaction_records[tr.transaction_count].Transaction_type = "Tax";
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_amount = 10;
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_date = Calendar
					.getInstance().getTime();
		}
	}
	@Override
	public boolean withdraw_balance(String accountID) {
		transaction_fee(accountID);
		int i=array_index(accountID);
		System.out.println(" Please Input the amount you wish to Withdraw!");
		int withdraw = input.nextInt();
		if(withdraw<Customer.customer[i].account.balance ) {
			Customer.customer[i].account.balance -= withdraw;
			Customer.customer[i].T.transaction_records[tr.transaction_count].Transaction_type = "Withdraw";
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_amount = withdraw;
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_date = Calendar
					.getInstance().getTime();
			Customer.customer[i].T.transaction_count++;
			System.out.println(" Amount withdrawn Succesfully!");
			return true;
		}else if(withdraw<Customer.customer[i].account.balance+5000 ) {
			
			Customer.customer[i].T.transaction_records[tr.transaction_count].Transaction_type = "Loan";
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_amount = withdraw-Customer.customer[i].account.balance;
			Customer.customer[i].account.balance -= withdraw;
			Customer.customer[i].T.transaction_records[tr.transaction_count].Transaction_type = "Withdraw";
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_amount = withdraw;
			Customer.customer[i].T.transaction_records[tr.transaction_count].transaction_date = Calendar
					.getInstance().getTime();
			Customer.customer[i].T.transaction_count++;
			System.out.println(" Amount withdrawn Succesfully!");
			return true;
		}
		else
		return false;
	}
	
	
	
	
	
}
