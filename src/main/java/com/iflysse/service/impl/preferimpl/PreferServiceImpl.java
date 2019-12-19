package com.iflysse.service.impl.preferimpl;

import com.iflysse.mapper.PreferMapper;
import com.iflysse.pojo.Prefer;
import com.iflysse.pojo.User;
import com.iflysse.service.PreferService;
import com.iflysse.viewmodel.PreferViewModel.PreferList;
import com.iflysse.viewmodel.UserViewModel.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferServiceImpl implements PreferService {
    @Autowired
    private PreferMapper preferMapper;

    private List<PreferList> buildPreferList(List<Prefer> prefers){
        if(prefers==null || prefers.size()==0){
            return new ArrayList<PreferList>(0);
        }

        List<PreferList> result = new ArrayList<>();
        for(Prefer item:prefers){
            PreferList glist = buildPrefer(item);
            result.add(glist);
        }
        return result;
    }

    private PreferList buildPrefer(Prefer prefer){
        PreferList item = new PreferList();
        item.setUserId(prefer.getUserId());
        item.setCategoryId(prefer.getCategoryId());
        item.setId(prefer.getId());
        item.setCreateTime(prefer.getCreateTime());
      return item;
    }

    @Override
    public List<PreferList> getByUserId(int userId){
        return buildPreferList(preferMapper.getByUserId(userId));
    }

    @Override
    public void insertInfo(int userId,int categoryId){
        preferMapper.insertInfo(userId,categoryId);
    }
}
