package com.sxsram.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxsram.ssm.entity.Log;
import com.sxsram.ssm.mapper.LogMapper;
import com.sxsram.ssm.service.LogService;

@Service("LogService")
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper logMapper;
	@Override
	public void addLog(Log log) {
		System.out.println(log);
		logMapper.addLog(log);
	}
}
