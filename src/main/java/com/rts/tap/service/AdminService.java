package com.rts.tap.service;

import com.rts.tap.model.Admin;

import java.util.List;

public interface AdminService {
	String addAdmin(Admin admin);

	List<Admin> getAllAdmins();

	Admin getAdminById(Long id);
}