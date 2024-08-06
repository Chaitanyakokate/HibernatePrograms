package in.pwskills.nitin.main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.Employee;
import in.pwskills.nitin.util.HibernateUtil;

public class CollectionMappingInsertApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			Employee employee = new Employee("sachin", "IND", List.of("saurav", "dravid", "sehwagh"));
			session.save(employee);

			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record saved to database...");
			} else {
				transaction.rollback();
				System.out.println("Record not saved to database...");
			}
		}

	}
}
