package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/user")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");			// 제일 위에서 먼저 실행
		System.out.println("GET 요청");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		Map<String, String> userMap = new HashMap<>();
		userMap.put("name", name);
		userMap.put("phone", phone);
		
		String userJson = gson.toJson(userMap);
		System.out.println(userJson);
		/**
		 * 	1. 주소창, src href, replace 등으로 요청할 수 있음.
		 * 	2. 데이터를 전달하는 방법(Query String)
		 * 		http(s)://서버주소:포트/요청메세지?key=value&key=value
		 */
		System.out.println(response.getCharacterEncoding());
		
//		response.addHeader("Content-Type", "text/html;charset=utf-8");		// Content-Type: html파일이나, 문자열로 읽을지 결정
//		response.addHeader("test", "test-data");
//		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json;charset=UTF-8");		// json으로 보낼 땐 application/json 사용
		/**
		 * 	- Content-Type: text/css
			- Content-Type: text/html
			- Content-Type: text/javascript
			- Content-Type: text/plain		// 일반 문자열
			- Content-Type: text/xml
		 */
		
//		response.setCharacterEncoding("UTF-8");			// response.getWriter()를 하기 전에 encoding이 일어나야 함
		PrintWriter out = response.getWriter();
//		out.println("이름: " + name);
//		out.println("연락처: " + phone);
		out.println(userJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST 요청");
		Gson gson = new Gson();
		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("address"));
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		String json = "";
		String line = null;
		while((line = bufferedReader.readLine()) != null) {
			json += line;
		}
		json.replaceAll(" ", "");
		System.out.println(json);
		
//		AtomicReference<String> jsonAtomic = new AtomicReference<>("");
//		bufferedReader.lines().forEach(line -> {
//			jsonAtomic.getAndUpdate(t -> t + line);
//		});
//		String json = jsonAtomic.get().replaceAll(" ", "");
//		System.out.println(json);
		
		Map<String, String> jsonMap = gson.fromJson(json, Map.class);
		System.out.println(jsonMap);
		
		/**
		 * 	1.
		 * 	<form action = "요청 메세지" method = "post">
		 * 		<input name="key" value="value" />
		 * 		<button type="submit">요청</button>
		 * 	</form>
		 */
	}

}
