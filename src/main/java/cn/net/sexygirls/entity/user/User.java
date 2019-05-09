package cn.net.sexygirls.entity.user;

import java.util.List;
/**
 * @Description:用户类
 * @Author: zule
 * @Date: 2019/5/6
 */
public class User{

	private Integer id;
	private String name;
	private String password;
	private Integer status;
	private List<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String password, Integer status) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}