package com.example.mvvm.repository.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.mvvm.model.User;

/**
 * 时间：2018/11/26 14:46
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long add(User user);

    @Query("select * from user where login = :username")
    User queryByUsername(String username) throws Exception;
}
