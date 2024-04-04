package com.csp.service;

import com.csp.bean.Project;
import com.csp.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public boolean saveOrUpdateProject(Project project) {
        projectDao.save(project);
        return true;
    }

    public boolean deleteProjectById(Long projectId) {
        projectDao.deleteById(projectId);
        return true;
    }

    public List<Project> getAllProjects() {
        return projectDao.findAll();
    }
}