package com.csp.controller;

import com.csp.bean.Region;
import com.csp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping("/insertRegions")
    public void performInsert(@RequestBody Region region) {
        regionService.saveOrUpdateRegion(region);
    }

    @PutMapping("/updateRegions")
    public void performUpdate(@RequestBody Region region) {
        regionService.saveOrUpdateRegion(region);
    }

    @DeleteMapping("/deleteRegions/{regionId}")
    public void performDelete(@PathVariable Long regionId) {
        regionService.deleteRegionById(regionId);
    }

    @GetMapping("/findAllRegions")
    public List<Region> viewAllRegions() {
        return regionService.getAllRegions();
    }
}