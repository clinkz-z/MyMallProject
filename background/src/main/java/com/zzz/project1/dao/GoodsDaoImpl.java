package com.zzz.project1.dao;

import com.zzz.project1.model.Goods;
import com.zzz.project1.model.Spec;
import com.zzz.project1.model.Type;
import com.zzz.project1.model.vo.GoodsGetInfoVO;
import com.zzz.project1.model.vo.GoodsTypeVO;
import com.zzz.project1.model.vo.SpecVO;
import com.zzz.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Type> getType() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> goodsList = null;
        try {
            goodsList = runner.query("select * from type",
                    new BeanListHandler<>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public Boolean addType(Type type) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into type (name) values (?)",
                    type.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Boolean searchType(Type type) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Type query = null;
        try {
            query = runner.query("select * from type where name = ?",
                    new BeanHandler<>(Type.class),
                    type.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query != null;
    }

    @Override
    public List<GoodsTypeVO> getGoodsByType(String typeId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<GoodsTypeVO> query = null;
        try {
            query = runner.query("select id,img,name,price,typeid,stocknum from goods where typeid = ?",
                    new BeanListHandler<>(GoodsTypeVO.class),
                    typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 新增商品表 price stockNum  需要通过specList 运算得到
     * 1. 保存数据到商品表
     * 2. 拿到商品表刚刚插入商品得id
     * 3. 将该id以及spec数据b保存到spec规格表
     * @param goods
     */
    @Override
    public void addGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into goods values (null,?,?,?,?,?,?)",
                    goods.getName(),
                    goods.getImg(),
                    goods.getPrice(),
                    goods.getTpyeId(),
                    goods.getStockNum(),
                    goods.getDesc());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int lastInsertId() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger query = null;
        try {
            query = runner.query("select last_insert_id()",
                    new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }

    @Override
    public void addSpecs(List<Spec> specs) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (Spec spec : specs) {
            try {
                runner.update("insert into spec values (null,?,?,?,?)",
                        spec.getSpecName(),
                        spec.getStockNum(),
                        spec.getUnitPrice(),
                        spec.getGoodsId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<String, Object> getGoodsInfo(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

        Map<String, Object> map = new HashMap<>();
        GoodsGetInfoVO getInfoVO = null;
        List<SpecVO> specVOList = new ArrayList<>();
        try {
            getInfoVO = runner.query("select * from goods where id = ?",
                    new BeanHandler<>(GoodsGetInfoVO.class),
                    id);
            map.put("goods", getInfoVO);
            specVOList = runner.query("select * from spec where goodsId = ?",
                    new BeanListHandler<SpecVO>(SpecVO.class),
                    id);
            map.put("specs", specVOList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
