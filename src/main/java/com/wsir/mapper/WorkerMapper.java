package com.wsir.mapper;

import com.wsir.entity.Canteen;
import com.wsir.entity.Card;
import com.wsir.entity.Worker;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface WorkerMapper {

    @Select("select * from worker where work_id = #{id}")
    Worker selectById(@Param("id") int id);

    @Select("select * from worker where work_id = #{workId} " +
            "and work_pwd = #{workPwd}")
    Worker matchWorker(Worker worker);

    @Update("update worker set work_pwd = #{workPwd} where work_id = #{workId}")
    int updatePwd(Worker worker);

    int pageSize(@Param("workId") int workId,
                 @Param("workName") String workName,
                 @Param("workSex") String workSex,
                 @Param("canId") int canId,
                 @Param("minSalary") int minSalary,
                 @Param("maxSalary") int maxSalary,
                 @Param("minEntryTime") LocalDateTime minEntryTime,
                 @Param("maxEntryTime") LocalDateTime maxEntryTime);

    List<Worker> selectPage(@Param("pageNum") int pageNum,
                            @Param("pageSize") int pageSize,
                            @Param("workId") int workId,
                            @Param("workName") String workName,
                            @Param("workSex") String workSex,
                            @Param("canId") int canId,
                            @Param("minSalary") int minSalary,
                            @Param("maxSalary") int maxSalary,
                            @Param("minEntryTime") LocalDateTime minEntryTime,
                            @Param("maxEntryTime") LocalDateTime maxEntryTime);

    @Insert("insert into worker(work_pwd, work_name, work_sex, work_phone, can_id, can_window, salary) " +
            "values(#{workPwd}, #{workName}, #{workSex}, #{workPhone}, #{canId}, #{canWindow}, #{salary})")
    int insert(Worker worker);

    @Update("update worker set work_name = #{workName}, work_sex = #{workSex}, work_phone = #{workPhone}," +
            "can_id = #{canId}, can_window = #{canWindow}, salary = #{salary}, entry_time = #{entryTime} " +
            "where work_id = #{workId}")
    int update(Worker worker);

    @Delete("delete from worker where work_id = #{workId}")
    int delete(int workId);

    @Update("update worker set work_pwd = #{workPwd} where work_id = #{workId}")
    int rePwd(Worker worker);
}
