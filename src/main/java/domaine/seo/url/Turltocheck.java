package domaine.seo.url;

import java.util.Date;


public class Turltocheck implements Comparable  {
	
	private String url;
	
	private Integer uid;
	
	private Date addDate;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
	public Turltocheck(String url,Integer uid) {
		this.url = url;
		this.uid = uid;
	}
	
	public Turltocheck(String url) {
		this.url = url;
	}

	public int compareTo(Object o) {
		if(this.url != null && this.url.equals(((Turltocheck)o).getUrl())) {
			return 0;
		}
		return -1;
	}
	
	@Override
	public boolean  equals(Object other) {
	    if (other==null || !(other instanceof Turltocheck)) {
	        return false;
	    }
	    return this.url.equals(((Turltocheck)other).url); // assume name is never null. You can check for that
	}
	
	public String toString() {
		return " uid : " + this.uid + " url : " + this.url;
		
	}
	
}