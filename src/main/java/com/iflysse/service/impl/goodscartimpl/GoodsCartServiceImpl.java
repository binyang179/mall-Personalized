package com.iflysse.service.impl.goodscartimpl;

import com.iflysse.mapper.CategoryMapper;
import com.iflysse.mapper.EvaluateMapper;
import com.iflysse.mapper.GoodsCartMapper;
import com.iflysse.mapper.GoodsMapper;
import com.iflysse.pojo.Evaluate;
import com.iflysse.pojo.Goods;
import com.iflysse.pojo.GoodsCart;
import com.iflysse.service.GoodsCartService;
import com.iflysse.util.PythonUtil;
import com.iflysse.viewmodel.GoodsCartModel.GoodsCartList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsCartServiceImpl implements GoodsCartService {
    @Autowired
    private GoodsCartMapper goodsCartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private EvaluateMapper evaluateMapper;

    private List<GoodsCartList> buildGoodsCartList(List<GoodsCart> goodsCarts){
        if(goodsCarts==null || goodsCarts.size()==0){
            return new ArrayList<GoodsCartList>(0);
        }

        List<GoodsCartList> result = new ArrayList<>();
        for(GoodsCart item:goodsCarts){
            GoodsCartList gList = buildGoodsCart(item);
            result.add(gList);
        }
        return result;
    }

    private GoodsCartList buildGoodsCart(GoodsCart goodsCart){
        GoodsCartList item = new GoodsCartList();
        item.setId(goodsCart.getId());
        item.setUserId(goodsCart.getUserId());
        item.setGoodsId(goodsCart.getGoodsId());
        item.setNumber(goodsCart.getNumber());
        item.setStatus(goodsCart.getStatus());
        item.setCreateTime(goodsCart.getCreateTime());
        Goods goodInfo= goodsMapper.getById(goodsCart.getGoodsId());
        item.setGoods(goodInfo);
        item.setParentCategoryId(categoryMapper.getCategoryById(goodInfo.getCategoryId()).getParentId());
        Evaluate eList=evaluateMapper.getByCartId(goodsCart.getId());
        if(eList!=null ){
            item.setEvaluateId(eList.getId());
        }
        return item;
    }

    @Override
    public List<GoodsCartList> getByUser (int userId, int status, int pageSize, int pageIndex) {
        return buildGoodsCartList(goodsCartMapper.getByUser(userId,status,pageSize, pageIndex));
    }
    @Override
    public void deleteById (int cartId){
        goodsCartMapper.deleteById(cartId);
    }

    @Override
    public void updateById (int cartId,int number){
        goodsCartMapper.updateById(cartId,number);
    }

    @Override
    public List<GoodsCartList> getByInfo(int userId, int goodsId, int status){
        return buildGoodsCartList(goodsCartMapper.getByInfo(userId,goodsId,status));
    }

    @Override
    public void insertCartInfo(int userId,int goodsId,int number,int status){
        goodsCartMapper.insertCartInfo(userId, goodsId, number,status);
    }

    @Override
    public void addCartCountById(int cartId){
        goodsCartMapper.addCartCountById(cartId);
    }

    /**
     * 根据用户编号获取已购买的商品信息
     * @param userId 用户编号
     * @param pageSize 每页显示的条数
     * @param pageIndex 当前页码
     * @return 用于展示的商品列表
     */
    public List<GoodsCartList> getPurchasedGoodByUserId(int userId,int pageSize, int pageIndex){
        return buildGoodsCartList(goodsCartMapper.getByUser(userId,1,pageSize, pageIndex));
    }
}
