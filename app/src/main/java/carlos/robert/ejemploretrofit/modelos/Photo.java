package carlos.robert.ejemploretrofit.modelos;

public class Photo{
	private int albumId;
	private int id;
	private String title;
	private String url;
	private String thumbnailUrl;

	public int getAlbumId(){
		return albumId;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	public String toString() {
		return "Photo{" +
				"albumId=" + albumId +
				", id=" + id +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", thumbnailUrl='" + thumbnailUrl + '\'' +
				'}';
	}
}
