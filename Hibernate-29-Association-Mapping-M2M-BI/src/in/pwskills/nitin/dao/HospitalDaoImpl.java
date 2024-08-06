package in.pwskills.nitin.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import in.pwskills.nitin.bean.Doctor;
import in.pwskills.nitin.bean.Patient;
import in.pwskills.nitin.util.HibernateUtil;

public class HospitalDaoImpl implements IHospitalDao {

	@Override
	public void saveRecordUsingParent() {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			//Created parent object
			Doctor doctor1 = new Doctor();
			doctor1.setDoctName("karthik");
			doctor1.setHospital("Apollo");
			
			Doctor doctor2 = new Doctor();
			doctor2.setDoctName("Ajay");
			doctor2.setHospital("Fortis");
			
			Doctor doctor3 = new Doctor();
			doctor3.setDoctName("savan");
			doctor3.setHospital("Victoria");

			//Created child objects
			Patient patient1 = new Patient();
			patient1.setPatName("suresh");
			patient1.setProblem("heart");
			
			Patient patient2 = new Patient();
			patient2.setPatName("dinesh");
			patient2.setProblem("kidney");
			
			Patient patient3 = new Patient();
			patient3.setPatName("ramesh");
			patient3.setProblem("covid");
			
			//linking child to parent
			doctor1.setPatients(Set.of(patient1, patient3));
			doctor2.setPatients(Set.of(patient3, patient2));
			doctor3.setPatients(Set.of(patient2, patient1));
			
			//linking parent to childs
			patient1.setDoctor(Set.of(doctor1, doctor3));
			patient2.setDoctor(Set.of(doctor2, doctor1));
			patient3.setDoctor(Set.of(doctor3));
			
			session.save(doctor1);
			session.save(doctor2);
			session.save(doctor3);
			
			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (transaction != null) {
				if (flag) {
					transaction.commit();
					System.out.println("Record saved to database using Parent(Doctors)...");
				} else {
					transaction.rollback();
					System.out.println("Record not saved to database Some Problem...");
				}
			}

		}

	}

	@Override
	public void loadRecordUsingParent() {
		// TODO Auto-generated method stub

		Session session = null;
		Transaction transaction = null;
		Boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			@SuppressWarnings("unchecked")
			Query<Doctor> query = session.createQuery("from Doctor");		
			List<Doctor> list = query.getResultList();
			
			list.forEach(result -> {
				System.out.println(result);
				Set<Patient> patients = result.getPatients();
				patients.forEach(System.out::println);
				System.out.println();
			});
			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

	@Override
	public void saveRecordUsingChild() {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			//Created parent object
			Doctor doctor1 = new Doctor();
			doctor1.setDoctName("karthik");
			doctor1.setHospital("Apollo");
			
			Doctor doctor2 = new Doctor();
			doctor2.setDoctName("Ajay");
			doctor2.setHospital("Fortis");
			
			Doctor doctor3 = new Doctor();
			doctor3.setDoctName("savan");
			doctor3.setHospital("Victoria");

			//Created child objects
			Patient patient1 = new Patient();
			patient1.setPatName("suresh");
			patient1.setProblem("heart");
			
			Patient patient2 = new Patient();
			patient2.setPatName("dinesh");
			patient2.setProblem("kidney");
			
			Patient patient3 = new Patient();
			patient3.setPatName("ramesh");
			patient3.setProblem("covid");
			
			//linking child to parent
			doctor1.setPatients(Set.of(patient1, patient3));
			doctor2.setPatients(Set.of(patient3, patient2));
			doctor3.setPatients(Set.of(patient2, patient1));
			
			//linking parent to childs
			patient1.setDoctor(Set.of(doctor1, doctor3));
			patient2.setDoctor(Set.of(doctor2, doctor1));
			patient3.setDoctor(Set.of(doctor3));
			
			session.save(patient1);
			session.save(patient2);
			session.save(patient3);
			
			flag = true;

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (transaction != null) {
				if (flag) {
					transaction.commit();
					System.out.println("Saving record using child...");
				} else {
					transaction.rollback();
					System.out.println("Some problem with insertion...");
				}
			}

		}

	}

	@Override
	public void loadRecordUsingChild() {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			@SuppressWarnings("unchecked")
			Query<Patient> query = session.createQuery("from Patient");
			List<Patient> resultList = query.getResultList();
			
			resultList.forEach(patient->{
				System.out.println(patient);
				Set<Doctor> doctor = patient.getDoctor();
				doctor.forEach(System.out::println);
				System.out.println();
			});
			

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
