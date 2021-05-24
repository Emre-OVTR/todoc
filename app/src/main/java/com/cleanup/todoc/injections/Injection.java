package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.dao.CleanUpDatabase;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskDataRepository provideTaskDataSource(Context context){
        CleanUpDatabase database = CleanUpDatabase.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }

    public static Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();}

    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskDataRepository dataSourceTask = provideTaskDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceTask, executor);
    }

}
