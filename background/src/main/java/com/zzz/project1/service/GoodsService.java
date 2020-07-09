package com.zzz.project1.service;

import com.zzz.project1.model.Type;
import com.zzz.project1.model.bo.*;
import com.zzz.project1.model.vo.GoodsTypeVO;
import com.zzz.project1.model.vo.MsgNoReplyVO;
import com.zzz.project1.model.vo.MsgReplyVO;
import com.zzz.project1.model.vo.SearchGoodsVO;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    List<Type> getType();

    Boolean addType(GoodsAddTypeBO addTypeBO);

    List<GoodsTypeVO> goodsByType(String typeId);

    void addGoods(GoodsUpdataBO updataBO);

    Map<String, Object> getGoodsInfo(String id);

    boolean addSpec(GoodsAddSpecBO addSpecBO);

    void deleteSpec(GoodsDeleteSpecBO deleteSpecBO);

    void updataGoods(GoodsUpdataBO updataBO);

    void deleteGoods(String id);

    List<MsgReplyVO> repliedMsg();

    List<MsgNoReplyVO> noReplyMsg();

    void reply(ReplyBO replyBO);

    List<SearchGoodsVO> searchGoods(String keyword);
}
