package com.example.mvvm.repository.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mvvm.model.User;
import com.example.mvvm.model.project.Projects;
import com.example.mvvm.repository.local.dao.ProjectsDao;
import com.example.mvvm.repository.local.dao.UserDao;

/**
 * 时间：2018/11/26 14:54
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
@Database(entities = {User.class, Projects.class}, version = 2)
public abstract class DB extends RoomDatabase {
    public abstract UserDao getUserDao();
    public abstract ProjectsDao getProjectsDao();
}
