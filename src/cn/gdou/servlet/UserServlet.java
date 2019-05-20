package cn.gdou.servlet;

import cn.gdou.common.PageResult;
import cn.gdou.common.ResultEnum;
import cn.gdou.entity.User;
import cn.gdou.service.UserService;
import cn.gdou.service.imp.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null && method == "") {
            req.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(req, resp);
            return;
        }
        switch (method) {
            case "list":
                list(req, resp);
                break;
            case "editUI":
                editUI(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            case "queryById":
//                queryById(request, response);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "register":
                register(req,resp);
            default:
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user =new User();
        request.setAttribute("userBean",user);
        String loginName=request.getParameter("loginName").trim();
        String password=request.getParameter("password").trim();
        String again_password=request.getParameter("again_password").trim();
        String phone=request.getParameter("phone").trim();
        String address=request.getParameter("address").trim();
        String realname=request.getParameter("realname").trim();
        System.out.println("23333");
        if(!password.equals(again_password)) {
            user.setBackNews("两次密码不同，注册失败，");
            RequestDispatcher dispatcher=
                    request.getRequestDispatcher("inputRegisterMess.jsp");
            dispatcher.forward(request, response);//转发
            return;
        }
        if("".equals(loginName)||"".equals(password)||"".equals(phone)||"".equals(address)||"".equals(realname)){
            user.setBackNews("信息填写不完整");
            RequestDispatcher dispatcher=
                    request.getRequestDispatcher("inputRegisterMess.jsp");
            dispatcher.forward(request, response);//转发
            return;
        }
        boolean isLD=true;
        for(int i=0;i<loginName.length();i++){
            char c=loginName.charAt(i);
            if(!((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<='9'&&c>='0')))
                isLD=false;
        }
        boolean boo=loginName.length()>0&&password.length()>0&&isLD;
        String backNews="";
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRealname(realname);
        UserService userService = new UserServiceImpl();
        Map<String, Object> register = null ;
        register = userService.Register(user);
        user.setBackNews((String)register.get("info"));
        RequestDispatcher dispatcher= request.getRequestDispatcher("inputRegisterMess.jsp");
        dispatcher.forward(request, response);//转发

    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String userName = request.getParameter("userName");
        Integer pageSizeInt = 5;
        Integer currentPageInt = 1;
        if (pageSize != null && pageSize.length() > 0) {
            pageSizeInt = Integer.parseInt(pageSize);
        }
        if (currentPage != null && currentPage.length() > 0) {
            currentPageInt = Integer.parseInt(currentPage);
        }
        UserService userService = new UserServiceImpl();
        PageResult pageResult = userService.queryByPages(currentPageInt, pageSizeInt, userName);
        request.setAttribute("pageResult", pageResult);
        //TODO 修改一下地址
        request.getRequestDispatcher("/WEB-INF/pages/user/list.jsp").forward(request, response);
    }

    private void editUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && idStr.trim().length() > 0) {
            UserService userService = new UserServiceImpl();
            User user = userService.findOne(idStr);
            request.setAttribute("user", user);
        }
        request.getRequestDispatcher("/WEB-INF/pages/user/edit.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && idStr.trim().length() > 0) {
            UserService userService = new UserServiceImpl();
            Boolean delete = userService.delete(idStr);
            if (delete) {
                request.setAttribute("msg", ResultEnum.DELETE_SUCCESS.getMsg());
                request.getRequestDispatcher("/WEB-INF/pages/user/list.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", ResultEnum.DELETE_FAIL.getMsg());
                request.getRequestDispatcher("/WEB-INF/pages/user/list.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
            return;
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String loginName = request.getParameter("loginName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String realname = request.getParameter("realname");
        String password = request.getParameter("password");
        User user = new User();
        user.setId(id);
        user.setLoginName(loginName);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRealname(realname);
        user.setPassword(password);
        UserService userService = new UserServiceImpl();
        Boolean result = userService.update(user);
        if (result) {
            request.setAttribute("msg", ResultEnum.UPDATE_SUCCESS.getMsg());
            request.getRequestDispatcher("/WEB-INF/pages/user/list.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", ResultEnum.UPDATE_FAIL.getMsg());
            request.getRequestDispatcher("/WEB-INF/pages/user/list.jsp").forward(request, response);
        }
    }
}

