package com.cleanup.todoc.database.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.VisibleForTesting;

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