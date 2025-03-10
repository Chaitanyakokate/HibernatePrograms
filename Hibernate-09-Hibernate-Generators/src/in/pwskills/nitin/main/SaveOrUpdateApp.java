package in.pwskills.nitin.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.PersonInfo;
import in.pwskills.nitin.util.HibernateUtil;

public class SaveOrUpdateApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;

		Boolean flag = false;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			PersonInfo info = new PersonInfo();
			
			info.setId(7);
			info.setPaddr("CSK");
			info.setPname("Dhoni");
			
			info.setDob(LocalDateTime.of(1996, 2,24,13,25));
			info.setDom(LocalDate.of(2021, 10, 30));
			info.setDoj(LocalTime.of(8, 40, 54));
			
			session.save(info);
			flag = true;
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (transaction != null) {
				if (flag) {
					transaction.commit();
					System.out.println("Record inserted/updated succesfully...");
				} else {
					transaction.rollback();
					System.out.println("Record failed for updation...");
				}

				HibernateUtil.closeSessionFactory();
				if (session != null) {
					session.close();
				}
			}
		}
	}
}
