package cn.edu.uestc.cac.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.uestc.cac.api.service.impl.SystemIndicatorsServiceImpl;
import cn.edu.uestc.cac.api.utils.Result;
import cn.edu.uestc.cac.dao.entity.SystemIndicators;

/**
 * @author wang
 */
@RestController
@RequestMapping("/indicators")
public class CollectionController {

    @Autowired
    SystemIndicatorsServiceImpl systemIndicatorsService;

    @PostMapping
    public Result<String> save(@RequestParam(name = "token", defaultValue = "test", required = false) String authToken,
                               @RequestParam(name = "version") int version,
                               @RequestParam(name = "uptime") int uptime,
                               @RequestParam(name = "sessions") int sessions,
                               @RequestParam(name = "processes") int processes,
                               @RequestParam(name = "fileHandles") int fileHandles,
                               @RequestParam(name = "fileHandlesLimit") int fileHandlesLimit,
                               @RequestParam(name = "ramTotal") long ramTotal,
                               @RequestParam(name = "ramUsage") long ramUsage,
                               @RequestParam(name = "swapTotal") long swapTotal,
                               @RequestParam(name = "swapUsage") long swapUsage,
                               @RequestParam(name = "diskTotal") long diskTotal,
                               @RequestParam(name = "diskUsage") long diskUsage,
                               @RequestParam(name = "diskRwTime") int diskRwTime,
                               @RequestParam(name = "connections") int connections,
                               @RequestParam(name = "ipv4") String ipv4,
                               @RequestParam(name = "rx") long rx,
                               @RequestParam(name = "tx") long tx,
                               @RequestParam(name = "load") String load,
                               @RequestParam(name = "loadCpu") float loadCpu,
                               @RequestParam(name = "loadIo") float loadIo,
                               @RequestParam(name = "label") int label) {
        SystemIndicators indicators = new SystemIndicators();
        indicators.setVersion(version);
        indicators.setUptime(uptime);
        indicators.setSessions(sessions);
        indicators.setProcesses(processes);
        indicators.setFileHandles(fileHandles);
        indicators.setFileHandlesLimit(fileHandlesLimit);
        indicators.setRamTotal(ramTotal);
        indicators.setRamUsage(ramUsage);
        indicators.setSwapTotal(swapTotal);
        indicators.setSwapUsage(swapUsage);
        indicators.setDiskTotal(diskTotal);
        indicators.setDiskUsage(diskUsage);
        indicators.setDiskRwTime(diskRwTime);
        indicators.setConnections(connections);
        indicators.setIpv4(ipv4);
        indicators.setRx(rx);
        indicators.setTx(tx);
        indicators.setLoad(load);
        indicators.setLoadCpu(loadCpu);
        indicators.setLoadIo(loadIo);
        indicators.setLabel(label);
        systemIndicatorsService.save(indicators);
        return Result.success();
    }
}
