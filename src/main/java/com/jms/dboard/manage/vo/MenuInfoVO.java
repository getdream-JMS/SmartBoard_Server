package com.jms.dboard.manage.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("menuInfoVO")
public class MenuInfoVO {
	int menuId;
	String menuCode;
	String menuPid;
    String menuName;
    String menuDepth;
    List<MenuInfoVO> subMenu;
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuPid() {
		return menuPid;
	}
	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuDepth() {
		return menuDepth;
	}
	public void setMenuDepth(String menuDepth) {
		this.menuDepth = menuDepth;
	}
	public List<MenuInfoVO> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<MenuInfoVO> subMenu) {
		this.subMenu = subMenu;
	}
	
}
