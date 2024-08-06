package in.pwskills.nitin.main;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.BankAccount;
import in.pwskills.nitin.util.HibernateUtil;

public class SoftDeletionApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			if (session != null) {
				transaction = session.beginTransaction();
				BankAccount bank = new BankAccount();
				bank.setAccNo(1);
				session.delete(bank);
				flag = true;
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if (flag) {
				transaction.commit();
				System.out.println("Account closed");
			}else {
				System.out.println();
			}
			HibernateUtil.closeSessionFactory();
		}
	}

}
