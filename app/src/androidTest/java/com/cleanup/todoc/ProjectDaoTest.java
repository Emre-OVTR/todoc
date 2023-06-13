package com.cleanup.todoc;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.graphics.Color;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    private TodocDatabase mTodocDatabase;
    private static Project NEW_PROJECT = new Project(1, "Projet Rouge", Color.RED);


    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb(){
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        this.mTodocDatabase = Room.inMemoryDatabaseBuilder(context, TodocDatabase.class).allowMainThreadQueries().build();

    }

    @After
    public void closeDb(){
        mTodocDatabase.close();
    }

    @Test
    public void getProjects() throws Exception{

        this.mTodocDatabase.projectDao().createProject(NEW_PROJECT);

        List<Project> projects = LiveDataTestUtils.getValue(this.mTodocDatabase.projectDao().getAllProjects());

        assertEquals(projects.get(0).getName(), NEW_PROJECT.getName());
    }

}
