package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.IDinnerTableDao;
import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.TableStatus;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IDinnerTableService;

public class DinnerTableService implements IDinnerTableService {

	// ���õ�Dao, ͨ����������ʵ��
	private IDinnerTableDao dinnerTableDao = BeanFactory.getInstance(
			"dinnerTableDao", IDinnerTableDao.class);

	@Override
	public DinnerTable findById(int id) {
		try {
			return dinnerTableDao.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> findNoUseTable() {
		try {
			// ����dao�ĸ���״̬��ѯ�ķ�������ѯû��Ԥ���Ĳ���
			return dinnerTableDao.findByStatus(TableStatus.Free);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
