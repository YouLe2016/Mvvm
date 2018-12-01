package com.example.mvvm.view.project;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.mvvm.model.project.Projects;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.repository.ProjectRepository;


/**
 * 时间：2018/11/28 16:00
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class ProjectViewModel extends ViewModel {

    private LiveData<StateModel<Projects>> projects;
    private MutableLiveData<Integer> page;

    public LiveData<StateModel<Projects>> getProjects() {
        if (projects == null) {
            this.page = new MutableLiveData<>();
            projects = Transformations.switchMap(this.page, page -> ProjectRepository.getInstance().getProject(page));
//            LiveData<LiveData<StateModel<Projects>>> map = Transformations.map(this.page, input -> ProjectRepository.getInstance().getProject(input));
        }
        return projects;
    }

    public void load(int page) {
        this.page.setValue(page);
    }
}
