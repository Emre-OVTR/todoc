package com.cleanup.todoc;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    private TodocDatabase mTodocDatabase;
    private static Project NEW_PROJECT = new Project(1, "Projet Rouge", Color.RED);

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
