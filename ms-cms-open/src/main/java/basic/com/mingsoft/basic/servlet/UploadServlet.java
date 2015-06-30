package com.mingsoft.basic.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.mingsoft.util.StringUtil;

/**
 * 
 * <p>
 * <b>mswx-铭飞微信酒店预订平台</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司path
 * </p>
 * 
 * @author wangtp
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科
 *          </p>
 * 
 *          <p>
 *          Comments: 文件上传通用servlet
 *          </p>
 * 
 *          <p>
 *          Create Date:2013-12-30
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
@WebServlet(urlPatterns = "/upload")
public class UploadServlet extends BaseServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String path = request.getContextPath();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		PrintWriter out = response.getWriter();
		SmartUpload smart = new SmartUpload();
		smart.initialize(this.getServletConfig(), request, response);
		// 初始化上传操作
		com.jspsmart.upload.File file = null;

		Request req = smart.getRequest();

		int i = 0;
		try {

			String maxSize = req.getParameter("maxSize"); // 上传的文件路径
			String allowedFile = req.getParameter("allowedFile"); // 允许上传文件
			String deniedFile = req.getParameter("deniedFile"); // 不允许上传文件

			if (!StringUtil.isBlank(allowedFile)) {
				smart.setAllowedFilesList(allowedFile);
			} else {
				// 定义允许上传文件类型
				smart.setAllowedFilesList("jpg,gif,png,css,js,rar,zip,doc,htm,html,ico,swf");
			}

			if (!StringUtil.isBlank(deniedFile)) {
				smart.setDeniedFilesList(deniedFile);
			} else {
				// 不允许上传文件类型
				smart.setDeniedFilesList("jsp,asp,php,aspx,exe,bat");
			}

			if (!StringUtil.isBlank(maxSize) && StringUtil.isInteger(maxSize)) {
				smart.setMaxFileSize(Integer.parseInt(maxSize));
			} else {
				// 单个文件最大限制
				smart.setMaxFileSize(2024 * 10024 * 1024);
			}
			smart.setCharset("utf-8");

			try {
				// 执行上传
				smart.upload();
			} catch (SecurityException e) {
				e.printStackTrace();
				if (e.getMessage().indexOf("allowed") >= 0) {
					out.print("allowed error");
				} else if (e.getMessage().indexOf("Size") >= 0) {
					out.print("size error");
				}
				out.flush();
				return;
			}

			String uploadPath = req.getParameter("uploadPath"); // 上传的文件路径
			String isRename = req.getParameter("isRename");

			if (!StringUtil.isBlank(uploadPath)) {
				// 如果文件夹不存在就创建
				File updateFile = new File(this.getServletContext().getRealPath(File.separator) + uploadPath);
				if (!updateFile.exists()) {
					updateFile.mkdirs();
				}
			} else {
				uploadPath = "/upload";
			}

			// 得到单个上传文件的信息
			file = smart.getFiles().getFile(0);
			// 设置文件在服务器的保存位置

			String filepath = uploadPath + "/";

			String fileName = System.currentTimeMillis() + "." + file.getFileExt();
			// 重命名
			if (StringUtil.isBlank(isRename) ||Boolean.parseBoolean(isRename)) {
				filepath += fileName;
			} else {
				filepath += file.getFileName();// 原始名称;
			}
			filepath = StringUtil.removeRepeatStr(filepath, File.separator);
			// 文件另存为
			file.setCharset("utf-8");
			file.saveAs(filepath, SmartUpload.SAVE_AUTO);
			out.print(filepath);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = request.getParameter("uploadPath"); // 上传的文件路径
		String fileSize = request.getParameter("fileSize"); // 上传的文件大小
		String fileType = request.getParameter("fileType"); // 上传的文件类型
		String deniedFileType = request.getParameter("deniedFileType"); // 不允许上传的文件类型，
	}
}