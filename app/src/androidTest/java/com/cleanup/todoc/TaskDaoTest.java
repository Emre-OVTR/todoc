package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private TodocDatabase mDatabase;
    private TaskDao mTaskDao;
    private ProjectDao mProjectDao;

    Project NEW_PROJECT = new Project(1, "projettest", Color.RED);
    Task NEW_TASK = new Task(1, "test", 0);



    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void initDb(){
        this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(), TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb(){
        mDatabase.close();
    }

    @Test
    public void addAndGetTasks() throws Exception{

        this.mDatabase.projectDao().createProject(NEW_PROJECT);

        this.mDatabase.taskDao().addTask(NEW_TASK);

        List<Task> tasks = LiveDataTestUtils.getValue(this.mDatabase.taskDao().getAllTasks());

        assertEquals(tasks.get(0).getName(), NEW_TASK.getName());
    }

    @Test
    public void deleteAndGetTasks() throws Exception{

        this.mDatabase.projectDao().createProject(NEW_PROJECT);
        this.mDatabase.taskDao().addTask(NEW_TASK);
        this.mDatabase.taskDao().deleteTask(NEW_TASK);
        List<Task> tasks = LiveDataTestUtils.getValue(this.mDatabase.taskDao().getAllTasks());
        assertFalse(tasks.contains(NEW_TASK));
    }


















}
