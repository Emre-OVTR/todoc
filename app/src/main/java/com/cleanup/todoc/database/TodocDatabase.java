package com.cleanup.todoc.database;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import androidx.annotation.NonNull;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;


@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    public static volatile TodocDatabase INSTANCE;
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();



    public static TodocDatabase getInstance(final Context context) {

        if (INSTANCE == null){

            synchronized (TodocDatabase.class) {
                if (INSTANCE== null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                    TodocDatabase.class, "todoc_database.db")
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

                Project[] projects = Project.getAllProjects();
                for (Project project : projects) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", project.getName());
                    contentValues.put("color", project.getColor());

                    db.insert("projects", OnConflictStrategy.IGNORE, contentValues);
                }
            }
        };

    }

}
