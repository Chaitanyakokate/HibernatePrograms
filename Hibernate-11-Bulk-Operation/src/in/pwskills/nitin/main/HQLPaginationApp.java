package in.pwskills.nitin.main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;

import in.pwskills.nitin.bean.InsurancePolicy;
import in.pwskills.nitin.util.HibernateUtil;

public class HQLPaginationApp {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		Query<InsurancePolicy> query = null;
		List<InsurancePolicy> listOfRecords = null;
		try {
			session = HibernateUtil.getSession();
			query = session.createQuery("FROM in.pwskills.nitin.bean.InsurancePolicy");
			
			query.setFirstResult(0);
			query.setMaxResults(2);
			
			listOfRecords = query.getResultList();
			listOfRecords.forEach(System.out::println);
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
