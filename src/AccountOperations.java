
public interface AccountOperations {

	
	boolean transfer_amount(String accountID);
	boolean withdraw_balance(String accountID);
	void bank_statement(String accountID);
	boolean deposit_amount(String accountID);
	
}
