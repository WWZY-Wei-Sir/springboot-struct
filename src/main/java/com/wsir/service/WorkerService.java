package com.wsir.service;

import com.wsir.entity.Card;
import com.wsir.entity.Worker;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkerService {

    Worker selectById(int id);

    Worker matchWorker(Worker worker);

    int updatePwd(Worker worker);

    int pageSize(int workId,
                 String workName,
                 String workSex,
                 int canId,
                 int minSalary,
                 int maxSalary,
                 LocalDateTime minEntryTime,
                 LocalDateTime maxEntryTime);

    List<Worker> selectPage(int pageNum,
                            int pageSize,
                            int workId,
                            String workName,
                            String workSex,
                            int canId,
                            int minSalary,
                            int maxSalary,
                            LocalDateTime minEntryTime,
                            LocalDateTime maxEntryTime);

    int insert(Worker worker);

    int update(Worker worker);

    int delete(int workId);

    int rePwd(Worker worker);
}
