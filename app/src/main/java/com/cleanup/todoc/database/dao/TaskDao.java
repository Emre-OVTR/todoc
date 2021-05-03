package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task WHERE projectId = :projectId")
    LiveData<List<Task>> getTasks(long projectId);

    @Insert
    long insertTask(Task task);

    @Insert
    long updateTask(Task task);

    @Query("DELETE FROM Task WHERE id = :taskId")
    long deleteTask(long taskId);

}
