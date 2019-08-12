package com.itdr.service;

import com.itdr.common.Const;
import com.itdr.common.ResponseCode;
import com.itdr.dao.UserDao;
import com.itdr.pojo.Users;

import java.util.List;

public class UserService {
    private  UserDao ud =new UserDao();
    public ResponseCode selectAll( String pageSize, String pageNum ) {
        if(pageSize==null||pageSize.equals("")){
            pageSize="10";
        }
        if(pageNum==null||pageNum.equals("")){
            pageNum="1";
        }


        List<Users> li=ud.selectAll(pageSize,pageNum);
        ResponseCode rs=new ResponseCode();
        rs.setData(li);
        return  rs;
    }
    public ResponseCode selectOne ( String username, String password ){
        ResponseCode rs =new ResponseCode();
        if(username==null||username.equals("")||password==null||password.equals("")){
              rs.setStatus(1);
              rs.setMag("账号或密码错误");
              return rs;
        }
        Users u= ud.selectOne(username,password);
        if(u==null){

            rs=ResponseCode.defeatdeRs(1,"账号或密码错误");
            return  rs;
        }
        if(u.getType()!=1){

            rs=ResponseCode.defeatdeRs(2,"没有操作权限！");
            return  rs;
        }
      rs= ResponseCode.successRs(0,u);

            return rs;
    }
    public ResponseCode selectOne ( String id){
        ResponseCode rs =new ResponseCode();
        if(id==null||id.equals("")){
            rs.setStatus(Const.USER_PARAMETER_CODE);
            rs.setMag(Const.USER_PARAMETER_MSG);
            return rs;
        }
        Integer ids=null;
        try {
             ids=Integer.parseInt(id);
        }catch (Exception e){

            rs=ResponseCode.defeatdeRs(105,"输入非法参数!");
            return rs;
        }

       ;
        Users u= ud.selectOne(ids);

        if(u==null){
            rs.setStatus(Const.USER_NO_CODE);
            rs.setMag(Const.USER_NO_MSG);
            return  rs;
        }
        if(u.getStats()==1){
            rs.setStatus(Const.USER_DISABLE_CODE);
            rs.setMag(Const.USER_DISABLE_MSG);
            return  rs;
        }
        int row=ud.updateByid(ids);
        if(row<=0){

            rs=ResponseCode.defeatdeRs(106,"用户禁用更新失败！");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(row);

        return rs;
    }
}
