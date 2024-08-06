package in.pwskills.nitin.main;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.MobileCustomer;
import in.pwskills.nitin.util.HibernateUtil;

public class SaveOrUpdateApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;

		Boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			MobileCustomer mobileCustomer = session.get(MobileCustomer.class, 1);
			
			mobileCustomer.setCname("CSK");
			mobileCustomer.setCallerTune("CSK..CSk..");
			mobileCustomer.setMobileNo(9988554466l);
			
			session.saveOrUpdate(mobileCustomer);
			
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
