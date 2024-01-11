
public class User {
	private String name;
	private String Phone_no;
	private String CNIC;
	private String Address;
	protected Account account;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_no() {
		return Phone_no;
	}

	public void setPhone_no(String phone_no) {
		Phone_no = phone_no;
	}

	public String getCNIC() {
		return CNIC;
	}

	public void setCNIC(String cNIC) {
		CNIC = cNIC;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
