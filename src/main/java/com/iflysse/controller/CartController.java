package com.iflysse.controller;

import com.iflysse.service.CategoryService;
import com.iflysse.service.GoodsCartService;
import com.iflysse.util.PythonUtil;
import com.iflysse.viewmodel.CategoryViewModel.ParentCategory;
import com.iflysse.viewmodel.GoodsCartModel.GoodsCartList;
import com.iflysse.viewmodel.GoodsViewModel.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsCartService goodsCartService;

    /**
     * 购物车列表页
     * @param model
     * @param pageSize
     * @param pageIndex
     * @return
     */

    @RequestMapping("/cart/cartView/{pageSize}/{pageIndex}")
    public String CartView(Model model,@PathVariable("pageSize") int pageSize, @PathVariable("pageIndex") int pageIndex, HttpSession httpSession){
        int userId=Integer.parseInt(httpSession.getAttribute("userId").toString());
        String userName=httpSession.getAttribute("userName").toString();
        model.addAttribute("userName",userName);
        List<GoodsCartList> carts = goodsCartService.getByUser(userId,0,pageSize,pageIndex);
        model.addAttribute("carts", carts);
        List<ParentCategory> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "cart";
    }

    @RequestMapping("/cart/deleteCartByIds/{cartIds}")
    public void  DeleteCartByIds(@PathVariable("cartIds") String cartIds) {

        String items = cartIds;
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            goodsCartService.deleteById(Integer.parseInt(str));
        }
    }

    @ResponseBody
    @RequestMapping("/cart/buyGoods")
    public void  BuyGoods(@RequestBody List<GoodsCartList> arr) {
        for (GoodsCartList cart : arr) {
            goodsCartService.updateById(cart.getId(),cart.getNumber());
        }
    }

    @RequestMapping("/cart/addCart")
    @ResponseBody
    public boolean  AddCart(@RequestBody GoodsCartList cart, HttpSession httpSession) {
        if (httpSession.getAttribute("userId")!=null)
        {
            int userId=Integer.parseInt(httpSession.getAttribute("userId").toString());
            List<GoodsCartList> isExits=goodsCartService.getByInfo(userId,cart.getGoodsId(),0);
            if(isExits==null || isExits.size()==0){
                goodsCartService.insertCartInfo(userId,cart.getGoodsId(),cart.getNumber(),0);
            }
            else
            {
                goodsCartService.addCartCountById(isExits.get(0).getId());
            }
            return true;
        }
        else
         {
             return false;
        }

    }
    @RequestMapping("/cart/buyGoodQuick")
    @ResponseBody
    public boolean  buyGoodQuick(@RequestBody  GoodsCartList cart, HttpSession httpSession) {
        if (httpSession.getAttribute("userId")!=null) {
            int userId=Integer.parseInt(httpSession.getAttribute("userId").toString());
            goodsCartService.insertCartInfo(userId, cart.getGoodsId(), cart.getNumber(), 1);

            // 修改  需要重新更新一下 相似商品
            String command = "/home/crab179/IdeaProjects/iflytek/Personalized/python/also_buy_recommendation_func.py";
            PythonUtil.executePython(cart.getGoodsId(), command);

            return true;
        }
        else
        {
            return false;
        }
    }
}
