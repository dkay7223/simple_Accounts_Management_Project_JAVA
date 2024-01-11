import java.util.Scanner;

public class UserInterface {
	
	static String userId;
	static Scanner input = new Scanner(System.in);
	Customer c=new Customer();
	Saving_account s=new Saving_account();
	Current_account ch=new Current_account();
	Manager m=new Manager();
	private void login_screen()  {
		// TODO Auto-generated method stub
		
		System.out.println(" Hello user, Please Welcome to JS Bank!");
		System.out.println(" Press 1: To login as a Manager");
		System.out.println(" Press 2: To login as a customer");
		System.out.println(" Press 3: To Signup as a Customer");
		System.out.println(" Press 0: To Exit!");
		int choice=input.nextInt();
		switch(choice) {
		case 1:
			manager_login();login_screen();
		case 2:
			login_customer();
		case 3:
			m.create_account();login_screen();
		case 0:
			System.exit(0);
			default:
				login_screen();
		}
	}
	private boolean manager_login() {
		System.out.println(" Enter your password:");
		String passcode=input.next();
		if (passcode.equals(m.password)) {
			manager_menu();return true;
		}else 
			System.out.println(" Incorrect password!");return false;

	}
		
	private void manager_menu() {
		Manager m=new Manager();
		// TODO Auto-generated method stub
		System.out.println("\t\t         MAIN MENU       ");
		System.out.println("\t\t ------------------------");
		System.out.println("\t\t|1. Create a New Account   |");
		System.out.println("\t\t|2. Delete an account     |");
		System.out.println("\t\t|3. Specify Interest Rate|"); 
		System.out.println("\t\t|4. Print Your Details  |");
		System.out.println("\t\t|5. Print Account Details |");
		System.out.println("\t\t|6. View Transactions|");
		System.out.println("\t\t|7. View All Accounts|");
		System.out.println("\t\t|8. Forget PIN|");
		System.out.println("\t\t|9. Exit                 |");
		System.out.println("\t\t ------------------------");
		System.out.println("Enter your choice : ");
		String choice=input.next();
		switch(choice) {
		case "1":
			m.create_account();manager_menu();
		case "2":
			m.delete_account();manager_menu();
		case "3":
			s.setInterestrate();
		case "4":
			m.manager_details();
		case "5":
			System.out.println(" Enter the account you wish to see!");
			String id=input.next();
			m.view_account_details(id);manager_menu();
		case "6":
			for(int j=0;j<Customer.customer_count;j++) {
				s.bank_statement(Customer.customer[j].account.getAccountID());manager_menu();
			}
		case "7":
			m.viewAllCustomers();manager_menu();
		case "8":
			m.forget_PIN();manager_menu();
		case "9":
			login_screen();
			default:
				login_screen();}
	}
	
	String  login_customer(){
		System.out.println(" Enter you ID");
		String Id=input.next();
		int index=s.array_index(Id);
		System.out.println(" Enter PIN!");
		String pin=input.next();
		{
			if(pin.equals(Customer.customer[index].account.getPINcode())) {
				System.out.println(" Succesfully logged in!");
				String type=Customer.customer[index].account.getAccount_type();
				if(type.equalsIgnoreCase("Current")) {
					Current_menu(Id);
				}else if(type.equalsIgnoreCase("Savings")) {
					Savings_menu(Id);
				}
			}
		}return Id;
	}
	void Savings_menu(String account) {
		
		System.out.println("\n");
		System.out.println("-------------WELCOME TO YOUR SAVING ACCOUNT------------");
		System.out.println("|                  1.  Deposit Amount                 |");
		System.out.println("|                  2.  Withdraw Amount                |");
		System.out.println("|                  3.  Transfer Amount                |"); 
		System.out.println("|                  4.  Check Balance                  |"); 
		System.out.println("|                  5.  Print Statement                |"); 
		System.out.println("|                  6.  Calculate Zakat                |"); 
		System.out.println("|                  7.  Calculate Interest             |"); 
		System.out.println("|                  8.  Display Deductions             |"); 
		System.out.println("|                  9.  Display Transactions           |"); 
		System.out.println("|                  10. Exit to Main Menu              |"); 
		System.out.println(" -----------------------------------------------------");
		
		System.out.println("Enter your choice : ");
		int choice=input.nextInt();
		
		switch(choice) {
		case 1:{
			s.deposit_amount(account);
			break;}
		case 2:{
			s.withdraw_balance(account);
			break;}
		case 3:{
			s.transfer_amount(account);
			break;}
		case 4:{
			s.check_balance(account);
			break;}
		case 5:{
			s.bank_statement(account);
			break;}
		case 6:{
			s.calculate_zakat(account);
			break;}
		case 7:{
			s.setInterestrate();
			break;}	
		case 8:{
			s.bank_statement(account);
			break;}	
		case 9:{
			s.bank_statement(account);
			break;
		}
		case 10: {
			login_screen();
			break;
		}
		default:{
			System.out.println("Invalid Choice!");Savings_menu(account);
			break;
		}
		}	
	}
	void Current_menu(String account) {
		
		System.out.println("\n");
		System.out.println("--------------WELCOME TO YOUR CHECKING ACCOUNT------------");
		System.out.println("|                   1.  Deposit Amount                    |");
		System.out.println("|                   2.  Withdraw Amount                   |");
		System.out.println("|                   3.  Transfer Amount                   |"); 
		System.out.println("|                   4.  Check Balance                     |"); 
		System.out.println("|                   5.  Print Statement                   |"); 	
		System.out.println("|                   6.  Display Deductions                |"); 
		System.out.println("|                   7.  Display Transactions              |"); 
		System.out.println("|                   8.  Exit to Main Menu                 |"); 
		System.out.println("|                   9.  Change Credentials                 |"); 
		System.out.println(" ---------------------------------------------------------");
		
		System.out.println("Enter your choice : ");
		int choice=input.nextInt();
		
		switch(choice) {
		case 1:{
			ch.deposit_amount(account);
			break;}
		case 2:{
			ch.withdraw_balance(account);
			break;}
		case 3:{
			ch.transfer_amount(account);
			break;}
		case 4:{
			ch.check_balance(account);}
		case 5:{
			ch.bank_statement(account);
			break;}
		case 6:{
			ch.bank_statement(account);
			break;}
		case 7:{
			ch.bank_statement(account);
			break;}	
		case 8:{
			ch.bank_statement(account);
			break;}	
		case 9:{
			ch.change_credentials(account);
			break;
		}
		
		default:{
			System.out.println("Invalid Choice!");Current_menu(account);
			break;
		}
		}	
	}
	
	public static void main(String[] args) {
		UserInterface m=new UserInterface();
		m.login_screen();
	}
	
}
