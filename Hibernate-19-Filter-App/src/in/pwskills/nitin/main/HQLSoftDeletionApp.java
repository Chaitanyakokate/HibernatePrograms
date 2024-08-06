package in.pwskills.nitin.main;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.pwskills.nitin.bean.BankAccount;
import in.pwskills.nitin.util.HibernateUtil;

public class HQLSoftDeletionApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Boolean flag = false;
		try {
			session = HibernateUtil.getSession();
				transaction = session.beginTransaction();
				@SuppressWarnings("unchecked")
				Query<BankAccount> query = session.createQuery("update BankAccount set status='close' where accNo=:no");
				query.setParameter("no", 2);
				query.executeUpdate();
				flag = true;
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			if (flag) {
				transaction.commit();
				System.out.println("Account closed");
			}else {
				System.out.println("Account not close");
			}
			HibernateUtil.closeSessionFactory();
		}
	}

}
