package in.pwskills.nitin.main;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.BankAccount;
import in.pwskills.nitin.util.HibernateUtil;

public class SaveOrUpdateApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		BankAccount account = null;

		Boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			account = new BankAccount();

			account.setHolderName("Dhoni");
			account.setBalance(654466.23);
			account.setType("Current");

			session.save(account);

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
					System.out.println("Record inserted/updated succesfully...");

					System.out.println("Account is opened   on :: " + account.getOpeningDate());
					System.out.println("Account is modified on :: " + account.getLastUpdate());
					System.out.println("Account version is     :: " + account.getVersionCount());
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
