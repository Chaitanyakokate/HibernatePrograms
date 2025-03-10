package in.pwskills.nitin.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.Address;
import in.pwskills.nitin.bean.StudentInfo;
import in.pwskills.nitin.util.HibernateUtil;

public class ComponentMappingInsertApp {
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		Boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			Address address = new Address("#2/2", "FoodStreet", "VJYNGR", "BENGALURU", "IND", 560026L);
			
			StudentInfo student = new StudentInfo("Nitin", 35.5f, address);
			
			session.save(student);
			flag = true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record inserted succesfully");
			}else {
				transaction.rollback();
				System.out.println("Record insertion fail");
			}
			HibernateUtil.closeSessionFactory();
		}
	}
}
