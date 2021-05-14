package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task WHERE id = :taskId")
    LiveData<Task> getTask (long taskId);


    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTask(Task task);

    @Query("DELETE FROM Task WHERE id = :taskId")
    void deleteTask(long taskId);

    @Query("SELECT * FROM task ORDER BY name")
    LiveData<List<Task>> sortTasksAlphabetical();

    @Query("SELECT * FROM task ORDER BY name DESC")
    LiveData<List<Task>> sortTasksAlphabeticalInverted();

    @Query("SELECT * FROM task ORDER BY creationTimestamp")
    LiveData<List<Task>> sortTasksOldFirst();

    @Query("SELECT * FROM task ORDER BY creationTimestamp DESC")
    LiveData<List<Task>> sortTasksRecentFirst();

}
