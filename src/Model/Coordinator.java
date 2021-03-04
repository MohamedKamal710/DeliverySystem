package Model;

import java.io.Serializable;
import java.util.Date;

public class Coordinator extends Person implements Serializable{

	private String password ;
	public Coordinator(long id , String firstName , String lastName) {
		super(id,firstName,lastName);
		
	}

	@Override
	public String toString() {
		return "[ Name : " + this.getFirstName() + " " + this.getSurname() + " , ID : " + this.getId() + " ]";
	}

	
	

}
