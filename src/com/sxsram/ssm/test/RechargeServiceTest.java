package com.sxsram.ssm.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sxsram.ssm.entity.OnlineJournalBook;
import com.sxsram.ssm.entity.OnlineJournalBookQueryVo;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordExpand;
import com.sxsram.ssm.entity.RechargeAndWithDrawRecordQueryVo;
import com.sxsram.ssm.service.OrderService;
import com.sxsram.ssm.service.RechargeAndWithdrawService;
import com.sxsram.ssm.util.Pagination;
import com.sxsram.ssm.util.QueryCondition;
import com.sxsram.ssm.util.QueryConditionAbstractItem;
import com.sxsram.ssm.util.QueryConditionItem;
import com.sxsram.ssm.util.QueryConditionOp;

public class RechargeServiceTest {
	private ApplicationContext ctx = null;
	{
		ctx = new ClassPathXmlApplicationContext("classpath:spring/springmvc-junit.xml");
	}

	@Test
	public void testQuery() throws Exception {
		RechargeAndWithdrawService service = ctx.getBean(RechargeAndWithdrawService.class);

		List<RechargeAndWithDrawRecordExpand> items = null;
		RechargeAndWithDrawRecordQueryVo recordQueryVo = new RechargeAndWithDrawRecordQueryVo();
		List<QueryConditionAbstractItem> whereCondList = new ArrayList<QueryConditionAbstractItem>();
		whereCondList.add(new QueryConditionItem("r.id", 2895 + "", QueryConditionOp.EQ));
		recordQueryVo.setQueryCondition(new QueryCondition(whereCondList));
		//recordQueryVo.setPagination(new Pagination(5, 1, null, "{\"journalTime\":\"desc\"}"));

		items = service.getRechargeAndWithdrawRecords(recordQueryVo);

		System.out.println(items.size());
		for (RechargeAndWithDrawRecordExpand record : items) {
			System.out.println(record);
			System.out.println(record.getUser().getUsername() + "\t" + record.getUser().getPhone());
		}
	}
}
