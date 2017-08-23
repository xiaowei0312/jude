package com.sxsram.ssm.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sxsram.ssm.entity.OnlineJournalBook;
import com.sxsram.ssm.entity.OnlineJournalBookQueryVo;
import com.sxsram.ssm.service.OrderService;
import com.sxsram.ssm.util.Pagination;
import com.sxsram.ssm.util.QueryCondition;
import com.sxsram.ssm.util.QueryConditionAbstractItem;
import com.sxsram.ssm.util.QueryConditionItem;
import com.sxsram.ssm.util.QueryConditionOp;

public class OrderServiceTest {
	private ApplicationContext ctx = null;
	{
		ctx = new ClassPathXmlApplicationContext("classpath:spring/springmvc-junit.xml");
	}

	@Test
	public void testGetOnlieOrdersTotalNum() throws Exception {
		OrderService memberService = ctx.getBean(OrderService.class);

		OnlineJournalBookQueryVo onlineJournalBookQueryVo = new OnlineJournalBookQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		whereCondList.add(new QueryConditionItem("status", 0 + "", QueryConditionOp.EQ));
		onlineJournalBookQueryVo.setQueryCondition(new QueryCondition(whereCondList));

		Integer totalNum = memberService.getOnlineOrdersTotalNum(onlineJournalBookQueryVo);
		System.out.println(totalNum);
	}

	@Test
	public void testGetOnlieOrders() throws Exception {
		OrderService memberService = ctx.getBean(OrderService.class);

		List<OnlineJournalBook> books = null;
		OnlineJournalBookQueryVo onlineJournalBookQueryVo = new OnlineJournalBookQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		whereCondList.add(new QueryConditionItem("jour.status", 0 + "", QueryConditionOp.EQ));
		onlineJournalBookQueryVo.setQueryCondition(new QueryCondition(whereCondList));
		onlineJournalBookQueryVo.setPagination(new Pagination(5, 1, null, "{\"journalTime\":\"desc\"}"));

		books = memberService.getOnlineOrders(onlineJournalBookQueryVo);
		System.out.println(books);
		System.out.println(books.size());
	}
}
