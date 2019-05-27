package cn.itcast.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.dao.IFoodDao;
import cn.itcast.entity.Food;
import cn.itcast.utils.Condition;
import cn.itcast.utils.JdbcUtils;
import cn.itcast.utils.PageBean;

public class FoodDao implements IFoodDao{

	@Override
	public Food findById(int id) {
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append("		f.id,");
		sb.append("		f.foodName,");
		sb.append("		f.price,");
		sb.append("		f.mprice,");
		sb.append("		f.remark,");
		sb.append("		f.img,");
		sb.append("		f.foodType_id,");
		sb.append("		t.typeName ");
		sb.append("from ");
		sb.append("		food f, ");
		sb.append("		foodtype t ");
		sb.append("where 1=1");
		sb.append("		and f.foodType_id=t.id");
		
		try {
			return JdbcUtils.getQuerrRunner().query(sb.toString(), new BeanHandler<Food>(Food.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void getAll(PageBean<Food> pb) {
		// ��ȡ��������
		Condition condition = pb.getCondition();
		// ����֮���id
		int typeId = condition.getFoodTypeId();
		// ����֮��Ʒ����
		String foodName = condition.getFoodName();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append("		f.id,");
		sb.append("		f.foodName,");
		sb.append("		f.price,");
		sb.append("		f.mprice,");
		sb.append("		f.remark,");
		sb.append("		f.img,");
		sb.append("		f.foodType_id,");
		sb.append("		t.typeName ");
		sb.append("from ");
		sb.append("		food f, ");
		sb.append("		foodtype t ");
		sb.append("where 1=1");
		sb.append("		and f.foodType_id=t.id ");
		// �洢��ѯ������Ӧ��ֵ
		List<Object> list = new ArrayList<Object>();
		/*******ƴ�Ӳ�ѯ����*********/
		// ���id����
		if (typeId > 0) {
			sb.append("	and f.foodType_id=?");
			list.add(typeId);  // ��װ����ֵ
		}
		// ��Ʒ����
		if (foodName != null && !"".equals(foodName.trim())) {
			sb.append("  and f.foodName like ?");
			list.add(foodName);    // ��װ����ֵ
		}
		
		/*********��ҳ����**********/
		sb.append(" LIMIT ?,?");
		
		
		/*****�жϣ�����ǰҳ< 1�� ���õ�ǰҳΪ1��  ����ǰҳ>��ҳ�������õ�ǰҳΪ��ҳ��******/
		// �Ȳ�ѯ�ܼ�¼��
		int totalCount = getTotalCount(pb);  //?
		// ���÷�ҳbean����֮�ܼ�¼��
		pb.setTotalCount(totalCount);
		
		if(pb.getCurrentPage() < 1) {
			pb.setCurrentPage(1);
		} else if (pb.getCurrentPage() > pb.getTotalPage()) {
			pb.setCurrentPage(pb.getTotalPage());
		}
		
		// ��ʼ��
		int index = (pb.getCurrentPage() - 1) * pb.getPageCount();
		// ���ؼ�¼��
		int count = pb.getPageCount();
		
		list.add(index);		    // ��װ����ֵ - ��ʼ��
		list.add(count);		    // ��װ����ֵ - ��ѯ���ص���
		
		
		// ����������ҳ��ѯ
		try {
			List<Food> pageData = JdbcUtils.getQuerrRunner().
				query(sb.toString(), new BeanListHandler<Food>(Food.class),list.toArray());
			// �Ѳ�ѯ�����������õ���ҳ������
			pb.setPageData(pageData);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int getTotalCount(PageBean<Food> pb) {
		// ��ȡ��������
		Condition condition = pb.getCondition();
		// ����֮���id
		int typeId = condition.getFoodTypeId();
		// ����֮��Ʒ����
		String foodName = condition.getFoodName();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append("		count(*) ");
		sb.append("from ");
		sb.append("		food f, ");
		sb.append("		foodtype t ");
		sb.append("where 1=1");
		sb.append("		and f.foodType_id=t.id ");
		// �洢��ѯ������Ӧ��ֵ
		List<Object> list = new ArrayList<Object>();
		/*******ƴ�Ӳ�ѯ����*********/
		// ���id����
		if (typeId > 0) {
			sb.append("	and f.foodType_id=?");
			list.add(typeId);  // ��װ����ֵ
		}
		// ��Ʒ����
		if (foodName != null && !"".equals(foodName.trim())) {
			sb.append("  and f.foodName like ?");
			list.add(foodName);    // ��װ����ֵ
		}
		
		try {
			// ��ѯ
			Long num = JdbcUtils.getQuerrRunner().query(sb.toString(), new ScalarHandler<Long>(),list.toArray());
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}




















