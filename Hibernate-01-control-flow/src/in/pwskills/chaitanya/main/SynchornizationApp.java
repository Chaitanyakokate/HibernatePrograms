package in.pwskills.chaitanya.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import in.pwskills.chaitanya.bean.Student;
import in.pwskills.chaitanya.util.HibernateUtil;

public class SynchornizationApp {

	public static void main(String[] args) {

		Session session = null;
		Student student = null;

		try {
			session = HibernateUtil.getSession();

			student = session.get(Student.class, 3);
			System.out.println("Before modification.....");
			System.out.println("Record with the id value :: " + student);
			
			//application is in pausing state
			System.in.read();

			//synchronization established b/w record to java object
			session.refresh(student);
			
			System.out.println("After modification....");
			System.out.println("Record with the id value :: "+student);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
			if (session != null) {
				session.close();
			}
		}

	}

}
