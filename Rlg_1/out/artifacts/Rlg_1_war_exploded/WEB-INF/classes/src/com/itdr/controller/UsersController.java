package com.itdr.controller;

import com.itdr.common.ResponseCode;

import com.itdr.pojo.Users;
import com.itdr.service.UserService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.itdr.utils.PathUTil.getpath;

@WebServlet("/manage/user/*")
public class UsersController extends HttpServlet {
    private UserService uc=new  UserService();
    protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

            String pathInfo=request.getPathInfo();
            String path= getpath(pathInfo);
            ; ResponseCode rs=null;
            switch (path){
                case "list":
                   rs= lisDo(request,response);
                   break;
                case "login":
                    loginDo(request,response);
                    break;
                case "disableuser":
                    rs=disableuserDo(request);
                    break;
            }

          //   response.getWriter().write(rs.toString());
    }
    private ResponseCode lisDo(HttpServletRequest request,HttpServletResponse response){
        ResponseCode rs=new ResponseCode();
        HttpSession session=request.getSession();
        Users user=(Users) session.getAttribute("user");
        if(user==null){
            rs.setStatus(3);
            rs.setMag("请登录后操作！");
            return  rs;
        }
        if(user.getType()!=1){
            rs.setStatus(3);
            rs.setMag("没有操作权限！");
            return  rs;
        }
        String pageSize= request.getParameter("pageSize");
        String pageNum=request.getParameter("pageNum");
        rs=uc.selectAll(pageSize,pageNum);
        request.setAttribute("uli",rs);
        try {
            request.getRequestDispatcher("/WEB-INF/userlist.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  rs;

    }
    private void loginDo(HttpServletRequest request,HttpServletResponse response ){
        String username= request.getParameter("username");
        String password=request.getParameter("password");
        ResponseCode rs= uc.selectOne(username, password);
        HttpSession session =request.getSession();
        session.setAttribute("user",rs.getData());

        try {
          //  response.sendRedirect("/WEB-INF/login.jsp");
            response.getWriter().write(rs.toString());

           request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private ResponseCode disableuserDo(HttpServletRequest request){
        String id= request.getParameter("id");

        ResponseCode rs = uc.selectOne(id);

        return  rs;
    }
    private ResponseCode yzDo(HttpServletRequest request){
        String username= request.getParameter("username");

        ResponseCode rs = uc.selectOne(username);

        return  rs;
    }
}
