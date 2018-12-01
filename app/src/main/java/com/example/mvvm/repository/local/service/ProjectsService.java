package com.example.mvvm.repository.local.service;

import android.arch.lifecycle.LiveData;

import com.example.mvvm.model.project.Projects;

/**
 * 时间：2018/12/1 9:52
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public interface ProjectsService {

    LiveData<Long> add(Projects projects);

    LiveData<Projects> queryProjects(int page);
}
