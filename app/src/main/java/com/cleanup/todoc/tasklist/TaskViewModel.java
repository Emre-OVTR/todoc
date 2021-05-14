package com.cleanup.todoc.tasklist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    private final TaskDataRepository taskDataSource;
    private final Executor executor;

    public TaskViewModel(TaskDataRepository taskDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }

    public LiveData<List<Task>> getTasks(){
        return taskDataSource.getAllTasks();
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

    public void deleteTask(long taskId) {
        executor.execute(() -> taskDataSource.deleteTask(taskId));
    }

    public void sortTasksAlphabetical(){
        executor.execute(() -> taskDataSource.sortTasksAlphabetical());
    }

    public void sortTasksAlphabeticalInverted(){
        executor.execute(() -> taskDataSource.sortTasksAlphabeticalInverted());
    }

    public void sortTasksRecentFirst(){
        executor.execute(() -> taskDataSource.sortTasksRecentFirst());
    }

    public void sortTasksOldFirst(){
        executor.execute(() -> taskDataSource.sortTasksOldFirst());
    }


}
