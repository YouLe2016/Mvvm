package com.example.mvvm.repository;

import android.arch.lifecycle.LiveData;

import com.example.mvvm.model.project.Projects;
import com.example.mvvm.model.ui.StateModel;

/**
 * 时间：2018/11/28 16:01
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public interface ProjectDataSource {

    LiveData<StateModel<Projects>> queryProjects(int page);
}
