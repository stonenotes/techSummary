package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.TableStatus;

public interface IDinnerTableDao {

	/**
	 * ����Ԥ��״̬��ѯ
	 */
	List<DinnerTable> findByStatus(TableStatus ts);

	/**
	 * ������ѯ
	 */
	DinnerTable findById(int id);

}
