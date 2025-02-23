package in.pwskills.nitin.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.pwskills.nitin.bean.InsurancePolicy;
import in.pwskills.nitin.util.HibernateUtil;

public class RetrieveRecordApp {

	public static void main(String[] args) {

		Session session = null;
		InsurancePolicy policy = null;
		try {
			session = HibernateUtil.getSession();

			policy = session.get(InsurancePolicy.class, 3L);// gets from DB put in L2 and L1
			System.out.println(policy);

			System.out.println("-----------------");

			policy = session.get(InsurancePolicy.class, 3L);// get from L1
			System.out.println(policy);

			session.clear();// clearing L1 cache memory

			policy = session.get(InsurancePolicy.class, 3L);// get from L2 and keep it in L1 cache
			System.out.println(policy);

			session.clear();// clearing L1 cache memory
			try {
				Thread.sleep(20000);// 20 sec :: removed object from L2 cache
			} catch (Exception e) {
			}

			System.out.println("-----------------------");
			policy = session.get(InsurancePolicy.class, 3L);// get from db, keep in L2 and L1 cache
			System.out.println(policy);
		} catch (HibernateException he) {
			he.printStackTrace();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}

}
