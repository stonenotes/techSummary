package cn.itcast.service;

import cn.itcast.entity.Food;
import cn.itcast.utils.PageBean;

public interface IFoodService {

	/**
	 * ������ѯ
	 */
	Food findById(int id);

	/**
	 * ��ҳ��ѯ
	 */
	void getAll(PageBean<Food> pb);
}
