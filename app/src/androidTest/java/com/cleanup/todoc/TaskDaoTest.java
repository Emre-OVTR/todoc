package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.dao.CleanUpDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private CleanUpDatabase database;
    private static long TASK_ID = 1L;
    private static long PROJECT_ID = 1L;
    private static Task TASK_DEMO = new Task(TASK_ID, PROJECT_ID, "Test", 143);
    private static Task NEW_TASK = new Task(2L, 1L, "New", 144);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                CleanUpDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }


    @Test
    public void taskListIsEmpty() throws InterruptedException{
        List<Task> task = LiveDataTestUtils.getValue(this.database.taskDao().getAllTasks());
        assertTrue(task.isEmpty());
    }


    @Test
    public void addAndGetTask() throws InterruptedException{

        this.database.taskDao().addTask(TASK_DEMO);
        List<Task> task = LiveDataTestUtils.getValue(this.database.taskDao().getAllTasks());
        assertTrue(task.size() == 1);
    }

    @Test
    public void deleteTask() throws  InterruptedException{
        this.database.taskDao().deleteTask(TASK_ID);
        Task task = LiveDataTestUtils.getValue(this.database.taskDao().getTask(TASK_ID));
        assertNull(task);
    }

    @Test
    public void sortTaskAlphabetical() throws InterruptedException{
        this.database.taskDao().addTask(TASK_DEMO);
        this.database.taskDao().addTask(NEW_TASK);
        List<Task> tasks = LiveDataTestUtils.getValue(this.database.taskDao().sortTasksAlphabetical());
        assertTrue(tasks.get(0).getName().equals(NEW_TASK.name));
        assertTrue(tasks.get(1).getName().equals(TASK_DEMO.name));

    }

    @Test
    public void sortTaskAlphabeticalInverted() throws InterruptedException{
        this.database.taskDao().addTask(TASK_DEMO);
        this.database.taskDao().addTask(NEW_TASK);
        List<Task> tasks = LiveDataTestUtils.getValue(this.database.taskDao().sortTasksAlphabeticalInverted());
        assertTrue(tasks.get(0).getName().equals(TASK_DEMO.name));
        assertTrue(tasks.get(1).getName().equals(NEW_TASK.name));
    }

    @Test
    public void sortTasksOldFirst() throws InterruptedException{
        this.database.taskDao().addTask(NEW_TASK);
        this.database.taskDao().addTask(TASK_DEMO);
        List<Task> tasks = LiveDataTestUtils.getValue(this.database.taskDao().sortTasksOldFirst());
        assertTrue(tasks.get(0).getName().equals(TASK_DEMO.name));
        assertTrue(tasks.get(1).getName().equals(NEW_TASK.name));
    }

    @Test
    public void sortTasksRecentFirst() throws InterruptedException{
        this.database.taskDao().addTask(TASK_DEMO);
        this.database.taskDao().addTask(NEW_TASK);
        List<Task> tasks = LiveDataTestUtils.getValue(this.database.taskDao().sortTasksRecentFirst());
        assertTrue(tasks.get(0).getName().equals(NEW_TASK.name));
        assertTrue(tasks.get(1).getName().equals(TASK_DEMO.name));
    }
}
