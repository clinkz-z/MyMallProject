package com.zzz.project1.dao;

import com.alibaba.druid.util.StringUtils;
import com.zzz.project1.model.Orders;
import com.zzz.project1.model.bo.CommentsBO;
import com.zzz.project1.model.bo.OrderAddBO;
import com.zzz.project1.model.bo.PageOrderBO;
import com.zzz.project1.model.bo.SettleAccountBO;
import com.zzz.project1.model.vo.*;
import com.zzz.project1.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    /**
     * 分页查询的sql语句
     * select * from Orders where ...... limit ? offset ?
     * @param orderBO
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<PageOrderInfoVO> ordersByPage(PageOrderBO orderBO) {
        Map<String, Object> results = getDynamicSql(orderBO);

        List<Object> params = (List<Object>) results.get("params");
        params.add(orderBO.getPagesize());
        params.add( (orderBO.getCurrentPage() - 1) * orderBO.getPagesize());

        String prefix_sql = "select id, userId, goodsDetailId, goods, spec, num as goodsNum," +
                " amount, stateId, nickname, name, address, phone ";
        String base = (String) results.get("sql");
        String limit = " limit ? offset ?";

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<PageOrderInfoVO> query = null;
        try {
            query = runner.query(prefix_sql + base + limit,
                    new BeanListHandler<>(PageOrderInfoVO.class),
                    params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * select count(id) from Orders where ......
     * @param orderBO
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public int getTotalCounts(PageOrderBO orderBO) {
        Map<String, Object> results = getDynamicSql(orderBO);

        String prefix_sql = "select count(id) ";
        String base = (String) results.get("sql");

        List<Object> params = (List<Object>) results.get("params");

        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Long query = null;
        try {
            query = runner.query(prefix_sql + base,
                    new ScalarHandler<>(),
                    params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue() ;
    }

    @Override
    public OrderByIdVO order(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        OrderByIdVO order = null;
        try {
            order = runner.query("select id,amount,num,goodsDetailId,stateId as state,goods,goodsId from orders where id = ?",
                    new BeanHandler<>(OrderByIdVO.class),
                    id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<OrderSpecVO> getSpecsByGoodsId(int goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<OrderSpecVO> query = null;
        try {
            query = runner.query("select id,specName,unitPrice from spec where goodsId = ?",
                    new BeanListHandler<>(OrderSpecVO.class),
                    goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public void changeOrder(Orders order) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update orders set spec = ?, goodsDetailId = ?, num = ?, amount = ?, stateId = ? where id = ?",
                    order.getSpec(),
                    order.getGoodsDetailId(),
                    order.getNum(),
                    order.getAmount(),
                    order.getStateId(),
                    order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SpecVO getSpec(int goodsId, int goodsDetailId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        SpecVO query = null;
        try {
            query = runner.query("select * from spec where id = ? and goodsId = ?",
                    new BeanHandler<>(SpecVO.class),
                    goodsDetailId,
                    goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public void deleteOrder(String id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("delete from orders where id = ?",
                    Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GetOrderByStateVO> getOrderByState(Integer state, String token) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<GetOrderByStateVO> query = new ArrayList<>();
        try {
            if (state == -1) {
                query = runner.query("select id, goodsId, stateId as state, num as goodsNum, amount, goodsDetailId, createTime," +
                                " hasComment from orders where nickname = ?",
                        new BeanListHandler<>(GetOrderByStateVO.class),
                        token);
            } else {
                query = runner.query("select id, goodsId, stateId as state, num as goodsNum, amount, goodsDetailId, createTime," +
                                " hasComment from orders where stateId = ? and nickname = ?",
                        new BeanListHandler<>(GetOrderByStateVO.class),
                        state,
                        token);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public GoodsByStateVO getGoodsByGoodsId(int goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        GoodsByStateVO query = null;
        try {
            query = runner.query("select id, name, img from goods where id = ?",
                    new BeanHandler<>(GoodsByStateVO.class),
                    goodsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public void settleAccounts(SettleAccountBO order) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update orders set stateId = 1, num = ?, amount = ?, updateTime = ? where id = ?",
                    order.getGoodsNum(),
                    order.getAmount(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())),
                    order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pay(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update orders set stateId = 1, updateTime = ? where id = ?",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())),
                    id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirmReceive(int id) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("update orders set stateId = 3, updateTime = ? where id = ?",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())),
                    id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendComment(CommentsBO commentsBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into comment values (null,?,?,?,?,?,?,?)",
                    commentsBO.getContent(),
                    commentsBO.getOrderId(),
                    commentsBO.getGoodsId(),
                    commentsBO.getGoodsDetailId(),
                    commentsBO.getToken(),
                    commentsBO.getScore(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
            runner.update("update orders set hasComment = 1 where id = ?",
                    commentsBO.getOrderId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrder(OrderAddBO orderAddBO) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
//        runner.update("insert into orders values ()");
    }


    private Map<String, Object> getDynamicSql(PageOrderBO orderBO) {
        String base = " from Orders where 1 = 1 ";

        List<Object> list = new ArrayList<>();
        if (orderBO.getState() != -1){
            base = base + " and stateId = ? ";
            list.add(orderBO.getState());
        }
        if (!StringUtils.isEmpty(orderBO.getMoneyLimit1())) {
            base = base + " and amount <= ? ";
            list.add(Double.parseDouble(orderBO.getMoneyLimit1()));
        }
        if (!StringUtils.isEmpty(orderBO.getMoneyLimit2())) {
            base = base + " and amount >= ? ";
            list.add(Double.parseDouble(orderBO.getMoneyLimit2()));
        }
        if (!StringUtils.isEmpty(orderBO.getGoods())) {
            base = base + " and goods like ? ";
            list.add("%" + orderBO.getGoods() + "%");
        }
        if (!StringUtils.isEmpty(orderBO.getAddress())) {
            base = base + " and address like ? ";
            list.add("%" + orderBO.getAddress() + "%");
        }
        if (!StringUtils.isEmpty(orderBO.getName())) {
            base = base + " and name like ? ";
            list.add("%" + orderBO.getName() + "%");
        }
        if (!StringUtils.isEmpty(orderBO.getId())) {
            base = base + " and id = ? ";
            list.add(Integer.parseInt(orderBO.getId()));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sql", base);
        map.put("params", list);
        return map;
    }
}
