# -*- coding:utf-8 -*-



'''
个性化推荐方案，由三个部分组成 基于标签的推荐+基于时间上下文的itemCF的推荐+基于购买记录的推荐
直接调用personal_recommendation(userID)函数就可以实现，将个性化推荐列表存入（更新）数据库
'''
from sys import argv

import numpy as np
from collections import Counter
import pymysql
import datetime
import random
user_set = {}
db = pymysql.connect('127.0.0.1', 'root', '123456', 'shopa')

def __init__(self):
    pass
##########################################################################
#基于标签的推荐
##########################################################################
def LBR(user_IDs,item_IDs,item_labels,purchasing_date,user_ID,selected_list,T,alpha=1/24/3600,beta=1/24/3600,initial_value=1):
    '''
    实现了基于标签推荐的函数
    输入：
    user_IDs:用户ID，数据类型：列表或者int。
    item_IDs:商品ID，数据类型：列表或者int。
    item_labels：商品对应的标签，数据类型：列表或者int
    purchasing_date:购买时间，数据类型：列表或者datetime.datetime。
    user_ID：用户ID，数据类型：int。
    selected_list:用户注册时选择的商品标签，数据类型：int或者list。
    alpha:默认为1/24/3600，时间衰减速率，alpha越大，则时间差对用户影响越大，数据类型：int 或者 float。
    beta:默认为1/24/3600，时间衰减速率，beta越大，则时间差对用户影响越大，数据类型：int 或者 float。
    initial_value：对用户注册时选择的商品标签，进行用户标签喜好初始赋值，默认值为1，数据类型：int或float。
    输出：
    推荐列表，数据类型：列表
    '''
    W = ItemSimilarity(user_IDs, item_IDs, item_labels, purchasing_date, alpha)   # 商品与商品之间的关系是根据时间来关联的
    label_rank = label_based_recommendation_rank(user_ID, selected_list, T, initial_value, beta)
    if len(label_rank) == 0:
        return []
    else:
        label = select_label_from_label_rank(label_rank)
        return label   # 最终确定的一个最优一个label  随机出来的


def ItemSimilarity(user_IDs, item_IDs, item_labels, purchasing_date, alpha):
    '''
    生成商品相似度矩阵，更新用户倒排表
    使用条件：
    每当用户产生购买行为时调用，函数会更新user_set（用户倒排表），并产生商品相似度字典W
    user_set：用来存储用户的行为数据。key是用户ID，value同样是一个字典，key是商品ID，value是[购买时间,评分，物品所属label]
    W（物品相似度字典）：key是商品名称（如'a'），value同样是一个字典，key是商品名称(如'b')，则W[a][b]是商品a和b的相似度
    输入：
    user_IDs:用户ID，列表或者int
    item_IDs:商品ID，列表或者int
    item_labels：商品对应的标签，列表或者int。
    purchasing_date:购买时间，列表或者int
    alpha:时间衰减速率，alpha越大，则时间差对用户影响越大，int或者float
    输出：
    物品相似度字典W：W[i][j]表示商品i和商品j的相似度
    '''
    # 构建用户倒排表user_set，对于同一件商品的多次购买记录，倒排表中只记录最近一次的购买行为
    # 设置user_set是全局变量
    # user_set是一个字典，key是用户名称，value是一个字典，key是物品名称，value是列表[购买时间，label]
    # 由于user_set是全局变量所以更新时可以直接调用ItemSimilarity函数，会自动更新user_set
    global user_set
    if type(user_IDs) == int:
        user_ID = user_IDs
        item_ID = item_IDs
        time = int(purchasing_date)
        item_label = item_labels
        if user_ID not in user_set:
            user_set[user_ID] = {}
        user_set[user_ID][item_ID] = [time, item_label]

    else:
        for i in range(len(user_IDs)):
            user_ID = user_IDs[i]
            item_ID = item_IDs[i]
            time = int(purchasing_date[i])
            label = item_labels[i]
            if user_ID not in user_set:
                user_set[user_ID] = {}
            user_set[user_ID][item_ID] = [time, label]

    # dict  {userid: [{item_id1: [time, label]}, {item_id2: [time ,  label]}]}

    C = {}
    N = {}
    for user in user_set.keys():
        for item_i in user_set[user].keys():
            time_i, label_i = user_set[user][item_i]
            if item_i not in N.keys():
                N[item_i] = 0   # 统计item_id这个商品被几个用户买过   {商品id， 用户的数量}
                C[item_i] = {}
            N[item_i] += 1
            for item_j in user_set[user].keys():
                time_j, label_j = user_set[user][item_j]
                if item_i == item_j:
                    continue
                if item_j not in C[item_i].keys():
                    C[item_i][item_j] = 0
                C[item_i][item_j] += 1 / (1 + alpha * abs(time_i - time_j))   # 商品item_i和商品item_j
    #  1073000   所有商品j找出来  合并
    #  1165033   所有商品j找出来
    #  找到当前用户购买的商品，并且使用 商品相似矩阵 找到和 当前用户购买的商品的 所有关系
    #  可以将 商品根据  （相似值） 排序， 相似值越小，说明两个商品之间的 关系越弱
    #  将商品的相似值进行一个排序 ， 排序结果按照 相似值 降序，取前十条记录作为推荐的商品

    """
    用户1  购买了 商品A 商品B
        itemCF time 的一个商品推荐
    商品与商品之间的关系
    1. 查询所有的商品
    2. 将商品与商品关系建立起来
    3. 向用户1 推荐商品 （根据用户1 购买的商品A B  找到上面算出来的商品关系 [A][B] [A][C] [A][D] ...）
    比喻： 
    """
    W = {}
    for item_i in C.keys():
        W[item_i] = Counter()
        for item_j in C[item_i].keys():
            W[item_i][item_j] = C[item_i][item_j] / np.sqrt(N[item_i] * N[item_j])   # 余弦推荐算法
    return W

def label_based_recommendation_rank(user_ID,initial_list,T,initial_value,beta):
    '''
    根据用户行为和初始标签选择，计算用户对标签的偏好
    使用条件：
    进行基于label的推荐时调用
    输入：
    user_ID：用户ID，数据类型：int
    initial_list：用户初注册时选择的标签列表，数据类型：int或者list
    T:当前时间，数据类型：datetime.datetime
    initial_value：初始标签列表中元素的权重，默认为1，越大，则初始选择标签对未来影响越大，数据类型：int或者float
    beta：时间对用户标签喜好程度的影响，默认是1/24/3600，越大，则时间影响越大，数据类型：int或者float
    输出：
    一个字典：key是标签，value是用户对该标签的喜欢程度，数据类型：字典
    '''
    #设置初始值为initial_value
    labels_rank = Counter()
    if  isinstance(initial_list, int):
        labels_rank[initial_list] += initial_value
    else:
        for i in initial_list:  # 用户初注册时选择的标签列表
            labels_rank[i] += initial_value
    if user_ID in user_set:
        for item_ID in user_set[user_ID].keys():
            item_label = user_set[user_ID][item_ID][1]
            purchasing_time = user_set[user_ID][item_ID][0]
            labels_rank[item_label] += 1/(abs(T-purchasing_time)*beta)   # 相似值： 最近购买的商品对应的 label 排序
    return labels_rank

def select_label_from_label_rank(labels_rank):
    '''
    按用户对标签的喜欢程度的比例大小，从标签中抽样，选择要推荐的标签
    使用条件：
    进行基于label的推荐时调用
    输入：
    labels_rank：一个字典，key是标签，value是用户对该标签的喜欢程度，数据类型：字典
    输出：
    label：标签，数据类型：int
    '''
    labels = list(labels_rank.keys())
    p = []
    for label in labels:
        p.append(labels_rank[label])

    p = p/np.sum(p)
    return np.random.choice(labels, 1, p=p)





def label_based_recommendation(item_list):
    '''
    根据选择的标签，输入标签下的所有商品，进行随机推荐
    使用条件：
    进行基于label的推荐时调用
    输入：
    item_list：某类标签下的商品ID列表
    输出：
    推荐商品ID的list，数据类型：list
    '''
    np.random.shuffle(item_list)
    return item_list

def calculate_time(t):
    '''
    时间转换函数，将datetime.datetime数值转换成以1970年1月1日开始计时，到t时刻的为止，一共经过了多少秒
    输入：
    t:当前时间,数据类型：元组
    输出：
    listt,时间转换后的列表，数据类型：列表
    '''
    listt=[]
    for i in t:
        listt.append(abs((i-datetime.datetime(1970,1,1)).total_seconds()))
    return listt


def recommendation_4(userid):
    '''
    实现基于标签推荐算法
    输入：用户ID，数据结构：int
    输出：推荐列表，数据结构：list
    '''
    cur = db.cursor()
    cur.execute('select a.userid,a.goodsid,c.parentid,a.createtime from goodscart a inner join category c inner join buygood b on a.goodsid = b.id and b.categoryid=c.id where a.status=1 ')
    results = cur.fetchall()  # 将查询出来的结果 转成列表数组
    data = np.array(results)
    user_IDs = data[:, 0]
    item_IDs = data[:, 1]
    item_labels = data[:, 2]
    purchasing_date = calculate_time(data[:, 3])
    user_ID = userid
    cur.execute('select categoryid from prefer where userid = %s ' % user_ID)
    results = cur.fetchall()
    selected_list = []
    for i in results:
        selected_list.append(i[0])   # 用户选择的标签列表
    T = calculate_time((datetime.datetime.now(),))[0]
    recommendation_list = LBR(user_IDs, item_IDs, item_labels, purchasing_date, user_ID, selected_list, T, alpha=1/24/3600,beta=1,initial_value=1)
    listt = []
    for j in range(10):
        for i in recommendation_list:
            parent_id = i
            cur.execute('select a.id from buygood a inner join category b on a.categoryid = b.id where b.parentid=%s order by rand() limit 1' % parent_id)
            results = cur.fetchall()[0][0]
            listt.append(results)
    return listt


##########################################################################################
#  基于时间上下文的itemCF的推荐
##########################################################################################

def recommendation_3(userid):
    '''
    实现购买了基于上下文的itemCF算法
    输入：
    用户ID，数据结构：int
    输出：
    推荐列表，数据结构：list
    '''
    cur = db.cursor()
    cur.execute('select a.userid,a.goodsid,c.parentid,a.createtime from goodscart a inner join category c inner join buygood b on a.goodsid = b.id and b.categoryid=c.id where a.status=1 ')
    # ((userid, goodsid, menuid, date),(userid, goodsid, menuid, date),(userid, goodsid, menuid, date))
    results = cur.fetchall()
    if results == ():
        return []
    else:
        # [[userid goodsid munuid date], [userid goodsid munuid date], [userid goodsid munuid date]]
        data = np.array(results)

        # [userid1 userid2 userid3]
        user_IDs = data[:, 0]
        # [goodsid1 goodsid2 goodsid3]
        item_IDs = data[:, 1]
        item_labels = data[:, 2]
        purchasing_date = calculate_time(data[:, 3])
        user_id = userid

        # 从购物车中取出买过的商品
        cur.execute('select goodsid from goodscart where status=1 and userid=%s ' %user_id)
        results = cur.fetchall()
        purchased_list = []
        for i in results:
            purchased_list.append(i[0])
        rec_list = TICF(user_IDs, item_IDs, item_labels, purchasing_date, purchased_list)
        return rec_list

def time_based_item_CF(purchased_list, W):
    '''
    根据物品相似度矩阵，进行推荐
    使用条件：
    生成基于时间上下文的itemCF推荐
    输入：
    purchased_list：用户购买历史，数据类型：list
    W：商品相似度字典，数据类型
    输出：
    推荐商品ID列表，数据类型：list
    '''
    # 已有了相似度矩阵，对用户A，只需要计算所有商品和A用户的所有商品的相似度之和
    rank = Counter()
    if isinstance(purchased_list, float):
        purchased_list = [purchased_list]
    for i in W.keys():
        if i not in purchased_list:
            for j in purchased_list:
                rank[i] += W[i][j]
    if rank.most_common() == []:
        return []
    else:
        return np.array(rank.most_common())[:, 0]

def TICF(user_IDs, item_IDs, item_labels, purchasing_date, purchased_list, alpha=1 / 24 / 3600):
    '''
    实现了基于时间上下文的itemCF算法
    输入：
    user_IDs:用户ID，数据类型：列表或者int
    item_IDs:商品ID，数据类型：列表或者int
    purchasing_date:购买时间，数据类型：列表或者float,int
    alpha，时间衰减速率，alpha越大，则时间差对用户影响越大，数据类型：int 或者 float
    purchased_list:用户已购买的商品ID，数据类型：int或者list
    输出：
    推荐列表，数据类型：列表
    '''
    if purchased_list:
        W = ItemSimilarity(user_IDs, item_IDs, item_labels, purchasing_date, alpha)
        TICF_list = time_based_item_CF(purchased_list, W)
    else:
        TICF_list = []
    return TICF_list

##########################################################################
#基于上次搜索的推荐
##########################################################################
def search_goods(X):
    '''
    实现用户X基于搜索的推荐函数
    输入：
    X；用户ID，数据类型：int
    输出：
    results：输出列表
    '''
    cur = db.cursor()
    cur.execute('select lastsearch from login where userid=%s' % X)
    results = cur.fetchall()
    #若无查询记录，直接返回空tuple
    if results==():
        return ()
    else:
        results = results[0][0]
        cur.execute("select id from buygood where goodsname like '%%%s%%'" % results)
        results = cur.fetchall()
        return results

def recommendation_2(X):
    '''
    实现基于用户搜索的推荐
    输入：X，用户ID，数据结构：int
    输出：推荐列表，数据结构：list
    '''
    results = search_goods(X)
    listt=[]
    for i in results:
        listt.append(list(i)[0])
    # print(listt)
    random.shuffle(listt)
    # print(listt)
    return listt


#####################################################################
#过滤重排
#####################################################################

def personnal_recommendation_list(user_id, initial_list):
    '''
    对于给定好的推荐列表，进行过滤重排，过滤出用户已经购买过的商品，对剩下的商品进行随机排列，输出前10个。
    输入:
    user_id:用户ID，数据类型，int
    initial_list:未过滤的初始列表，数据类型，list
    输出：
    过滤重排后的列表，数据类型，list
    '''
    listt=[]
    cur = db.cursor()
    cur.execute('select goodsid from goodscart where status=1 and userid=%s'%user_id)
    results = cur.fetchall()
    if results == ():
        purchased_list = []
    else:
        purchased_list = np.array(results)
    for i in (initial_list) :
        if i not in results:
            listt.append(i)
    listt = list(set(listt))
    if len(listt) >= 10:
        listt = listt[:10]
    # if len(listt) < 10:
    #     t = 10-len(listt)
    #     for i in range(t):
    #         cur.execute('select id from buygood order by rand() limit 1 ')
    #         result = cur.fetchall()[0][0]
    #         if result in purchased_list:
    #             continue
    #         if result in listt:
    #             continue
    #         listt.append(result)
    # else:
    #     np.random.shuffle(listt)
    #     listt=listt[:10]
    return listt

# 最终函数
def personal_recommendation(userID):
    '''
    实现一个函数，输入用户ID，自动计算用户个性化推荐列表，并且以json(array)格式存入（更新）到数据库中
    输入：用户ID，int
    '''
    # list4 = recommendation_4(userID)
    # list3 = recommendation_3(userID)
    list2 = recommendation_2(userID)
    # listt = personnal_recommendation_list(userID,list(list2)+list(list3)+list(list4))
    listt = personnal_recommendation_list(userID, list(list2))
    list_str = ','.join(str(int(i)) for i in listt)
    print(list_str)
    cur = db.cursor()
    print("update recommendation set list='%s', createtime=now() where userid=%s" % (list_str, userID))
    if cur.execute('select * from recommendation where userid=%s' % userID):
        cur.execute("update recommendation set list='%s', createtime=now() where userid=%s" % (list_str, userID))
    else:
        cur.execute("insert into recommendation (userid,list,createtime) value ('%s','%s', now())" % (userID, list_str))
    db.commit()

def personal_recommendation_lbr(userID):
    list4 = recommendation_4(userID)
    print(list4)
    listt = personnal_recommendation_list(userID, list(list4))
    list_str = ','.join(str(int(i)) for i in listt)
    # print(list_str)
    cur = db.cursor()
    print("update recommendation set list='%s', createtime=now() where userid=%s" % (list_str, userID))
    if cur.execute('select * from recommendation where userid=%s' % userID):
        cur.execute("update recommendation set list='%s', createtime=now() where userid=%s" % (list_str, userID))
    else:
        cur.execute("insert into recommendation (userid,list,createtime) value ('%s', '%s', now())" % (userID, list_str))
    db.commit()

"""
个性化推荐中的
搜索推荐
1. 需要一个用户（需要一个用户来登录系统）
2. 用户登录，进行搜索（记录最后一次搜索的条件）
3. 根据数据库中搜索条件，通过python来进行记录删选
4. 将搜索出来的结果保存到数据库
    4.1 先搜索出来所有商品（buygood） 已经购买的商品中搜索
    4.2 去掉我买过的商品 （不需要推荐了）
    4.3 去重（去掉重复的记录）
    4.4 计算一下商品的数量，数量如果是大于10，取10条，小于10的，那么从商品表（buygood）中取一些数据 （可能出现重复，但是概率比较低）
    4.5 最后返回数据并更新数据库
5. 展示数据（已经完成）
"""
if __name__ == '__main__':
    userId = argv[1]
    # userId = 11
    personal_recommendation(userId)
    # cur = db.cursor()
    # cur.execute('select list from recommendation where userid = 2 ')
    # print(cur.fetchall())
    # userId = 11
    # list2 = recommendation_2(userId)
    # print(list2)
    # list3 = recommendation_3(2)
