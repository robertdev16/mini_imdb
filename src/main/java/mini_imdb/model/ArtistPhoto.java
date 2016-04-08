package mini_imdb.model;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class ArtistPhoto {

	@Lob
	private byte[] photoBytes;
	
	public ArtistPhoto() {
	}

	public ArtistPhoto(byte[] photoBytes) {
		super();
		this.photoBytes = photoBytes;
	}

	public byte[] getPhotoBytes() {
		return photoBytes;
	}

	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}

}
