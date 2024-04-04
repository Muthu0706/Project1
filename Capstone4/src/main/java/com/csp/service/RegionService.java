package com.csp.service;

import com.csp.bean.Region;
import com.csp.dao.RegionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionDao regionDao;

    public boolean saveOrUpdateRegion(Region region) {
        regionDao.save(region);
        return true;
    }

    public boolean deleteRegionById(Long regionId) {
        regionDao.deleteById(regionId);
        return true;
    }

    public List<Region> getAllRegions() {
        return regionDao.findAll();
    }
}