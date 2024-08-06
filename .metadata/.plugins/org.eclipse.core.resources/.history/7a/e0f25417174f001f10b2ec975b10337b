package in.pwskills.nitin.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import in.pwskills.nitin.bean.CardPayment;
import in.pwskills.nitin.bean.ChequePayment;
import in.pwskills.nitin.bean.Payment;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	static {
		if (sessionFactory == null) {
			sessionFactory = new Configuration()
							.configure()
							.addAnnotatedClass(Payment.class)
							.addAnnotatedClass(CardPayment.class)
							.addAnnotatedClass(ChequePayment.class)
							.buildSessionFactory();
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		return sessionFactory;
	}
	public static Session getSession() {
		if (sessionFactory != null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
