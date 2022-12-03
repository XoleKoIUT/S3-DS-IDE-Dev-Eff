package orange;

public class Evenement {
	private int num_alias;
	private String time;
	private String iso_country;
	private String country;
	private String type_ev;
	private String categ_ev;
	private int x;
	private int y;
	private String information;
	private int num_mat;

	public Evenement(int num_alias, String time, String iso_country, String country, String type_ev, String categ_ev,
			int x, int y, String information, int num_mat) {
		this.num_alias = num_alias;
		this.time = time;
		this.iso_country = iso_country;
		this.country = country;
		this.type_ev = type_ev;
		this.categ_ev = categ_ev;
		this.x = x;
		this.y = y;
		this.information = information;
		this.num_mat = num_mat;
	}

	public int getNum_alias() {
		return num_alias;
	}

	public void setNum_alias(int num_alias) {
		this.num_alias = num_alias;
	}

	public String getTime() {
		return time;
	}
	
	public int getHeure() {
		int    res;
		String str = this.getTime();
		
		res        = Integer.valueOf(str.substring(11,13));
		
		return res;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIso_country() {
		return iso_country;
	}

	public void setIso_country(String iso_country) {
		this.iso_country = iso_country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType_ev() {
		return type_ev;
	}

	public void setType_ev(String type_ev) {
		this.type_ev = type_ev;
	}

	public String getCateg_ev() {
		return categ_ev;
	}

	public void setCateg_ev(String categ_ev) {
		this.categ_ev = categ_ev;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getNum_mat() {
		return num_mat;
	}

	public void setNum_mat(int num_mat) {
		this.num_mat = num_mat;
	}

}