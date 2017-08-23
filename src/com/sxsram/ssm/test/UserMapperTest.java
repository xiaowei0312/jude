package com.sxsram.ssm.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sxsram.ssm.entity.Address;
import com.sxsram.ssm.entity.CommodityType;
import com.sxsram.ssm.entity.Indent;
import com.sxsram.ssm.entity.JournalBook;
import com.sxsram.ssm.entity.JournalBookExpand;
import com.sxsram.ssm.entity.ShoppingMallExpand;
import com.sxsram.ssm.entity.User;
import com.sxsram.ssm.entity.UserExpand;
import com.sxsram.ssm.mapper.UserMapper;

public class UserMapperTest {
	private ApplicationContext ctx = null;
	private SqlSessionFactory sqlSessionFactory;
	{
		ctx = new ClassPathXmlApplicationContext("classpath:spring/springmvc-junit.xml");
		sqlSessionFactory = ctx.getBean(SqlSessionFactory.class);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInsertNewUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		UserExpand userExpand = new UserExpand();
		userExpand.setOpenId("oVX5hwLoTdw1iWTiI5BY0O-NkNY");
		//userExpand.setCity("TY");
		//userExpand.setProvince("SX");
		userExpand.setHeadImgUrl("http://test.com");
		userExpand.setPhone("181343535");
		try {
			userMapper.insertNewUser(userExpand);
			System.out.println(userExpand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryUserByOpenId() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			UserExpand userExpand = userMapper.queryUserByOpenId("oVX5hwLoTdw1iWTiI5BY0O-NkNmU");
			System.out.println(userExpand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryUserById() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			UserExpand userExpand = userMapper.queryUserById(1);
			System.out.println(userExpand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryAllShoppingMalls() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			List<ShoppingMallExpand> malls = userMapper.queryAllShoppingMalls();
			System.out.println(malls);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryAddress() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			List<Address> addresses = userMapper.queryEparchies(1);
			System.out.println(addresses);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetMoneyAccountBalance() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			Double balance = userMapper.getMoneyAccountBalance(1);
			System.out.println(balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindIndentById() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			Indent indent = userMapper.findIndentByIndentNo("12345");
			if (indent != null)
				System.out.println(indent.getId() + "," + indent.getIndentNo() + "," + indent.getFlag());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryUserByPhone() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			// UserExpand userExpand =
			// userMapper.queryUserByPhone("18135100170");
			UserExpand userExpand = userMapper.queryUserByPhone("13546712419");

			System.out.println(userExpand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertNewJournalBookItem() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			JournalBookExpand journalBookExpand = new JournalBookExpand();
			journalBookExpand.setAmount(1000);
			journalBookExpand.setCommodityName("电视机");
			journalBookExpand.setFlag(0);

			UserExpand client = new UserExpand();
			UserExpand business = new UserExpand();
			Indent indent = new Indent();
			indent.setId(1);
			client.setId(1);
			business.setId(2);
			journalBookExpand.setBusiness(business);
			journalBookExpand.setClient(client);
			journalBookExpand.setIndent(indent);
			journalBookExpand.setPremiumRates((float)0.01);
			userMapper.insertNewJournalBookItem(journalBookExpand);

			System.out.println(journalBookExpand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryCommodityByParentId() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			List<CommodityType> types = userMapper.queryCommodityByParentId(1);
			System.out.println(types.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
