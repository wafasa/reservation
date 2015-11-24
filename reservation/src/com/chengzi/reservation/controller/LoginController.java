package com.chengzi.reservation.controller;

import com.chengzi.reservation.dao.BaseDao;
import com.chengzi.reservation.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 橙子 on 2015/11/24.
 */

@Controller
@RequestMapping("/view/")
public class LoginController{

    Session session = new HibernateUtil().getSession();

    @Autowired
    private BaseDao baseDao;

    @RequestMapping("adminlogin.do")
    public String adminLogin(HttpServletRequest request,String name,String password){

        try {
            String hql = "select a.password from Admin a where a.name = ?";
            Query q = session.createQuery(hql);
            q.setString(0,name);
            String pw = q.list().get(0).toString();
            if(pw!=null&&password.equals(pw)){
                return "admin/admin";
            }else{
                request.setAttribute("error","name or password is wrong!");
                return "login/adminLogin";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","name or password is wrong!");
            return "login/adminLogin";
        }
    }

    @RequestMapping("customerlogin.do")
    public String customerLogin(HttpServletRequest request,String name,String password){

        try {
            String hql = "select c.password from Customer c where c.name=?";
            Query q = session.createQuery(hql);
            q.setString(0,name);
            String pw = q.list().get(0).toString();
            System.out.println(pw);
            if(pw!=null&&password.equals(pw)){
                return "customer/customer";
            }else{
                request.setAttribute("error","name or password is wrong!");
                return "login/customerLogin";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","name or password is wrong!");
            return "login/customerLogin";
        }
    }

}
