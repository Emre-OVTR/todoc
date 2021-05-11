package com.cleanup.todoc.database.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.style.TtsSpan;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.ui.MainActivity;
import com.cleanup.todoc.ui.TasksAdapter;

import java.util.Calendar;
import java.util.Date;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class CleanUpDatabase extends RoomDatabase {

    public static volatile CleanUpDatabase INSTANCE;

    public abstract TaskDao taskDao();

    public static CleanUpDatabase getInstance(Context context) {

        if (INSTANCE == null){

            synchronized (CleanUpDatabase.class) {
                if (INSTANCE== null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                    CleanUpDatabase.class, "MyDatebase.db")
                                                    .addCallback(prepopulateDatabase())
                                                    .build();
                }
            }

        }
        return INSTANCE;
    }


    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("projectId", 1L);
                contentValues.put("name", "Test");
                contentValues.put("creationTimestamp", new Date().getTime());


                db.insert("Task", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }

}
