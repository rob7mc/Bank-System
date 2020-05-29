package beans;

public class Accounts {
	private String TransactionID;
	private String AccountNumber;
	private String TransactionDate;
	private String Details;
	private String Debit;
	private String Credit;
	private String Balance;

	public Accounts(String transactionID, String accountNumber, String transactionDate, String details, String debit, String credit, String balance) {
		super();
		TransactionID = transactionID;
		AccountNumber = accountNumber;
		TransactionDate = transactionDate;
		Details = details;
		Debit = debit;
		Credit = credit;
		Balance = balance;
	}
	
	public Accounts() {}

	public String getTransactionID() {return TransactionID;}
	public void setTransactionID(String transactionID) {TransactionID = transactionID;}

	public String getAccountNumber() {return AccountNumber;}
	public void setAccountNumber(String accountNumber) {AccountNumber = accountNumber;}

	public String getTransactionDate() {return TransactionDate;}
	public void setTransactionDate(String transactionDate) {TransactionDate = transactionDate;}

	public String getDetails() {return Details;}
	public void setDetails(String details) {Details = details;}

	public String getDebit() {return Debit;}
	public void setDebit(String debit) {Debit = debit;}

	public String getCredit() {return Credit;}
	public void setCredit(String credit) {Credit = credit;}

	public String getBalance() {return Balance;}
	public void setBalance(String balance) {Balance = balance;}
}
