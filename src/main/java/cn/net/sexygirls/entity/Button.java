package cn.net.sexygirls.entity;

/**
 * @Description:按钮类，暂时没有
 * @Author: zule
 * @Date: 2019/5/6
 */
public class Button {
	private Integer id;
	private String name;
	private Menu menu;

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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
