package com.booking.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.Object.ObjMenu;
import com.booking.ObjectDAO.TblMenu;
import com.booking.dao.TblMenuDAO;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceFacade{
	
	@Autowired
	TblMenuDAO tblMenuDAO;

	public ArrayList<ObjMenu> getAllMenu(){
		ArrayList<ObjMenu> objMenus = new ArrayList<>();
		ArrayList<TblMenu> tblMenus = new ArrayList<>();
		
		tblMenus = tblMenuDAO.getAllMenu();
		for(TblMenu tbl : tblMenus){
			ObjMenu obj = new ObjMenu();
			obj.setMenuId(tbl.getMenuId());
			obj.setMenuName(tbl.getMenuName());
			obj.setMenuAction(tbl.getMenuAction());
			obj.setDescription(tbl.getDescription());
			obj.setRoleId(tbl.getRoleId());
			objMenus.add(obj);
		}

		return objMenus;
		
	}
}
