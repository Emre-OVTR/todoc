package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

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
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    private CleanUpDatabase database;
    private static long TASK_ID = 1L;
    private static long PROJECT_ID = 1L;
    private static long TIMESTAMP = new Date().getTime();
    private static Task TASK_DEMO = new Task(TASK_ID, PROJECT_ID, "Test", TIMESTAMP);

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
    public void insertAndGetUser() throws InterruptedException{

        this.database.taskDao().addTask(TASK_DEMO);
        Task task = LiveDataTestUtils.getValue(this.database.taskDao().getTask(TASK_ID));
        assertTrue(task.getName().equals(TASK_DEMO.getName()) && task.getId() == TASK_ID );

    }
}
