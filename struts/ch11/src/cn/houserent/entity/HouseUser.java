package cn.houserent.entity;




import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "houseuser")
public class HouseUser implements Serializable {

	private static final long serialVersionUID = -4526367383828929L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="TELEPHONE")
	private String telephone;
	@Column(name="USERNAME")
	private String username;
	@Column(name="ISADMIN")
	private String isadmin;
	@Column(name="REALNAME")
	private String realname;

	public HouseUser() {

	}
	public HouseUser(Integer id) {
		super();
		this.id = id;
	}
	public HouseUser(Integer id, String password, String telephone, String username, String isadmin, String realname) {
		super();
		this.id = id;
		this.password = password;
		this.telephone = telephone;
		this.username = username;
		this.isadmin = isadmin;
		this.realname = realname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	
}
