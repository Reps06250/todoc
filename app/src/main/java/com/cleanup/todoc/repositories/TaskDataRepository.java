package com.cleanup.todoc.repositories;
import com.cleanup.todoc.database.TaskDao;
import android.arch.lifecycle.LiveData;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {
    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) { this.taskDao = taskDao; }

    // --- GET ---

    public LiveData<List<Task>> getTasks(){ return this.taskDao.getTasks(); }
 //   public List<Task> getTasksList(){ return this.taskDao.getTasksList(); }


    // --- CREATE ---

    public void createTask(Task task){ taskDao.insertTask(task); }

    // --- DELETE ---
    public void deleteTask(long id){ taskDao.deleteTask(id); }

    // --- UPDATE ---
    public void updateTask(Task task){ taskDao.updateTask(task); }

}
