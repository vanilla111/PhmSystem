package cn.edu.uestc.cac.api.service.impl;

import org.apache.ibatis.annotations.Mapper;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.uestc.cac.api.service.SystemIndicatorsService;
import cn.edu.uestc.cac.dao.mapper.SystemIndicatorsMapper;

/**
 * @author wang
 */
@Mapper
public class SystemIndicatorsServiceImpl implements SystemIndicatorsService {

    @Autowired
    private SystemIndicatorsMapper mapper;


}
