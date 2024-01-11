import java.util.Date;

public class Transaction {

	protected Transaction[] transaction_records = new Transaction[500];
	protected String Transaction_type;
	protected int transaction_count=0;
	protected double transaction_amount=0;
	int zakat_count=0;
	Date transaction_date;	
	
	
	/*			
	/*
	 * protected int deposit_transaction_count; protected int
	 * withdraw_transaction_count; protected int transfer_transaction_count;
	 * protected int received_transaction_count; protected int[]
	 * deposit_transaction_record = new int[500]; protected int[]
	 * withdraw_transaction_record = new int[500]; protected int[]
	 * transfer_transaction_record = new int[500]; protected int[]
	 * received_transaction_record = new int[500]; protected String[] payee_ID = new
	 * String[2000]; protected String[] receiver_ID = new String[2000];
	 */

	/*public int getReceived_transaction_count() {
		return received_transaction_count;
	}

	public void setReceived_transaction_count(int received_transaction_count) {
		this.received_transaction_count = received_transaction_count;
	}

	public int getDeposit_transaction_count() {
		return deposit_transaction_count;
	}

	public void setDeposit_transaction_count(int deposit_transaction_count) {
		this.deposit_transaction_count = deposit_transaction_count;
	}

	public int getWithdraw_transaction_count() {
		return withdraw_transaction_count;
	}

	public void setWithdraw_transaction_count(int withdraw_transaction_count) {
		this.withdraw_transaction_count = withdraw_transaction_count;
	}

	public int getTransfer_transaction_count() {
		return transfer_transaction_count;
	}

	public void setTransfer_transaction_count(int transfer_transaction_count) {
		this.transfer_transaction_count = transfer_transaction_count;
	}*/

}
