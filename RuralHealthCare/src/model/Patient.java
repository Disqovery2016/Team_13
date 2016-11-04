package model;

public class Patient {
private long patientID;
private String username;
private int age;
private String address;
private int password;
private String email;
private int phoneN;

boolean loggedIn=true;

public Patient(long patientID, String name, int age, String address, int pasword, String email, int phoneN) {
	super();
	this.patientID = patientID;
	this.username = name;
	this.age = age;
	this.address = address;
	this.password = pasword;
	this.email = email;
	this.phoneN = phoneN;
}
public long getPatientID() {
	return patientID;
}
public void setPatientID(long patientID) {
	this.patientID = patientID;
}
public String getName() {
	return username;
}
public void setName(String name) {
	this.username = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getPasword() {
	return password;
}
public void setPasword(int pasword) {
	this.password = pasword;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getPhoneN() {
	return phoneN;
}
public void setPhoneN(int phoneN) {
	this.phoneN = phoneN;
}

public void logOut(){
	loggedIn=false;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + (int) (patientID ^ (patientID >>> 32));
	result = prime * result + phoneN;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Patient other = (Patient) obj;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (patientID != other.patientID)
		return false;
	if (phoneN != other.phoneN)
		return false;
	return true;
}
@Override
public String toString() {
	return patientID+" "+age+" "+address+" "+username+" "+phoneN;
}



}
