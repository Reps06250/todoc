package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

//The purpose of the repository is to isolate the data source (DAO) from the ViewModel,
// who does not directly manipulate the data source.
public class ProjectDataRepository {
    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    // --- GET PROJECT ---
    public LiveData<List<Project>> getProjects() { return this.projectDao.getProjects(); }
}
