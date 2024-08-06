package in.pwskills.chaitanya.main;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import in.pwskills.chaitanya.bean.Student;
import in.pwskills.chaitanya.util.HibernateUtil;


public class SavePresistApp {
	public static void main(String[] args) {
		Transaction transaction = null;
		Session session = null;
		boolean flag = false;
		
		try {
			session = HibernateUtil.getSession();
			if (session != null) {
				transaction = session.beginTransaction();
				Student student = new Student();
				
				student.setSname("Dhoni");
				student.setSage(41);
				student.setSaddrerss("CSK");
				
				session.save(student);
				
				flag = true;
			}
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				System.in.read();
				if(flag) {
					transaction.commit();
					System.out.println("Object saved into database");
				}
				else {
					transaction.rollback();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			if (session != null) {
				session.close();
			}
		}
	}
}
