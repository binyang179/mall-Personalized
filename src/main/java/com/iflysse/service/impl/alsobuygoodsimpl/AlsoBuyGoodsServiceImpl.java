package com.iflysse.service.impl.alsobuygoodsimpl;

import com.iflysse.mapper.AlsoBuyGoodsMapper;
import com.iflysse.mapper.CategoryMapper;
import com.iflysse.mapper.GoodsMapper;
import com.iflysse.pojo.Goods;
import com.iflysse.pojo.HotGoods;
import com.iflysse.service.AlsobuyGoodsService;
import com.iflysse.viewmodel.GoodsViewModel.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AlsoBuyGoodsServiceImpl implements AlsobuyGoodsService {
    @Autowired
    private AlsoBuyGoodsMapper alsoBuyGoodsMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Goods4List> getAlsoBuyGoodsTop(Integer goodsId) {
        return buildAlsoBuyGoodsList(alsoBuyGoodsMapper.getAlsoBuyGoodsTop(goodsId));
    }


    private List<Goods4List> buildAlsoBuyGoodsList(String goodsIds){
        if(goodsIds==null || goodsIds.length()==0){
            return new ArrayList<>(0);
        }
        // 3465025,3465001,3452029,3452043,3451023,3505030,1459026,1687029,1587050,3481214
        // 它是一个所有相似商品的集合
        List<Goods4List> result = new ArrayList<Goods4List>();
        // 有代码错误 教学演示代码 不是一个线上代码
        List<Goods> alsoBuyGoods=goodsMapper.getByIds(goodsIds);
        for(Goods item:alsoBuyGoods){
            Goods4List g4list = buildGoods(item);
            result.add(g4list);
        }
        return result;
    }

    private Goods4List buildGoods(Goods item){
        Goods4List goods4List = new Goods4List();
        goods4List.setId(item.getId());
        goods4List.setName(item.getGoodsName());
        goods4List.setPrice(item.getGoodsPrice());
        goods4List.setUrl(item.getUrl());
        goods4List.setDescription(item.getGoodsIntroduce());
        goods4List.setCategoryId(item.getCategoryId());
        goods4List.setCategory(categoryMapper.getCategoryById(item.getCategoryId()));
        goods4List.setCategoryId(item.getCategoryId());
        return goods4List;
    }
}
