package com.example.mvvm.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.mvvm.model.project.Projects;

/**
 * 时间：2018/12/1 9:38
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
@Dao
public interface ProjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long add(Projects projects);

    @Query("select * from projects where page = :page")
    LiveData<Projects> queryProjects(int page);
}
