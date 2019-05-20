package cn.gdou.servlet;

import cn.gdou.common.PageResult;
import cn.gdou.common.ResultEnum;
import cn.gdou.entity.Classify;
import cn.gdou.service.ClassifyService;
import cn.gdou.service.imp.ClassifyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/classify")
public class ClassifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null && method == "") {
            request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
            return;
        }
        switch (method) {
            case "list":
                list(request, response);
                break;
            case "add":
                add(request,response);
                break;
            case "delete":
                delete(request,response);
                break;
            case "update":
                update(request,response);
                break;
            case "query":
                query(request,response);
                break;
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) {
        ClassifyService classifyService = new ClassifyServiceImpl();

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classifyName = request.getParameter("classifyName");
        String classifyId = request.getParameter("classifyId");
        Classify classify = new Classify();
        classify.setName(classifyName);
        classify.setClassifyId(Integer.parseInt(classifyId));
        ClassifyService classifyService = new ClassifyServiceImpl();
        Boolean result = classifyService.update(classify);
        if(result){
            request.setAttribute("msg", ResultEnum.DELETE_SUCCESS.getMsg());
            request.getRequestDispatcher("/WEB-INF/pages/classify/list.jsp").forward(request, response);
        }else {
            request.setAttribute("msg", ResultEnum.DELETE_FAIL.getMsg());
            request.getRequestDispatcher("/WEB-INF/pages/classify/list.jsp").forward(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if(idStr!=null&&idStr.trim().length()>0) {
            request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
            return;
        }
        ClassifyService classifyService = new ClassifyServiceImpl();
        Boolean result = classifyService.delete(Integer.parseInt(idStr));
        if(result){
            request.setAttribute("msg", ResultEnum.DELETE_SUCCESS.getMsg());
            request.getRequestDispatcher("/WEB-INF/pages/classify/list.jsp").forward(request, response);
        }else {
            request.setAttribute("msg", ResultEnum.DELETE_FAIL.getMsg());
            request.getRequestDispatcher("/WEB-INF/pages/classify/list.jsp").forward(request, response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classifyName = request.getParameter("classifyName");
        Classify classify = new Classify();
        classify.setName(classifyName);
        ClassifyService classifyService = new ClassifyServiceImpl();
        classifyService.add(classify);
        request.setAttribute("msg", ResultEnum.ADD_SUCCESS.getMsg());
        request.getRequestDispatcher("/WEB-INF/pages/classify/list.jsp").forward(request, response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //默认的页面大小
        Integer pageSizeInt = 5;
        //默认的当前页面
        Integer currentPageInt = 1;
        if(pageSize!=null&&pageSize.length()>0) {
            pageSizeInt = Integer.parseInt(pageSize);
        }
        if(currentPage!=null&&currentPage.length()>0) {
            currentPageInt = Integer.parseInt(currentPage);
        }
        ClassifyService classifyService = new ClassifyServiceImpl();
        PageResult pageResult = classifyService.queryByPage(currentPageInt, pageSizeInt);
        request.setAttribute("pageResult", pageResult);
        request.getRequestDispatcher("/WEB-INF/pages/classify/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
