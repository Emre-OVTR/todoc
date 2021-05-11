package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.ui.TasksAdapter;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task WHERE id = :taskId")
    LiveData<List<Task>> getTasks (long taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTask(Task task);



    @Query("DELETE FROM Task WHERE id = :taskId")
    void deleteTask(long taskId);

}
