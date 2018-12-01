package com.example.mvvm.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.mvvm.model.project.Projects;
import com.example.mvvm.model.ui.StateFactory;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.repository.ProjectsDataSource;
import com.example.mvvm.repository.local.service.ProjectsService;
import com.example.mvvm.repository.local.service.ProjectsServiceImpl;
import com.example.mvvm.utils.LogUtils;


/**
 * 时间：2018/12/1 10:05
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class LocalProjectsDataSource implements ProjectsDataSource {
    private static final LocalProjectsDataSource ourInstance = new LocalProjectsDataSource();

    public static LocalProjectsDataSource getInstance() {
        return ourInstance;
    }

    private LocalProjectsDataSource() {
    }

    private ProjectsService service = ProjectsServiceImpl.getInstance();

    @Override
    public LiveData<StateModel<Projects>> queryProjects(int page) {
        MediatorLiveData<StateModel<Projects>> data = new MediatorLiveData<>();
        data.setValue(StateFactory.loading());
        data.addSource(service.queryProjects(page), projects -> {
            if (null == projects) {
                data.setValue(StateFactory.empty());
            } else {
                projects.itemsFromJson();
                data.setValue(StateFactory.content(projects));
            }
        });
        return data;
    }

    public LiveData<Long> addProjects(Projects projects) {
        projects.itemsToJson();
        return service.add(projects);
    }
}
