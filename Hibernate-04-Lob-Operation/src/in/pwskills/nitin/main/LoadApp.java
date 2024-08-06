package in.pwskills.nitin.main;

import java.io.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import in.pwskills.nitin.bean.Naukri;
import in.pwskills.nitin.util.HibernateUtil;

public class LoadApp {

	public static void main(String[] args) {

		Session session = null;

		try {
			session = HibernateUtil.getSession();

			Naukri naukri = session.get(Naukri.class, 1);
			
			if (naukri != null) {
				System.out.println("NAme is :: "+naukri.getName());
				System.out.println("Address is ::"+naukri.getAddress());
				
				String ImageLoc = "E:\\TimePAss\\image.jpeg";
				String ResumeLoc = "E:\\TimePAss\\resume.txt";
				try(FileWriter fw = new FileWriter(new File(ResumeLoc));
					FileOutputStream oos = new FileOutputStream(ImageLoc)){
					
					fw.write(naukri.getResume());
					fw.flush();
					System.out.println("REsume details in "+ResumeLoc);
					
					oos.write(naukri.getImage());
					oos.flush();
					System.out.println("Image location in "+ImageLoc);
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}else {
				System.out.println("Record not found for given id");
			}

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
