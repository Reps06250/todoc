package com.cleanup.todoc.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {
    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor; //facilite l'exécution en arrière-plan de certaines méthodes

    // DATA
    @Nullable
    private LiveData<List<Task>> currentTasks;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    public void init() { //initialise le ViewModel dès que l'activité se crée et qui sera donc appelée à l'intérieur de sa méthode  onCreate()
        if (this.currentTasks != null) {
            return;
        }
        currentTasks = taskDataSource.getTasks();
    }

    // -------------
    // FOR PROJECT
    // -------------

    public LiveData<List<Project>> getProjects() { return projectDataSource.getProjects();  }

//    public Project getProject(long projectId) { return projectDataSource.getProject(projectId);  }


    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTasks() {
        return taskDataSource.getTasks();
    }

//We use the Executor class to asynchronously perform the update queries of our SQLite tables.
    public void createTask(Task task) {
        executor.execute(() -> {
            taskDataSource.createTask(task);
        });
    }

    public void deleteTask(long id) {
        executor.execute(() -> {
            taskDataSource.deleteTask(id);
        });
    }

}
