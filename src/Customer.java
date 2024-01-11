
public class Customer extends User {

	
	
	static int customer_count = 0;
	static int deleted_customer_count=0;
	static Customer[] customer = new Customer[500];
	protected Transaction T;
	
	public Customer(String name, String phone_no, String cNIC, String address, Account account) {
		super();
		super.setName(name);
		setPhone_no(phone_no);
		setCNIC(cNIC);
		setAddress(address);
		super.setAccount(account);
	}

	public Customer() {

	}

	

}
