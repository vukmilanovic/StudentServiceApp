package model_sistema;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class pkg_main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		Date temp = new Date(1, 1, 2000);
		Date[] temps = new Date[] {temp, temp};
		
		File f = new File("DataBase\\test.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		try {
			oos.writeObject(temps);
		} finally {
			oos.close();
		}
		
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		try {
			temps = (Date[]) ois.readObject();
		}
		finally {
			ois.close();
		}
		
		for(Date d : temps) System.out.println(d.toString());
	}
}
