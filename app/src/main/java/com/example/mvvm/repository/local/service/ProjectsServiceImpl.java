package com.example.mvvm.repository.local.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.mvvm.model.project.Projects;
import com.example.mvvm.repository.local.dao.ProjectsDao;
import com.example.mvvm.repository.local.db.DBHelper;
import com.example.mvvm.utils.LogUtils;

/**
 * 时间：2018/12/1 9:55
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class ProjectsServiceImpl implements ProjectsService {
    private static final ProjectsServiceImpl ourInstance = new ProjectsServiceImpl();

    public static ProjectsServiceImpl getInstance() {
        return ourInstance;
    }

    private ProjectsServiceImpl() {
    }

    private ProjectsDao projectsDao = DBHelper.getInstance().getDb().getProjectsDao();

    @Override
    public LiveData<Long> add(Projects projects) {
        MutableLiveData<Long> data = new MutableLiveData<>();
        new AddProjectsTask().init(projectsDao, data).execute(projects);
        return data;
    }

    @Override
    public LiveData<Projects> queryProjects(int page) {
        return projectsDao.queryProjects(page);
    }


    private static class AddProjectsTask extends AsyncTask<Projects, Void, Long> {
        ProjectsDao projectsDao;
        MutableLiveData<Long> result;

        private AddProjectsTask init(ProjectsDao projectsDao, MutableLiveData<Long> result) {
            this.projectsDao = projectsDao;
            this.result = result;
            return this;
        }

        @Override
        protected Long doInBackground(Projects... Projects) {
            return projectsDao.add(Projects[0]);
        }

        @Override
        protected void onPostExecute(Long rowId) {
            result.setValue(rowId);
        }
    }


}
