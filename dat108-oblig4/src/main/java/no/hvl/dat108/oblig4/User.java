package no.hvl.dat108.oblig4;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import no.hvl.dat108.oblig4.helpers.IHasPrimaryKey;

@Entity
@Table(name = "users")
public class User implements IHasPrimaryKey {
	
	public static HashUtil hashUtil = new HashUtil();
	
	private String firstname;
	private String lastname;
	@Id
	private String cell;
	private String hash;
	private String salt;
	private String sex;

	public User() {}
	
	//We could consider using an enum for sex, but we decided to use Strings (m / f) instead.
	private User(String firstname, String lastname, String cell, String hash, String salt, String sex) {
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

	@Override
	public Object getPrimaryKey() {
		return getCell();
	}
}
