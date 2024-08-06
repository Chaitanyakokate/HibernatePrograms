package in.pwskills.chaitanya.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import in.pwskills.chaitanya.bean.Student;
import in.pwskills.chaitanya.util.HibernateUtil;

public class SaveOrUpdateApp {
	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		Student student = null;
		Integer id = null;
		BufferedReader br = null;
		String name = null, address = null, age = null;
		Boolean flag = false;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			session = HibernateUtil.getSession();

			if (session != null && br != null) {
				System.out.println("Enter the value of ID :: ");
				id = Integer.parseInt(br.readLine());

				student = session.get(Student.class, id);
			}

			if (student != null) {
				transaction = session.beginTransaction();

				if (transaction != null) {
					System.out.println("Records of id :: " + id);

					System.out.println("OldName is :: " + student.getSname() + " Enter new Name ::");
					name = br.readLine();

					if (name.equals("") || name == null) {
						student.setSname(student.getSname());
					} else {
						student.setSname(name);
					}

					System.out.print("OldAddress is :: " + student.getSaddrerss() + "  Enter new Address :: ");
					address = br.readLine();
					if (address.equals("") || address == null) {
						student.setSaddrerss(student.getSaddrerss());
					} else {
						student.setSaddrerss(address);
					}

					System.out.print("OldAge  is :: " + student.getSage() + "  Enter new Age :: ");
					age = br.readLine();

					if (age.equals("") || age == null) {
						student.setSage(student.getSage());
					} else {
						student.setSage(Integer.parseInt(age));
					}

					session.saveOrUpdate(student);

					flag = true;

				}
			} else {
				student = new Student();
				student.setSname("Rahul");
				student.setSaddrerss("LSG");
				student.setSage(32);

				transaction = session.beginTransaction();
				session.saveOrUpdate(student);

				flag = true;
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record updated succesfully...");
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
