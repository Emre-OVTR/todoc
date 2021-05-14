package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskdao;

    public TaskDataRepository(TaskDao taskdao) {
        this.taskdao = taskdao;
    }

    public LiveData<Task> getTask(long taskId){
        return this.taskdao.getTask(taskId);
    }

    public LiveData<List<Task>> getAllTasks(){
        return taskdao.getAllTasks();
    }

    public void addTask(Task task){
        taskdao.addTask(task);
    }

    public void deleteTask(long taskId){
        taskdao.deleteTask(taskId);
    }

    public LiveData<List<Task>> sortTasksAlphabetical(){
        return this.taskdao.sortTasksAlphabetical();
    }

    public LiveData<List<Task>> sortTasksAlphabeticalInverted(){
        return this.taskdao.sortTasksAlphabeticalInverted();
    }

    public LiveData<List<Task>> sortTasksRecentFirst(){
        return this.taskdao.sortTasksRecentFirst();
    }

    public LiveData<List<Task>> sortTasksOldFirst(){
        return this.taskdao.sortTasksOldFirst();
    }


}
