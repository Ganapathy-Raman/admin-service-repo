package com.rts.tap.serviceimplementation;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.rts.tap.constants.MessageConstants;
import com.rts.tap.dao.BusinessUnitDao;
import com.rts.tap.exception.BusinessUnitException;
import com.rts.tap.model.BusinessUnit;
import com.rts.tap.service.BusinessUnitService;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

    private final BusinessUnitDao businessUnitDao;
    private static final Logger logger = LoggerFactory.getLogger(BusinessUnitServiceImpl.class);

    public BusinessUnitServiceImpl(BusinessUnitDao businessUnitDao) {
        this.businessUnitDao = businessUnitDao;
    }

    @Override
    public String addBusinessUnit(BusinessUnit businessUnit) {
        try {
            businessUnitDao.save(businessUnit);
            logger.info(MessageConstants.BUSINESSUNIT_ADDED_SUCCESS + ": {}", businessUnit);
            return MessageConstants.BUSINESSUNIT_ADDED_SUCCESS;
        } catch (Exception e) {
            logger.error(MessageConstants.BUSINESSUNIT_ADDITION_FAILED + ": {}", e.getMessage(), e);
            throw new BusinessUnitException(MessageConstants.BUSINESSUNIT_ADDITION_FAILED);
        }
    }

    @Override
    public BusinessUnit getBusinessUnitByLocation(String workLocation) {
        try {
            BusinessUnit businessUnit = businessUnitDao.getBusinessUnitByLocation(workLocation);

            if (businessUnit != null) {
                logger.info(MessageConstants.BUSINESSUNIT_RETRIEVED_SUCCESS + " for location {}: {}", workLocation, businessUnit);
            } else {
                logger.warn(MessageConstants.BUSINESSUNIT_NOT_FOUND + " location: {}", workLocation);
            }

            return businessUnit;
        } catch (Exception e) {
            logger.error(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED + " for location {}: {}", workLocation, e.getMessage(), e);
            throw new BusinessUnitException(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED);
        }
    }

    @Override
    public BusinessUnit getBusinessUnitById(Long businessunitId) {
        try {
            BusinessUnit businessUnit = businessUnitDao.findById(businessunitId);

            if (businessUnit == null) {
                logger.warn(MessageConstants.BUSINESSUNIT_NOT_FOUND + " ID: {}", businessunitId);
                throw new BusinessUnitException(MessageConstants.BUSINESSUNIT_NOT_FOUND + " with ID: " + businessunitId);
            }

            logger.info(MessageConstants.BUSINESSUNIT_RETRIEVED_SUCCESS + " for ID {}: {}", businessunitId, businessUnit);
            return businessUnit;

        } catch (Exception e) {
            logger.error(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED + " ID: {}: {}", businessunitId, e.getMessage(), e);
            throw new BusinessUnitException(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED);
        }
    }

    @Override
    public List<BusinessUnit> getAllBusinessUnits() {
        try {
            List<BusinessUnit> businessUnits = businessUnitDao.findAll();

            if (businessUnits.isEmpty()) {
                logger.warn(MessageConstants.NO_BUSINESSUNITS_FOUND);
            } else {
                logger.info("Successfully retrieved {} business units.", businessUnits.size());
            }

            return businessUnits;
        } catch (Exception e) {
            logger.error(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED + ": {}", e.getMessage(), e);
            throw new BusinessUnitException(MessageConstants.BUSINESSUNIT_RETRIEVAL_FAILED);
        }
    }

    
    @Override
    public String updateBusinessUnit(Long id, BusinessUnit updatedBusinessUnit) {
        try {
            BusinessUnit existingBusinessUnit = businessUnitDao.findById(id);
            
            if (existingBusinessUnit != null) {
                existingBusinessUnit.setBusinessUnitName(updatedBusinessUnit.getBusinessUnitName());
                existingBusinessUnit.setDescription(updatedBusinessUnit.getDescription());
                existingBusinessUnit.setBusinessUnitLocation(updatedBusinessUnit.getBusinessUnitLocation());
                businessUnitDao.save(existingBusinessUnit); // This can throw a RuntimeException
                logger.info(MessageConstants.BUSINESSUNIT_UPDATED_SUCCESS + ": {}", existingBusinessUnit);
                return MessageConstants.BUSINESSUNIT_UPDATED_SUCCESS;
            } else {
                logger.warn(MessageConstants.BUSINESSUNIT_NOT_FOUND + " ID: {}", id);
                throw new BusinessUnitException(MessageConstants.BUSINESSUNIT_NOT_FOUND + " with ID: " + id);
            }
        } catch (RuntimeException e) { 
            logger.error("Failed to update business unit for ID {}: {}", id, e.getMessage(), e);
            throw new BusinessUnitException(MessageConstants.BUSINESSUNIT_UPDATE_FAILED);
        }
    }
}