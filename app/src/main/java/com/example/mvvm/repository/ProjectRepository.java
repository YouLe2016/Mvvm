package com.example.mvvm.repository;

import android.arch.lifecycle.LiveData;

import com.example.mvvm.App;
import com.example.mvvm.model.project.Projects;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.repository.local.LocalProjectsDataSource;
import com.example.mvvm.repository.remote.RemoteProjectsDataSource;
import com.example.mvvm.utils.LogUtils;
import com.example.mvvm.utils.NetworkUtils;

/**
 * 时间：2018/11/28 14:23
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class ProjectRepository {
    private static final ProjectRepository ourInstance = new ProjectRepository();

    public static ProjectRepository getInstance() {
        return ourInstance;
    }

    private ProjectRepository() {

    }

    private ProjectsDataSource remoteProjectDataSource = RemoteProjectsDataSource.getInstance();
    private ProjectsDataSource localProjectDataSource = LocalProjectsDataSource.getInstance();

    public LiveData<StateModel<Projects>> getProject(int page) {
        if (NetworkUtils.isNetworkConnected(App.getAppContext())) {
            return remoteProjectDataSource.queryProjects(page);
        } else {
            return localProjectDataSource.queryProjects(page);
        }
    }


}
