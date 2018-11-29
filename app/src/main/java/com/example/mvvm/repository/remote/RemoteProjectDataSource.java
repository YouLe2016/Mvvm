package com.example.mvvm.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.mvvm.model.project.Projects;
import com.example.mvvm.model.ui.StateFactory;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.repository.ProjectDataSource;
import com.example.mvvm.utils.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
public class RemoteProjectDataSource implements ProjectDataSource {

    private static final RemoteProjectDataSource instance = new RemoteProjectDataSource();

    private RemoteProjectDataSource() {
    }

    public static RemoteProjectDataSource getInstance() {
        return instance;
    }

    private ProjectApi api = RetrofitFactory.getInstance().create(ProjectApi.class);

    @Override
    public LiveData<StateModel<Projects>> queryProjects(int page) {
        MutableLiveData<StateModel<Projects>> data = new MutableLiveData<>();
        data.setValue(StateFactory.loading());
        api.queryProjects(page).enqueue(new Callback<Projects>() {
            @Override
            public void onResponse(Call<Projects> call, Response<Projects> response) {
                Projects projects = response.body();
                if (projects == null || projects.getItems() == null || projects.getItems().size() == 0) {
                    data.setValue(StateFactory.empty());
                } else {
                    data.setValue(StateFactory.content(projects));
                }
            }

            @Override
            public void onFailure(Call<Projects> call, Throwable t) {
                data.setValue(StateFactory.error(t));
            }
        });
        return data;
    }
}
