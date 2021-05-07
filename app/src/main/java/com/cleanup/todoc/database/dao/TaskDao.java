package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.ui.TasksAdapter;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task WHERE projectId = :projectId")
    LiveData<List<Task>> getTasks(long projectId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //long insertTask(Task task);
    void addTask(Task task);

    @Insert
    void updateTask(List<Task> tasks);

    @Query("DELETE FROM Task WHERE id = :taskId")
    void deleteTask(long taskId);

}
