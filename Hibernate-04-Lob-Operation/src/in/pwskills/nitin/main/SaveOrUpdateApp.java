package in.pwskills.nitin.main;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.pwskills.nitin.bean.Naukri;
import in.pwskills.nitin.util.HibernateUtil;

public class SaveOrUpdateApp {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;

		Boolean flag = false;
		byte[] image = null;
		char[] resume = null;
		FileInputStream fis = null;
		BufferedReader br = null;

		try{
			//reading image data
			fis = new FileInputStream("D:\\upload\\photos\\download.jpeg");
			int size = fis.available();
			image = new byte[size];
			fis.read(image);
			
			//reading resume data
			File file = new File("D:\\upload\\resumes\\resume.txt");
			br = new BufferedReader(new FileReader(file));
			resume = new char[(int) file.length()];
			br.read(resume);
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			Naukri naukri = new Naukri();
			naukri.setName("Rengoku");
			naukri.setAddress("Demon Slayer");
			
			naukri.setImage(image);
			naukri.setResume(resume);
			
			session.save(naukri);
			flag = true;
			
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (transaction != null) {
				if (flag) {
					transaction.commit();
					System.out.println("Record inserted/updated succesfully...");
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
}
