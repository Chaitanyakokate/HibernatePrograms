package in.pwskills.chaitanya.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sid;
	
	@Column(name = "SNAME")
	private String sname;
	
	@Column(name = "SADDRESS")
	private String saddrerss;
	
	@Column(name = "SAGE")
	private Integer sage;
	static {
		System.out.println("Student.class file loaded");
	}
	public Student() {
		// TODO Auto-generated constructor stub
		System.out.println("Student object:: zero param constructor");
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSaddrerss() {
		return saddrerss;
	}
	public void setSaddrerss(String saddrerss) {
		this.saddrerss = saddrerss;
	}
	public Integer getSage() {
		return sage;
	}
	public void setSage(Integer sage) {
		this.sage = sage;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", saddrerss=" + saddrerss + ", sage=" + sage + "]";
	}
	
}
