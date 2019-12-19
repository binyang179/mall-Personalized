# -*- coding:utf-8 -*-

import numpy as np
from collections import Counter
import pymysql
import datetime

db = pymysql.connect('127.0.0.1', 'root', '123456', 'shopa')

def RecentPopularity(goods_IDs, purchasing_date, T, alpha=1 / 24 / 3600):
    '''
    输入商品ID和对应的购买时间，当前日期，返回当前最热门的商品列表
    参数：
    goods_IDs：商品ID列表，数据类型：列表或int
    purchasing_date：对应的购买时间，数据类型：元组
    T:当前时间，数据类型：datetime.datetime
    alpha:默认为1/24/3600，时间对热门的影响程度，alpha越大，影响越大，数据类型：int或者float

    返回：
    热门商品的ID列表，数据类型：列表
    '''
    #  Counter() python中的计数器
    ret = Counter()
    #  int i = 0; i < 5; i++
    for i in range(len(goods_IDs)):
        if purchasing_date[i] >= T:
            continue
        ret[goods_IDs[i]] += 1 / (1.0 + alpha * abs(T - purchasing_date[i]))
    good_list = np.array(ret.most_common())
    # 取所有行的第0个元素
    return good_list[:, 0]

def calculate_time(t):
    '''
    时间转换函数，将datetime.datetime数值转换成以1970年1月1日开始计时，到t时刻的为止，一共经过了多少秒
    输入：
    t:当前时间,数据类型：元组
    输出：
    listt,时间转换后的列表，数据类型：列表
    '''
    listt = []
    for i in t:
        listt.append(abs((i - datetime.datetime(1970, 1, 1)).total_seconds()))
    return listt

def recommendation_0():
    '''
    实现热门推荐算法
    输入：空
    输出：热门商品推荐列表，数据类型：list
    '''
    cur = db.cursor()
    cur.execute('select goodsid,createtime from goodscart where status=1 ')
    results = cur.fetchall()
    if results == ():
        return []
    else:
        data = np.array(results)
        #  此处我们取得是 所有的商品的id  goodsid
        item_IDs = data[:, 0]
        #  data[:, 1] 商品在购物车中的时间  将时间和1970.1.1进行减法运算
        purchasing_date = calculate_time(data[:, 1])
        #  将当前时间和1970.1.1进行减法运算
        T = calculate_time((datetime.datetime.now(),))[0]
        #  开始建立推荐矩阵
        listt = RecentPopularity(item_IDs, purchasing_date, T)
        return listt

################################################################
# 获取最热门的10条商品记录
################################################################
def popular_recommendation_list(initial_list):
    '''
    输入推荐列表，若推荐列表中的商品个数小于10，则随机选择商品进行填充，若大于10，则选择前10个商品
    输入：
    user_id:用户ID，数据类型：int
    initial_list:初始推荐列表，数据类型：list
    输出：
    最终推荐列表，数据类型：list
    '''
    listt = initial_list
    while len(listt) < 10:
        cur = db.cursor()
        cur.execute('select id from buygood order by rand() limit 1 ')
        result = cur.fetchall()[0][0]
        if result not in listt:
            listt.append(result)
    listt = listt[:10]
    return listt

def popular_recommendation():
    """
    实现一个函数，该函数会自动计算热门商品，并插入（更新）数据库
    """
    #  listt 就是通过算法算出来的，最热门的前10条商品记录（取的是商品的ID）
    listt = popular_recommendation_list(list(recommendation_0()))

    #  将这些商品插入到热门商品记录表中 就可以了
    cur = db.cursor()
    try:
        if cur.execute('select * from hotgoods'):
            # cur.execute('update hotgoods set list="{listt}",createtime=now()')
            cur.execute('delete from hotgoods where 1=1')
        else:
            # cur.execute('insert into hotgoods (list,createtime) value ("{listt}",now())')
            pass
        for goodsId in listt:
            gId = int(goodsId)
            cur.execute('insert into hotgoods(goodsId,createtime) values(%s,now())', gId)
        db.commit()
        print("数据操作成功")
    except Exception as e:
        print("删除数据失败：case%s" % e)
        # 发生错误时回滚
        db.rollback()
    finally:
        # 关闭游标连接
        cur.close()
        # 关闭数据库连接
        # db.close()

if __name__ == '__main__':
    popular_recommendation()
    # cur = db.cursor()
    # cur.execute('select goodsId from hotgoods')
    # print(cur.fetchall())
