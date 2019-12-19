package com.iflysse.service.impl.goodsimpl;

import com.iflysse.mapper.CategoryMapper;
import com.iflysse.mapper.GoodsMapper;
import com.iflysse.pojo.Goods;
import com.iflysse.service.CategoryService;
import com.iflysse.service.GoodsService;
import com.iflysse.viewmodel.GoodsViewModel.Goods4List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    private List<Goods4List> buildGoodsList(List<Goods> goods){
        if(goods==null || goods.size()==0){
            return new ArrayList<>(0);
        }

        List<Goods4List> result = new ArrayList<>();
        for(Goods item:goods){
            Goods4List g4list = buildGoods(item);
            result.add(g4list);
        }
        return result;
    }

    private Goods4List buildGoods(Goods item){
        Goods4List result = new Goods4List();
        result.setId(item.getId());
        result.setName(item.getGoodsName());
        result.setPrice(item.getGoodsPrice());
        result.setUrl(item.getUrl());
        result.setDescription(item.getGoodsIntroduce());
        result.setCategoryId(item.getCategoryId());
        result.setCategory(categoryMapper.getCategoryById(item.getCategoryId()));
        result.setCategoryId(item.getCategoryId());
        String slide=item.getSlidePicture();

        //处理华东图片
        slide=slide.substring(1, slide.length()-1);
        String[] sp = slide.split("\\|");
        result.setSlide_1(sp[0].substring(1, sp[0].length()-1));
        result.setSlide_2(sp[1].substring(1, sp[1].length()-1));
        result.setSlide_3(sp[2].substring(1, sp[2].length()-1));
        result.setSlide_4(sp[3].substring(1, sp[3].length()-1));
        //处理detail图片
        if (item.getDetailPicture()!=null) {
            String details = item.getDetailPicture();

            List<String> sList=new ArrayList<>();
            if (details.length()>2) {
                details = details.substring(1, details.length() - 1);
                String[] sDetails = details.split("\\|");
                for (String s : sDetails) {
                    sList.add(s.substring(1, s.length()-1));
                }
            }
            result.setDetailPicture(sList);
        }
        return result;
    }

    @Override
    public List<Goods4List> getAll(int pageSize, int pageIndex) {
        return buildGoodsList(goodsMapper.getAll(pageSize, pageIndex));
    }

    @Override
    public List<Goods4List> getByCategory(int categoryId, int pageSize, int pageIndex) {
        return buildGoodsList(goodsMapper.getByCategory(categoryId,pageSize, pageIndex));
    }

    @Override
    public Goods4List getById(int goodsId) {
        return buildGoods(goodsMapper.getById(goodsId));
    }

    @Override
    public List<Goods4List> getByName(String goodName, int pageSize, int pageIndex) {
        return buildGoodsList(goodsMapper.getByName(goodName,pageSize,pageIndex));
    }

}
