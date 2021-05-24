package com.cleanup.todoc.tasklist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    private final TaskDataRepository taskDataSource;
    private final Executor executor;

    private LiveData<List<Task>> currentTasks;

    public TaskViewModel(TaskDataRepository taskDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }

    public void init(){
        if (this.currentTasks!= null){
            return;
        }
        currentTasks = taskDataSource.getAllTasks();


    }

    public LiveData<List<Task>> getAllTasks(){
        return this.currentTasks;
    }

    public void addTask(Task task) {
        executor.execute(() -> taskDataSource.addTask(task));
    }

    //public void addTask(final Task task) {
      //  executor.execute(new Runnable() {
        //    @Override
          //  public void run() {
            //    taskDataSource.addTask(task);
         //   }
      //  });
    //}

    public void deleteTask(Task task) {
        executor.execute(() -> taskDataSource.deleteTask(task));
   }




}
