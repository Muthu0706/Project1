package com.csp.controller;

import com.csp.bean.Project;
import com.csp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/insertProjects")
    public void performInsert(@RequestBody Project project) {
        projectService.saveOrUpdateProject(project);
    }

    @PutMapping("/updateProjects")
    public void performUpdate(@RequestBody Project project) {
        projectService.saveOrUpdateProject(project);
    }

    @DeleteMapping("/deleteProjects/{projectId}")
    public void performDelete(@PathVariable Long projectId) {
        projectService.deleteProjectById(projectId);
    }

    @GetMapping("/findAllProjects")
    public List<Project> viewAllProjects() {
        return projectService.getAllProjects();
    }
}