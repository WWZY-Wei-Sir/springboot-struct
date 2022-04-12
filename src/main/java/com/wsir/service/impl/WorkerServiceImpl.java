package com.wsir.service.impl;

import com.wsir.entity.Card;
import com.wsir.entity.Worker;
import com.wsir.exception.ServiceException;
import com.wsir.mapper.CardMapper;
import com.wsir.mapper.WorkerMapper;
import com.wsir.service.WorkerService;
import com.wsir.util.Constants;
import com.wsir.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public Worker selectById(int id) {
        return workerMapper.selectById(id);
    }

    @Override
    public Worker matchWorker(Worker worker) {
        Worker newWorker;
        try {
            newWorker = workerMapper.matchWorker(worker);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "服务器错误");
        }
        if (newWorker != null) {
            String token = TokenUtils.createToken(Constants.ROLE_WORKER,
                    Integer.toString(newWorker.getWorkId()),
                    newWorker.getWorkPwd());
            newWorker.setRole(Constants.ROLE_WORKER);
            newWorker.setToken(token);
        } else {
            throw new ServiceException(Constants.CODE_400, "员工id或密码错误");
        }
        return newWorker;
    }

    @Override
    public int updatePwd(Worker worker) {
        return workerMapper.updatePwd(worker);
    }

    @Override
    public int pageSize(int workId,
                        String workName,
                        String workSex,
                        int canId,
                        int minSalary,
                        int maxSalary,
                        LocalDateTime minEntryTime,
                        LocalDateTime maxEntryTime) {
        return workerMapper.pageSize(workId, workName, workSex, canId,
                minSalary, maxSalary, minEntryTime, maxEntryTime);
    }

    @Override
    public List<Worker> selectPage(int pageNum,
                                   int pageSize,
                                   int workId,
                                   String workName,
                                   String workSex,
                                   int canId,
                                   int minSalary,
                                   int maxSalary,
                                   LocalDateTime minEntryTime,
                                   LocalDateTime maxEntryTime) {
        return workerMapper.selectPage(pageNum, pageSize, workId, workName, workSex, canId,
                minSalary, maxSalary, minEntryTime, maxEntryTime);
    }

    @Override
    public int insert(Worker worker) {
        return workerMapper.insert(worker);
    }

    @Override
    public int update(Worker worker) {
        return workerMapper.update(worker);
    }

    @Override
    public int delete(int workId) {
        return workerMapper.delete(workId);
    }

    @Override
    public int rePwd(Worker worker) {
        return workerMapper.rePwd(worker);
    }
}
