package com.example.mvvm.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.mvvm.model.livedata.StateLiveDateFactory;
import com.example.mvvm.model.project.Projects;
import com.example.mvvm.model.ui.StateFactory;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.repository.ProjectsDataSource;
import com.example.mvvm.repository.local.LocalProjectsDataSource;
import com.example.mvvm.repository.remote.service.ProjectApi;
import com.example.mvvm.utils.net.RetrofitFactory;
import com.example.mvvm.utils.net.RxUtils;


/**
 * 时间：2018/11/28 16:03
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class RemoteProjectsDataSource implements ProjectsDataSource {

    private static final RemoteProjectsDataSource instance = new RemoteProjectsDataSource();

    private RemoteProjectsDataSource() {
    }

    public static RemoteProjectsDataSource getInstance() {
        return instance;
    }

    private ProjectApi api = RetrofitFactory.getInstance().create(ProjectApi.class);
    private LocalProjectsDataSource localProjectsDataSource = LocalProjectsDataSource.getInstance();

    @Override
    public LiveData<StateModel<Projects>> queryProjects(int page) {
        MutableLiveData<StateModel<Projects>> data = new MutableLiveData<>();
        data.setValue(StateFactory.loading());
        return StateLiveDateFactory.createStateModel(api.queryProjects(page)
                .compose(RxUtils.transform())
                .map(projects -> {
                    projects.setPage(page);
                    localProjectsDataSource.addProjects(projects);
                    return projects;
                }));
    }
}
