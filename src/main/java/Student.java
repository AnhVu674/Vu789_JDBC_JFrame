
public class Student {
private int id;
private String fullName;
private String email;
private String address;
public Student(int id, String fullName, String email, String address) {
	this.id = id;
	this.fullName = fullName;
	this.email = email;
	this.address = address;
}

public Student(String fullName, String email, String address) {
	this.fullName = fullName;
	this.email = email;
	this.address = address;
}

public Student() {
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAdress() {
	return address;
}
public void setAdress(String adress) {
	this.address = adress;
}
@Override
public String toString() {
	return "Student [id=" + id + ", fullName=" + fullName + ", email=" + email + ", adress=" + address + "]";
}



}
