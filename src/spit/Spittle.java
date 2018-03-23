/**
 * 
 */
/**
 * @author Hung
 *
 */
package spit;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Spittle{
	@Id
	protected int id;
	protected int type;
	protected String username;
	protected int QQ;
	protected String sex;
	
	public int getQQ() {
		return QQ;
	}

	public void setQQ(int qQ) {
		QQ = qQ;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Spittle(){
		
	}
	public Spittle(int id,int type,String username,int QQ,String sex) {
		// TODO 自动生成的构造函数存根
		this.id=id;
		this.type=type;
		this.username=username;
		this.QQ=QQ;
		this.sex=sex;
	}
	
}