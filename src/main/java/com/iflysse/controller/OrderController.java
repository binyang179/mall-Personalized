package com.iflysse.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.iflysse.pojo.Evaluate;
import com.iflysse.service.CategoryService;
import com.iflysse.service.EvaluateService;
import com.iflysse.service.GoodsCartService;
import com.iflysse.viewmodel.CategoryViewModel.ParentCategory;
import com.iflysse.viewmodel.EvaluateViewModel.EvaluateList;
import com.iflysse.viewmodel.GoodsCartModel.GoodsCartList;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsCartService goodsCartService;

    @Autowired
    private EvaluateService evaluateService;

    /**
     * 购物车列表页
     * @param model
     * @param pageSize
     * @param pageIndex
     * @return
     */

    @RequestMapping("/order/orderView/{pageSize}/{pageIndex}")
    public String OrderView(Model model,@PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex, HttpSession httpSession){
        int userId=Integer.parseInt(httpSession.getAttribute("userId").toString());
        List<GoodsCartList> carts = goodsCartService.getPurchasedGoodByUserId(userId,pageSize,pageIndex);
        model.addAttribute("carts", carts);
        List<ParentCategory> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        String userName=httpSession.getAttribute("userName").toString();
        model.addAttribute("userName",userName);
        return "order";
    }

    @RequestMapping("/order/addEvaluation")
    @ResponseBody
    public boolean  AddEvaluation(@RequestBody Evaluate data, HttpSession httpSession) {
        if (httpSession.getAttribute("userId")!=null) {
            int userId=Integer.parseInt(httpSession.getAttribute("userId").toString());
            data.setUserId(userId);
            evaluateService.insertEvaluateInfo(data);
            return true;
        }
        else
        {
            return false;
        }
    }

    @RequestMapping("/order/getByCartId")
    @ResponseBody
    public String  GetByCartId(@RequestBody Evaluate data, HttpSession httpSession) {

     EvaluateList result=evaluateService.getByCartId (data.getCartId());
     return result.getGrade()+","+result.getComment();
    }


}
