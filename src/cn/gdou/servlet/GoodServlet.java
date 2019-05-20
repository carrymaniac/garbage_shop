package cn.gdou.servlet;

import cn.gdou.common.PageResult;
import cn.gdou.common.ResultEnum;
import cn.gdou.common.StringUtil;
import cn.gdou.dao.ClassifyDao;
import cn.gdou.entity.Classify;
import cn.gdou.entity.Good;
import cn.gdou.entity.Page;
import cn.gdou.service.ClassifyService;
import cn.gdou.service.GoodService;
import cn.gdou.service.imp.ClassifyServiceImpl;
import cn.gdou.service.imp.GoodServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@WebServlet("/good")
public class GoodServlet extends HttpServlet {
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
            case "editUI":
                editUI(request,response);
                break;
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GoodService goodService = new GoodServiceImpl();
        String mobileClassifyId= request.getParameter("mobileClassifyId");
        if(mobileClassifyId==null)
            mobileClassifyId="0";
        Page dataBean = null;
        HttpSession session=request.getSession(true);
        dataBean = ServletUtil.getPage(session);
        List<Good> byClassify = goodService.findByClassify(Integer.parseInt(mobileClassifyId));
        dataBean.setData(byClassify);
        response.sendRedirect("byPageShow.jsp");//重定向到byPageShow.jsp
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * 删除商品操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if(idStr!=null&&idStr.trim().length()>0) {
            request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
            return;
        }
        GoodService goodService = new GoodServiceImpl();
        Boolean result = goodService.delete(idStr);
        if(result){
            request.setAttribute("msg", ResultEnum.DELETE_SUCCESS.getMsg());
            request.getRequestDispatcher(request.getContextPath()+"/good?method=list").forward(request, response);
        }else {
            request.setAttribute("msg", ResultEnum.DELETE_FAIL.getMsg());
            request.getRequestDispatcher(request.getContextPath()+"/good?method=list").forward(request, response);
        }

    }

    /**
     * 商品列表操作
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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
        GoodService goodService = new GoodServiceImpl();
        PageResult pageResult = goodService.queryByPage(currentPageInt, pageSizeInt);
        request.setAttribute("pageResult", pageResult);
        request.getRequestDispatcher("/WEB-INF/pages/good/list.jsp").forward(request, response);

    }

    /**
     * 商品添加
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Good good = this.getGood(request);
        GoodService goodService = new GoodServiceImpl();
        goodService.add(good);
        request.setAttribute("msg", ResultEnum.ADD_SUCCESS.getMsg());
        request.getRequestDispatcher(request.getContextPath()+"/good?method=list").forward(request, response);
    }
    //todo 修改所有的项目跳转
    /**
     * 商品更新
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Good good = this.getGood(request);
        String mobileId = request.getParameter("mobileId");
        good.setId(mobileId);
        GoodService goodService = new GoodServiceImpl();
        Boolean update = goodService.update(good);
        if(update) {
            request.setAttribute("msg", ResultEnum.UPDATE_SUCCESS.getMsg());
            request.getRequestDispatcher(request.getContextPath()+"/good?method=list").forward(request, response);
        }else {
            request.setAttribute("msg", ResultEnum.UPDATE_FAIL.getMsg());
            request.getRequestDispatcher(request.getContextPath()+"/good?method=list").forward(request, response);
        }
    }

    /**
     * 辅助方法,从request域中获取参数填充到good中并返回
     * @param request
     * @return 填充好的GOOD
     */
    private Good getGood(HttpServletRequest request){
        String mobileVersion = request.getParameter("mobileVersion");
        String mobileName = request.getParameter("mobileName");
        String mobileMade = request.getParameter("mobileMade");
        String mobilePrice = request.getParameter("mobilePrice");
        String mobileMess = request.getParameter("mobileMess");
        String mobilePic = request.getParameter("mobilePic");
        String mobileClassify = request.getParameter("mobileClassify");
        if(StringUtil.strIsNull(mobileVersion)||StringUtil.strIsNull(mobileName)){
            return null;
        }
        Integer mobilePriceInt = null;
        System.out.println(mobilePrice);
        if(!StringUtil.strIsNull(mobilePrice)) {
            mobilePriceInt = Integer.parseInt(mobilePrice);
        }
        Good good = new Good();
        good.setMobileVersion(mobileVersion);
        good.setMobileMess(mobileMess);
        good.setMobileName(mobileName);
        good.setMobileMade(mobileMade);
        good.setMobilePrice(mobilePriceInt);
        good.setMobilePic(mobilePic);
        good.setMobileClassify(Integer.parseInt(mobileClassify));
        return good;
    }

    private void editUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Good good = this.getGood(request);
        System.out.println("@@@@@@@@@");
        if(good == null){
            //说明是个添加页面
            request.setAttribute("page","添加商品");
            ClassifyService classifyService = new ClassifyServiceImpl();
            List<Classify> classifies = classifyService.queryAll();
            classifies.stream().forEach(e-> e.setId(e.getClassifyId().toString()));
            System.out.println(classifies.toString());
            request.setAttribute("classifies",classifies);
            request.getRequestDispatcher("/WEB-INF/pages/good/editUI.jsp").forward(request,response);
        }else{
            //说明是个修改操作
            request.setAttribute("page","修改商品");
            request.setAttribute("good",good);
            request.getRequestDispatcher("/WEB-INF/pages/good/editUI.jsp").forward(request,response);
        }

    }

    
}
