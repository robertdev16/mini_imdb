package mini_imdb;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mini_imdb.dao.ImdbDAO;

public class App {
	
	private static final EntityManagerFactory emf;
	private static ImdbDAO imdbDAO;
	private static final String READ_IMG_PATH = "target/classes/img";
	private static final String WRITE_IMG_PATH = "write_img";
	private static Logger logger = LoggerFactory.getLogger(App.class);

	static {
		try {
			emf = Persistence.createEntityManagerFactory("mini_imdb");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}	
	
	
	public static void main(String[] args) {
		imdbDAO = new ImdbDAO(emf);

		
		emf.close();

	}

	
	
	private static byte[] readPic(String filename) {
        Path p = FileSystems.getDefault().getPath(READ_IMG_PATH, filename);
        byte[] fileData = null;
        try {
			fileData = Files.readAllBytes(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileData;
	}

	private static Path writePic(byte[] fileData, String fileName) {
        if (fileData == null || fileName == null || fileName.length() == 0)
        	return null;
        Path p = FileSystems.getDefault().getPath(WRITE_IMG_PATH, fileName);
        Path result = null;

        try {
        	result = Files.write(p, fileData, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
