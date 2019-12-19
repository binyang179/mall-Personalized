package com.iflysse.controller;

import com.iflysse.pojo.User;
import com.iflysse.service.*;
import com.iflysse.viewmodel.CategoryViewModel.ParentCategory;
import com.iflysse.viewmodel.EvaluateViewModel.EvaluateList;
import com.iflysse.viewmodel.GoodsViewModel.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Controller
@SessionAttributes(value = {"categories"})
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private HotGoodsService hotGoodsService;
    @Autowired
    private AlsobuyGoodsService alsobuyGoodsService;
    @Autowired
    private RecomService recomService;
    @Autowired
    private UserService userService;

    /**
     * 系统首页
     * @param model ViewModel
     * @return
     */
    @RequestMapping("/home")
    public String Index(Model model,HttpSession httpSession) {
        /**
         * 怎么开发？
         * 基于首页的开发为例来讲解？
         * 1.先讲解项目的开发流程 --> 框架的运行原理
         * 2.首页的业务的功能--> 登录注册购物车，菜单，热门商品，猜你喜欢，新品推荐
         * 3.读取数据库（菜单--> 重点  数据结构的设计（树形的数据结构的设计））
         * 4.热门商品、猜你喜欢--> 通过python进行算法建模实现，将结果写到指定数据库的表中，然后在ssm框架中直接调用
         *
         * 怎么设计的？
         * 1. 表的设计
         * 2. 界面的设计
         * 3. 代码实现： controller调用service service调用mapper
         *
         * 前后端的交互：
         *  后端将数据传递到前端
         *  通过service/mapper将数据查询出来，保存到model对象中（request作用域中attribute）
         *  在页面上面，使用java脚本或者el表达式（jstl标签/spring标签） 性能上jstl标签的性能比spring的标签高
         *  我们的项目使用的是java脚本（我希望最后答辩的时候，此处大家要使用jstl标签来替换java脚本）
         *
         *  前端数据如何传递到后端
         *  1.通过form表单进行传递 （登录/注册） 注意：我们项目中的登录和注册使用的是ajax（因为登录成功后，只需要修改登录状态）
         *  2.通过ajax方式和后台进行数据交互 （ajax 的 data参数来进行数据传递）
         *  3.通过window.location.href的方式来进行数据请求和数据交互 （@PathVariable）使用路径变量来完成数据传递
         *      只适合数据量比较小的请求 。比如：删除，状态变更
         *  4.通过a标签的形式来进行数据交互，也是通过@PathVariable来进行数据传递
         *  5.也可以js通过调用form的方式进行数据提交（设备保修系统） ...
         */
        List<ParentCategory> categories = categoryService.getCategories();
        List<Goods4List> goods = goodsService.getAll(9, 0);
        List<Goods4List> hotGoods = hotGoodsService.getTop();
        List<Goods4List> newsGoods = hotGoodsService.getNewsGoods();
        Integer userId = (Integer)httpSession.getAttribute("userId");


        model.addAttribute("categories", categories);
        model.addAttribute("goods", goods);
        model.addAttribute("hotGoods", hotGoods);
        model.addAttribute("newsGoods",newsGoods);
        String userName=null;
        if (userId!=null) {
            userName = httpSession.getAttribute("userName").toString();
        }else{
            userId = 8;
        }
        List<Goods4List> personalGoods = recomService.getRecommendationGoods(userId); // 个性化的推荐
        System.out.println("--------------------------------------------------"+personalGoods);
        model.addAttribute("personalGoods",personalGoods);
        model.addAttribute("userName", userName);
        return "index";
    }


    @RequestMapping("/pythonExec")
    public void pythonExec() {
        InputStreamReader stdISR = null;
        InputStreamReader errISR = null;
        Process process = null;
        String command = "/home/crab179/IdeaProjects/iflytek/Personalized/hahaha.sh";
        try {
            process = Runtime.getRuntime().exec(command);
            int exitValue = process.waitFor();

            String line = null;

            stdISR = new InputStreamReader(process.getInputStream());
            BufferedReader stdBR = new BufferedReader(stdISR);
            while ((line = stdBR.readLine()) != null) {
                System.out.println("STD line:" + line);
            }

            errISR = new InputStreamReader(process.getErrorStream());
            BufferedReader errBR = new BufferedReader(errISR);
            while ((line = errBR.readLine()) != null) {
                System.out.println("ERR line:" + line);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stdISR != null) {
                    stdISR.close();
                }
                if (errISR != null) {
                    errISR.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (IOException e) {
                System.out.println("正式执行命令：" + command + "有IO异常");
            }
        }
    }

    /**
     *
     * @param model
     * @param categoryId
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping("/home/product/{cId}/{pageSize}/{pageIndex}")
    public String Product(Model model,@PathVariable("cId") int categoryId,
                          @PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex,
                          HttpSession httpSession){
        List<Goods4List> goods = goodsService.getByCategory(categoryId, pageSize, pageIndex);
        model.addAttribute("goods", goods);
        List<ParentCategory> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        String userName="";
        if (httpSession.getAttribute("userId")!=null) {
            userName = httpSession.getAttribute("userName").toString();
        }
        model.addAttribute("userName", userName);
        return "goods";
    }
    @RequestMapping("/home/product/search/{content}/{pageSize}/{pageIndex}")
    public String ProductSearch(Model model,@PathVariable("content") String content,
                                @PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex, HttpSession httpSession){
        List<Goods4List> goods = goodsService.getByName(content,pageSize,pageIndex);
        model.addAttribute("goods", goods);
        List<ParentCategory> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        String userName  ="";
        Integer userId = (Integer)httpSession.getAttribute("userId");
        if (userId!=null) {  // 用户是登录了
            userName = (String)httpSession.getAttribute("userName");
            // 进行成功搜索之后，将最后的搜索条件保存到数据库中，给python来进行查询
            User user = new User();
            user.setUserId(userId);
            user.setLastSearch(content);
            userService.updateUser(user);


        }
        model.addAttribute("userName", userName);
        return "goods";
    }

    /**
     *物品详情页
     * @param model
     * @param goodsId
     * @return
     */
    @RequestMapping("/home/productView/{gId}")
    public String ProductView(Model model,@PathVariable("gId") int goodsId,HttpSession httpSession){
        Goods4List goods = goodsService.getById(goodsId);
        model.addAttribute("goods", goods);
        List<EvaluateList> evaluates = evaluateService.getByGood(goodsId,10,0);
        model.addAttribute("evaluates", evaluates);
        // 从后台获取 alsobuyGoods 这样的数据   仿照hotGoods实现方式
        List<Goods4List> alsobuyGoods = alsobuyGoodsService.getAlsoBuyGoodsTop(goodsId);


        String userName="";
        if (httpSession.getAttribute("userId")!=null) {
            userName = httpSession.getAttribute("userName").toString();
        }
        model.addAttribute("userName", userName);
        model.addAttribute("alsobuyGoods", alsobuyGoods);

        return "detail";
    }

}
