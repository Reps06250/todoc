package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.ToDocDatabase;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
class TaskDaoTest {
    // FOR DATA
    private ToDocDatabase database;
    // DATA SET FOR TEST
    private static Task TASK_DEMO = new Task(0, 1L, "blabla", 999999);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ToDocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void insertAndGetTask() throws InterruptedException {
        // BEFORE : Adding a new task
        this.database.taskDao().insertTask(TASK_DEMO);
        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        Task task = tasks.get(0);
        assertFalse(task.getName().equals(TASK_DEMO.getName()) && task.getId() == TASK_DEMO.getId());
    }

    @Test
    public void deleteTask() throws InterruptedException {
        // BEFORE : Adding and delete a new task
        this.database.taskDao().insertTask(TASK_DEMO);
        this.database.taskDao().deleteTask(0);
        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }
}
