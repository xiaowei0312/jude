package com.sxsram.ssm.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sxsram.ssm.entity.GlobalPrams;
import com.sxsram.ssm.mapper.GlobalParamMapper;

public class GlobalParamMapperTest {
	private ApplicationContext ctx = null;
	private SqlSessionFactory sqlSessionFactory;
	{
		ctx = new ClassPathXmlApplicationContext("classpath:spring/springmvc-junit.xml");
		sqlSessionFactory = ctx.getBean(SqlSessionFactory.class);
	}

	@Test
	public void testQueryGlobalParams() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		GlobalParamMapper globalParamMapper = sqlSession.getMapper(GlobalParamMapper.class);
		GlobalPrams params = globalParamMapper.queryCurrentGlobalParams();
		System.out.println(params);
		sqlSession.close();
	}

	@Test
	public void testInsertNewGlobalParam() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		GlobalParamMapper globalParamMapper = sqlSession.getMapper(GlobalParamMapper.class);
		GlobalPrams globalPrams = new GlobalPrams();
		globalPrams.setBeginTime(null);
		globalPrams.setBusJfRatio(200.0);
		globalPrams.setCashByOneDlb(100.0);
		globalParamMapper.insertNewGlobalParam(globalPrams);;
		System.out.println(globalPrams);
		sqlSession.close();
	}

	@Test
	public void testUpdateGlobalParamFlag() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		GlobalParamMapper globalParamMapper = sqlSession.getMapper(GlobalParamMapper.class);
		GlobalPrams globalPrams = new GlobalPrams();
		globalPrams.setId(1);
		globalPrams.setFlag(1);
		globalParamMapper.updateGlobalParamFlag(globalPrams);
		System.out.println(globalPrams);
		sqlSession.close();
	}

}
