package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.DinnerTable;

public interface IDinnerTableService {

	/**
	 * ��ѯ����δԤ���Ĳ���
	 */
	List<DinnerTable> findNoUseTable();
	
	/**
	 * ����������ѯ
	 */
	DinnerTable findById(int id);
}
