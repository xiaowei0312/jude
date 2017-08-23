package com.sxsram.ssm.mapper;

public interface SystemOverviewMapper {
	/**
	 * 获取用户充值总额
	 * 
	 * @return
	 * @throws Exception
	 */
	public Double queryTotalRecharges() throws Exception;

	/**
	 * 获取用户提现总额
	 * 
	 * @return
	 * @throws Exception
	 */
	public Double queryTotalWithDraws() throws Exception;

	/**
	 * 获取今日用户充值总额
	 * 
	 * @return
	 * @throws Exception
	 */
	public Double queryTotalRechargesWithToday() throws Exception;

	/**
	 * 获取今日用户提现总额
	 * 
	 * @return
	 * @throws Exception
	 */
	public Double queryTotalWithDrawsWithToday() throws Exception;

	/**
	 * 获取平台总收入
	 */
	public Double queryTotalIncomings() throws Exception;

	/**
	 * 获取平台总支出
	 */
	public Double queryTotalOutgoings() throws Exception;

	/**
	 * 获取今日平台总收入
	 */
	public Double queryTotalIncomingsWithToday() throws Exception;

	/**
	 * 获取今日平台总支出
	 */
	public Double queryTotalOutgoingsWithToday() throws Exception;
	
	/**
	 * 获取目前的得利宝总数
	 * @return
	 * @throws Exception
	 */
	public Integer queryTotalPlatformDlbs() throws Exception;
}
