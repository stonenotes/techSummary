package cn.itcast.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.DinnerTable;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IDinnerTableService;
import cn.itcast.utils.WebUtils;

public class IndexServlet extends BaseServlet {

	/*
	 * // ����Service private IDinnerTableService dinnerTableService =
	 * BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
	 * 
	 * 
	 * 
	 * public void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * 
	 * // ��ȡ���������� String method = request.getParameter("method"); if (method ==
	 * null) { // Ĭ��ִ�еķ����� ����ǰ̨�б����ҳ method = "listTable"; }
	 * 
	 * // �ж� if ("listTable".equals(method)) { // 1. ǰ̨��ҳ����ʾ����δԤ���Ĳ���
	 * listTable(request,response); }
	 * 
	 * }
	 * 
	 * public void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { this.doGet(request,
	 * response); }
	 */

	// 1. ǰ̨��ҳ����ʾ����δԤ���Ĳ���
	public Object listTable(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ������ת��Դ(ת��/�ض���)
		Object uri = null;
		// ��ѯ����δԤ������
		List<DinnerTable> list = dinnerTableService.findNoUseTable();
		// ���浽request
		request.setAttribute("listDinnerTable", list);
		// ��ת����ҳ��ʾ
		uri = request.getRequestDispatcher("/app/index.jsp");
		return uri;

		// ��ת
		// WebUtils.goTo(request, response, uri);
	}

}
