package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao mProjectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        mProjectDao = projectDao;
    }

    public LiveData<List<Project>> getAllProjects(){
        return mProjectDao.getAllProjects();
    }
}
