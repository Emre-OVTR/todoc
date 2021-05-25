package com.cleanup.todoc;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.dao.CleanUpDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    public CleanUpDatabase mCleanUpDatabase;
    private TaskDao mTaskDao;
    public ProjectDao mProjectDao;



    @Before
    public void initDb() {

        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        mCleanUpDatabase = Room.inMemoryDatabaseBuilder(context, CleanUpDatabase.class).allowMainThreadQueries().build();

        mTaskDao = mCleanUpDatabase.taskDao();

        mProjectDao = mCleanUpDatabase.projectDao();
    }



    @After
    public void closeDb() {
        mCleanUpDatabase.close();
    }


    @Test
    public void addAndGetTask() throws InterruptedException {





        Project example = new Project(1,"example", Color.RED);

        mProjectDao.createProject(example);

        Task task = new Task(1, "Task 0", 0);

        mTaskDao.addTask(task);

        List<Task> tasks = LiveDataTestUtils.getValue(mTaskDao.getAllTasks());

        assertEquals(1, tasks.size());

        assertEquals(task.getName(), tasks.get(0).getName());
    }

    @Test

    public void deleteTask() throws InterruptedException {


        mTaskDao = mCleanUpDatabase.taskDao();

        mProjectDao = mCleanUpDatabase.projectDao();

        Project example = new Project(1,"example", Color.RED);

        mProjectDao.createProject(example);

        Task task = new Task(1, "Task 0", 0);

        mTaskDao.addTask(task);

        List<Task> tasks = LiveDataTestUtils.getValue(mTaskDao.getAllTasks());

        mTaskDao.deleteTask(task);

        assertFalse(tasks.contains(task));


    }


















}
