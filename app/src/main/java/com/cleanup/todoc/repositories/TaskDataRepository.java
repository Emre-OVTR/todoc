package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskdao;

    public TaskDataRepository(TaskDao taskdao) {
        this.taskdao = taskdao;
    }

    public LiveData<List<Task>> getAllTasks(){
        return taskdao.getAllTasks();
    }

    public void addTask(Task task){
        taskdao.addTask(task);
    }

    public void deleteTask(Task task){
        taskdao.deleteTask(task);
    }




}
