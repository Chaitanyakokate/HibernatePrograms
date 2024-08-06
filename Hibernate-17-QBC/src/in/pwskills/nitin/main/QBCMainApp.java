package in.pwskills.nitin.main;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import in.pwskills.nitin.bean.Project;
import in.pwskills.nitin.util.HibernateUtil;

public class QBCMainApp {

	private static Root<Project> from;

	public static void main(String[] args) {
		
		Session session = null;
		
		/*
		 * // QBC : HQL : SQL -> select ... from PROJECT_QBC try { session =
		 * HibernateUtil.getSession();
		 * 
		 * // Create a builder object CriteriaBuilder builder =
		 * session.getCriteriaBuilder();
		 * 
		 * // Create a CriteriaQuery object CriteriaQuery<Project> cQuery =
		 * builder.createQuery(Project.class);
		 * 
		 * Root<Project> root = cQuery.from(Project.class);
		 * 
		 * // adding root object to cquery object cQuery.select(root);
		 * 
		 * // prepare a query object having CQuery Query<Project> query =
		 * session.createQuery(cQuery);
		 * 
		 * // execute JPA QBC logic List<Project> list = query.getResultList();
		 * list.forEach(System.out::println);
		 * 
		 * } catch (HibernateException he) { he.printStackTrace(); } finally {
		 * HibernateUtil.closeSession(session); HibernateUtil.closeSessionFactory(); }
		 */
		
		/*
		try {
			session = HibernateUtil.getSession();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Project> cquery = builder.createQuery(Project.class);
			
			Root<Project> root = cquery.from(Project.class);
			
			cquery.select(root);
			
			Query<Project> query = session.createQuery(cquery);
			List<Project> list = query.getResultList();
			
			list.forEach(System.out::println);
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
			
		}
		*/
		/*
		try {
			session = HibernateUtil.getSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Project> cquery = builder.createQuery(Project.class);
			
			Root<Project> root = cquery.from(Project.class);
			cquery.select(root)
			      .where(builder.and(builder.ge(root.get("projId"), 1l),builder.le(root.get("projId"), 3l)))
			      .orderBy(builder.desc(root.get("projName")));
			/*
			ParameterExpression<Long> param1 = builder.parameter(Long.class);
			ParameterExpression<Long> param2 = builder.parameter(Long.class);
			
			Predicate p1 =  builder.ge(root.get("projId"),param1);
			Predicate p2 = builder.le(root.get("projId"),param2);
			Predicate finalCond = builder.and(p1,p2);
			
			cquery.where(finalCond);
			
			Order order = builder.desc(root.get("projName"));
			cquery.orderBy(order);
			//*
			
			Query<Project> query = session.createQuery(cquery);
			List<Project> list = query.getResultList();
			list.forEach(System.out::println);
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
		*/

		/*
		try {
			session = HibernateUtil.getSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Project> cquery = builder.createQuery(Project.class);
			
			Root<Project> root = cquery.from(Project.class);
			cquery.select(root)
			      .where(root.get("location").in("Pune","Mumbai"))
			      .orderBy(builder.desc(root.get("projName")));
			
			
			Query<Project> query = session.createQuery(cquery);
			List<Project> list = query.getResultList();
			list.forEach(System.out::println);
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
		*/
		
		try {
			session = HibernateUtil.getSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Project> cquery = builder.createQuery(Project.class);
			
			Root<Project> root = cquery.from(Project.class);
			cquery.select(root)
			      .where(builder.between(root.get("teamSize"), 10, 20)) 
			      .orderBy(builder.desc(root.get("projName")));
			
			
			Query<Project> query = session.createQuery(cquery);
			List<Project> list = query.getResultList();
			list.forEach(System.out::println);
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
