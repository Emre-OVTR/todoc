package com.cleanup.todoc.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class MainActivityViewModel extends ViewModel {

    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;


    @Nullable
    private LiveData<List<Project>> mProjects;

    public MainActivityViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    public void init(){
       mProjects = projectDataSource.getAllProjects();
    }

    @Nullable
    public LiveData<List<Project>> getProjects(){
        return mProjects;
    }

    public LiveData<List<Task>> getTasks(){
        return taskDataSource.getAllTasks();
    }

    public void addTask(Task task) {
        executor.execute(() -> taskDataSource.addTask(task));
    }

    public void deleteTask(Task task) {
        executor.execute(() -> taskDataSource.deleteTask(task));
   }




}
