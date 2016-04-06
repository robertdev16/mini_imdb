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
import mini_imdb.model.Actor;
import mini_imdb.model.Director;
import mini_imdb.model.Writer;

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
		
		Actor actorAngelina = new Actor("Angelina Jolie", "06/04/1975", "Los Angeles, California, USA");
		actorAngelina.setBiography("Angelina Jolie is an Oscar-winning actress who became popular after playing the title role in the 'Lara Croft' blockbuster movies, as well as Mr. & Mrs. Smith (2005), Wanted (2008), Salt (2010) and Maleficent (2014).");
		actorAngelina.getPhotoList().add(readPic("Angelina Jolie_01.jpg"));
		actorAngelina.getPhotoList().add(readPic("Angelina Jolie_02.jpg"));
		actorAngelina.getPhotoList().add(readPic("Angelina Jolie_03.jpg"));
		
		Actor actorBrad = new Actor("Brad Pitt", "12/18/1963", "Shawnee, Oklahoma, USA");
		actorBrad.setBiography("An actor and producer known as much for his versatility as he is for his handsome face, Golden Globe-winner Brad Pitt's most widely recognized role may be Tyler Durden in Fight Club (1999).");
		actorBrad.getPhotoList().add(readPic("Brad Pitt_01.jpg"));
		actorBrad.getPhotoList().add(readPic("Brad Pitt_02.jpg"));

		Director directorDoug = new Director("Doug Liman", "07/24/1965", "New York City, New York, USA");
		directorDoug.setBiography("Doug Liman was born on July 24, 1965 in New York City, New York, USA. He is a producer and director, known for Edge of Tomorrow (2014), The Bourne Identity (2002) and The Bourne Ultimatum (2007).");
		directorDoug.getPhotoList().add(readPic("Doug Liman.jpg"));
		
		Writer writerDrew = new Writer("Drew Goddard", "02/26/1975", "Los Alamos, New Mexico, USA");
		writerDrew.setBiography("Drew Goddard was raised in Los Alamos, New Mexico. He attended Los Alamos High School in Los Alamos, New Mexico and graduated in 1993. He then attended the University of Colorado, and worked as a production assistant in L.A. after graduation.");
		writerDrew.getPhotoList().add(readPic("Drew Goddard.jpg"));
		
		Writer writerJones = new Writer("J.G. Jones", "01/01/1901", "Unknown");
		writerJones.setBiography("J.G. Jones is known for his work on Wanted (2008), Fox (2016) and Grant Morrison: Talking with Gods (2010).");
		writerJones.getPhotoList().add(readPic("J.G. Jones.jpg"));
		
		Actor actorJames = new Actor("James McAvoy", "04/21/1979", "Port Glasgow, Scotland, UK");
		actorJames.setBiography("McAvoy was born on 21 April 1979 in Glasgow, Scotland, to Elizabeth (née Johnstone), a nurse, and James McAvoy senior, a bus driver.");
		actorJames.getPhotoList().add(readPic("James McAvoy_01.jpg"));
		actorJames.getPhotoList().add(readPic("James McAvoy_02.jpg"));
		
		Director directorMarc = new Director("Marc Forster", "11/30/1969", "Illertissen, Bavaria, Germany");
		directorMarc.setBiography("Marc Forster was born on November 30, 1969 in Illertissen, Bavaria, Germany. He is a producer and director, known for World War Z (2013), Quantum of Solace (2008) and Finding Neverland (2004).");
		directorMarc.getPhotoList().add(readPic("Marc Forster.jpg"));
		
		Writer writerMatthew = new Writer("Matthew Michael Carnahan", "01/01/1901", "Unknown");
		writerMatthew.setBiography("Matthew Michael Carnahan is a writer and producer, known for World War Z (2013), State of Play (2009) and The Kingdom (2007).");
		writerMatthew.getPhotoList().add(readPic("Matthew Michael Carnahan.jpg"));
		
		Actor actorMireille = new Actor("Mireille Enos", "09/22/1975", "Houston, Texas, USA");
		actorMireille.setBiography("Mireille Enos was born in Kansas City, Missouri. She attended The High School for the Performing and Visual Arts. She then went to Brigham Young University.");
		actorMireille.getPhotoList().add(readPic("Mireille Enos.jpg"));
		
		Actor actorMorgan = new Actor("Morgan Freeman", "06/01/1937", "Memphis, Tennessee, USA");
		actorMorgan.setBiography("With an authoritative voice and calm demeanor, this ever popular American actor has grown into one of the most respected figures in modern US cinema.");
		actorMorgan.getPhotoList().add(readPic("Morgan Freeman_01.jpg"));
		actorMorgan.getPhotoList().add(readPic("Morgan Freeman_02.jpg"));

		Writer writerSimon = new Writer("Simon Kinberg", "08/02/1973", "London, England, UK");
		writerSimon.setBiography("Simon Kinberg was born on August 2, 1973 in London, England. He is a producer and writer, known for X-Men: Days of Future Past (2014), Sherlock Holmes (2009) and X-Men: The Last Stand (2006).");
		writerSimon.getPhotoList().add(readPic("Simon Kinberg.jpg"));
		
		Director directorTimur = new Director("Timur Bekmambetov", "06/25/1961", "Guryev, Kazakh SSR, USSR");
		directorTimur.setBiography("Timur Bekmambetov is a Russian-Kazakh film director known for vampire franchise Night Watch (2004) and Day Watch (2006).");
		directorTimur.getPhotoList().add(readPic("Timur Bekmambetov.jpg"));
		
		
		
		
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
