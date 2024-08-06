package in.pwskills.nitin.main;

import java.io.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.MobileCustomer;
import in.pwskills.nitin.util.HibernateUtil;

public class LoadApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		MobileCustomer customer = null;
		boolean flag = false;

		try {
			session = HibernateUtil.getSession();

			int id = 1;
			customer = session.get(MobileCustomer.class, id);

			if (customer != null) {
				System.out.println(customer);

				transaction = session.beginTransaction();
				customer.setCallerTune("Wistle podu");
				flag = true;
			}

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
