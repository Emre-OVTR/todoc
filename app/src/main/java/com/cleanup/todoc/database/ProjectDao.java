package com.cleanup.todoc.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.annotation.VisibleForTesting;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM Projects")
    LiveData<List<Project>> getAllProjects();

    @VisibleForTesting
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

}
