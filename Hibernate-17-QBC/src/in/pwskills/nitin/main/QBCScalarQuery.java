package in.pwskills.nitin.main;

import java.util.List;
import java.util.Optional;

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

public class QBCScalarQuery {

	private static Root<Project> from;

	public static void main(String[] args) {
		
		Session session = null;
		
		/*
		try {
			session = HibernateUtil.getSession();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Project> cquery = builder.createQuery(Project.class);
			
			Root<Project> root = cquery.from(Project.class);
			
			cquery.multiselect(root.get("projName"))
			      .where(builder.between(root.get("cost"), 20000, 30000));
			
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
			CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
			Root<Project> root = criteriaQuery.from(Project.class);

			criteriaQuery.multiselect(builder.count(root.get("projId")));

			Query<Long> query = session.createQuery(criteriaQuery);
			Optional<Long> optional = query.uniqueResultOptional();
			long count = optional.isPresent() ? optional.get() : 0;
			System.out.println("No of records is :: " + count);
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
			
		}
		
	}

}
