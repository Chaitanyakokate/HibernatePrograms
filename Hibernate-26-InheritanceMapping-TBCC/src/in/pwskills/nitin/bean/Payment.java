package in.pwskills.nitin.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer pid;
	private Float amount;

	static {
		System.out.println("Payment.class file is loading...");
	}

	public Payment() {
		System.out.println("Hibernate creating an object of Payment...");
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment [pid=" + pid + ", amount=" + amount + "]";
	}
}
