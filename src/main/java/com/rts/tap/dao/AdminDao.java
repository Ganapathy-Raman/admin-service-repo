package com.rts.tap.dao;

import com.rts.tap.model.Admin;
import java.util.List;

public interface AdminDao {
	Admin save(Admin admin);

	Admin findEmail(String username);

	List<Admin> findAll();

	Admin findById(Long id);
}