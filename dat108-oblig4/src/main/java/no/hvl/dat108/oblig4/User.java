package no.hvl.dat108.oblig4;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "users", schema = "fest")
@Table(schema = "fest") // Må klassen hete det samme som tabellen? 
//@NamedQuery(name = "findAllUsers", query ="SELECT a FROM users a")
public class User {
	
	public static HashUtil hashUtil = new HashUtil();
	
	private String firstname;
	private String lastname;
	@Id
	private String cell;
	private String hash;
	private String salt;
	private String sex;

	public User() {}
	
	private User(String firstname, String lastname, String cell, String hash, String salt, String sex) {
		// TODO Consider making enum, look into cell phone changes
		if(!sex.equals("m") && !sex.equals("f")) throw new IllegalArgumentException("Sex must be m or f.");
		if(!cell.matches("^\\d{8}$")) throw new IllegalArgumentException("Cell must be 8 digits.");
		this.firstname = firstname;
		this.lastname = lastname;
		this.cell = cell;
		this.hash = hash;
		this.salt = salt;
		this.sex = sex;
	}
	
	public static User createNewFromPassword(String firstname, String lastname, String cell, String password, String sex) {
		String salt = hashUtil.genSalt();
		String hash = hashUtil.hash(password, salt);
		return new User(firstname, lastname, cell, hash, salt, sex);
	}

	public String getCell() {
		return cell;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getSex() {
		return sex;
	}
	
	public boolean checkPassword(String password) {
		return hashUtil.check(password, salt, hash);
	}
	
	public String toString() {
		return String.format("('%s', '%s', '%s', '%s', '%s', '%s')", firstname, lastname, cell, hash, salt, sex);
	}
}
