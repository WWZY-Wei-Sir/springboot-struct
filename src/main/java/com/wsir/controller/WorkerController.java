package com.wsir.controller;

import cn.hutool.core.util.StrUtil;
import com.wsir.entity.Worker;
import com.wsir.service.WorkerService;
import com.wsir.util.Constants;
import com.wsir.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping("/login")
    public Result matchWorker(@RequestBody Worker worker) {
        if (worker.getWorkId() == 0 || StrUtil.isBlank(worker.getWorkPwd())) {
            return Result.error(Constants.CODE_400, "员工id为空或密码为空");
        } else {
            Worker newWorker = workerService.matchWorker(worker);
            return Result.success(newWorker);
        }
    }

    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody Worker worker) {
        workerService.updatePwd(worker);
        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam int pageNum,
                             @RequestParam int pageSize,
                             @RequestParam int workId,
                             @RequestParam String workName,
                             @RequestParam String workSex,
                             @RequestParam int canId,
                             @RequestParam int minSalary,
                             @RequestParam int maxSalary,
                             @RequestParam String minEntryTimeStr,
                             @RequestParam String maxEntryTimeStr) {
        LocalDateTime minEntryTime = null;
        LocalDateTime maxEntryTime = null;
        if (StrUtil.isBlank(workName)) {
            workName = null;
        }
        if (StrUtil.isBlank(workSex)) {
            workSex = null;
        }
        if (!StrUtil.isBlank(minEntryTimeStr) ) {
            LocalDate localDate = LocalDate.parse(minEntryTimeStr);
            minEntryTime = localDate.atStartOfDay();
        }
        if (!StrUtil.isBlank(maxEntryTimeStr) ) {
            LocalDate localDate = LocalDate.parse(maxEntryTimeStr);
            maxEntryTime = localDate.atStartOfDay();
        }
        pageNum = (pageNum - 1) * pageSize;
        List<Worker> workers = workerService.selectPage(pageNum, pageSize, workId, workName, workSex,
                canId, minSalary, maxSalary, minEntryTime, maxEntryTime);
        int total = workerService.pageSize(workId, workName, workSex, canId,
                minSalary, maxSalary, minEntryTime, maxEntryTime);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", workers);
        System.out.println(total);
        return Result.success(map);
    }

    @PostMapping("/insertOrUpdate")
    public Result insertOrUpdate(@RequestBody Worker worker) {
        if (worker.getIsNew()) {
            workerService.insert(worker);
        } else {
            workerService.update(worker);
        }
        return Result.success();
    }

    @DeleteMapping("/deleteOne/{workId}")
    public Result deleteOne(@PathVariable Integer workId) {
        workerService.delete(workId);
        return Result.success();
    }

    @PostMapping("/deleteMany")
    public Result deleteMany(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            workerService.delete(id);
        }
        return Result.success();
    }

    @PostMapping("/rePwd")
    public Result rePwd(@RequestBody Worker worker) {
        workerService.rePwd(worker);
        return Result.success();
    }
}
