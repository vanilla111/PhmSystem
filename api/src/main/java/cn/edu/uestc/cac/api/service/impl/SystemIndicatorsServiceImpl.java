package cn.edu.uestc.cac.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.uestc.cac.api.service.SystemIndicatorsService;
import cn.edu.uestc.cac.api.utils.DataDecodeUtil;
import cn.edu.uestc.cac.dao.entity.SystemIndicators;
import cn.edu.uestc.cac.dao.mapper.SystemIndicatorsMapper;

/**
 * @author wang
 */
@Service
public class SystemIndicatorsServiceImpl implements SystemIndicatorsService {

    private final Logger logger = LoggerFactory.getLogger(SystemIndicatorsServiceImpl.class);

    @Autowired
    private SystemIndicatorsMapper mapper;

    public void save(SystemIndicators indicators) {
        String load = DataDecodeUtil.base64Decode(indicators.getLoad());
        String[] loadArr = load.split(" ");
        if (loadArr.length == 3) {
            indicators.setLoadAvg1(DataDecodeUtil.stringToFloat(loadArr[0]));
            indicators.setLoadAvg5(DataDecodeUtil.stringToFloat(loadArr[1]));
            indicators.setLoadAvg15(DataDecodeUtil.stringToFloat(loadArr[2]));
        }
        long memTotal = indicators.getRamTotal();
        long memUsage = indicators.getRamUsage();
        float percent = memUsage * 1.0F / memTotal * 100;
        indicators.setLoadIo(indicators.getLoadIo() / 100F);
        indicators.setLoadCpu(indicators.getLoadCpu() / 100F);
        indicators.setLoadMem(percent);
        mapper.insert(indicators);
    }
}
