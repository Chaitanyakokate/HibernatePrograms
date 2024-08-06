package in.pwskills.nitin.main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import in.pwskills.nitin.bean.Products;
import in.pwskills.nitin.util.HibernateUtil;

public class NativeQueryApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;

		try {

			session = HibernateUtil.getSession();
			NativeQuery<Products> query = session
					.createSQLQuery("CALL `GET_PRODUCT_DETAILS_BY_NAME`(:product1,:product2)");
			query.addEntity(Products.class);
			query.setParameter("product1", "fossil");
			query.setParameter("product2", "armani");

			List<Products> list = query.getResultList();
			list.forEach(System.out::println);
			
			
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
