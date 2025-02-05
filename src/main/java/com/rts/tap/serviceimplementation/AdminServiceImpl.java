package com.rts.tap.serviceimplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.rts.tap.dao.AdminDao;
import com.rts.tap.exception.AdminException;
import com.rts.tap.model.Admin;
import com.rts.tap.service.AdminService;
import com.rts.tap.constants.MessageConstants; // Import message constants
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public String addAdmin(Admin admin) {
        try {
            adminDao.save(admin);
            logger.info(MessageConstants.ADMIN_CREATED_SUCCESS + ": {}", admin);
            return MessageConstants.ADMIN_CREATED_SUCCESS;

        } catch (Exception e) {
            logger.error(MessageConstants.ADMIN_ADDITION_FAILED + ": {}", e.getMessage(), e);
            throw new AdminException(MessageConstants.ADMIN_ADDITION_FAILED);
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        try {
            logger.info(MessageConstants.GETTING_ALL_ADMINS);
            List<Admin> admins = adminDao.findAll();
            logger.info(MessageConstants.ADMINS_RETRIEVED_SUCCESS + ": {}", admins);
            return admins;
        } catch (Exception e) {
            logger.error(MessageConstants.ADMIN_RETRIEVAL_FAILED + ": {}", e.getMessage(), e);
            throw new AdminException(MessageConstants.ADMIN_RETRIEVAL_FAILED);
        }
    }

    @Override
    public Admin getAdminById(Long id) {
        try {
            logger.info("Retrieving admin with ID: {}", id);
            Admin admin = adminDao.findById(id);
            if (admin != null) {
                logger.info(MessageConstants.ADMIN_RETRIEVED_SUCCESS + ": {}", admin);
            } else {
                logger.warn(MessageConstants.ADMIN_NOT_FOUND + " ID: {}", id);
            }
            return admin;
        } catch (Exception e) {
            logger.error(MessageConstants.ADMIN_RETRIEVAL_FAILED + " ID: {}: {}", id, e.getMessage(), e);
            throw new AdminException(MessageConstants.ADMIN_RETRIEVAL_FAILED);
        }
    }
}