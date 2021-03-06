package com.kushnir.paperki.dao;

import com.kushnir.paperki.model.delivery.ShipmentInfo;
import com.kushnir.paperki.model.order.*;
import com.kushnir.paperki.model.payment.PaymentInfo;
import com.kushnir.paperki.model.product.CartProduct;
import com.kushnir.paperki.model.product.Item;
import com.kushnir.paperki.model.user.CustomerInfo;
import com.kushnir.paperki.model.user.EnterpriseInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service("orderDao")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Transactional
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${webapp.host}")
    private String host;

    private static final String P_ORDER_ID = "p_id_order";

    @Value("${order.getByToken}")
    private String getOrderByTokenSqlQuery;

    @Value("${order.getByUseId}")
    private String getUserOrdersSqlQuery;

    @Value("${order.getAllNew}")
    private String getAllNewSqlQuery;

    @Value("${order.getAttributes}")
    private String getOrderAttributeSqlQuery;

    @Value("${order.getItems}")
    private String getOrderItemsSQlQuery;

    @Value("${order.add}")
    private String addOrderSqlQuery;

    private static final String P_ORDER_ID_ORDER_TYPE = "p_id_order_type";
    private static final String P_ORDER_TOKEN = "p_token_order";
    private static final String P_ORDER_NUMBER = "p_order_number";
    private static final String P_ORDER_ID_USER = "p_id_user";
    private static final String P_ORDER_TOTAL = "p_total";
    private static final String P_ORDER_TOTAL_WITH_VAT = "p_total_with_vat";
    private static final String P_ORDER_VAT_TOTAL = "p_vat_total";
    private static final String P_ORDER_TOTAL_DISCOUNT = "p_total_discount";
    private static final String P_ORDER_FINAL_TOTAL = "p_final_total";
    private static final String P_ORDER_FINAL_TOTAL_WITH_VAT = "p_final_total_with_vat";
    private static final String P_ORDER_COMMENT = "p_comment";
    private static final String P_ORDER_STATUS_ID = "p_id_order_status";
    private static final String P_ORDER_PAPNUMBER = "p_pap_order_number";

    private static final String P_CUSTOMER_NAME = "p_customer_name";
    private static final String P_ENTERPRISE_NAME = "p_enterprise_name";
    private static final String P_UNP = "p_unp";
    private static final String P_EMAIL = "p_email";
    private static final String P_PHONE = "p_phone";
    private static final String P_PAYMENT_NAME = "p_payment_name";
    private static final String P_PAYMENT_ACCOUNT = "p_payment_account";
    private static final String P_PAYMENT_BANK_NAME = "p_payment_bank_name";
    private static final String P_PAYMENT_BANK_CODE = "p_payment_bank_code";
    private static final String P_SHIPMENT_NAME = "p_shipment_name";
    private static final String P_SHIPMENT_ADDRESS = "p_shipment_address";
    private static final String P_USER_NOTES = "p_user_notes";


    @Value("${order.addItem}")
    private String addOrderItemSqlQuery;

    private static final String P_ITEM_ID_ORDER = "p_id_order";
    private static final String P_ITEM_ID_PRODUCT = "p_id_product";
    private static final String P_ITEM_PRODUCT_FULL_NAME = "p_product_full_name";
    private static final String P_ITEM_VAT = "p_VAT";
    private static final String P_ITEM_BASE_PRICE = "p_base_price";
    private static final String P_ITEM_BASE_PRICE_WITH_VAT = "p_base_price_with_vat";
    private static final String P_ITEM_DISCOUNTED_PRICE = "p_discounted_price";
    private static final String P_ITEM_DISCOUNTED_PRICE_WITH_VAT = "p_discounted_price_with_vat";
    private static final String P_ITEM_QUANTITY = "p_quantity";
    private static final String P_ITEM_TOTAL = "p_total";
    private static final String P_ITEM_TOTAL_WITH_VAT = "p_total_with_vat";


    @Value("${order.addAttribute}")
    private String addAttributeSqlQuery;

    @Value("${order.updateOrderPapNumber}")
    private String updateOrderPapNumberSqlQuery;

    @Value("${order.updateOrderStatusId}")
    private String updateOrderStatusIdSqlQuery;

    @Value("${order.addOrderStatusHistory}")
    private String addOrderStatusHistorySqlQuery;


    @Override
    public Order getOrderByToken(String token) {
        LOGGER.debug("getOrderByToken({}) >>>", token);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_ORDER_TOKEN, token);
        Order order =  namedParameterJdbcTemplate.query(
                getOrderByTokenSqlQuery,
                parameterSource,
                new OrderResultSetExtractor());
        return order;
    }

    @Override
    public HashMap<String, HashMap<Integer, Order>> getOrdersByUserId(Integer userId) {
        LOGGER.debug("getOrdersByUserId({}) >>>", userId);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_ORDER_ID_USER, userId);
        HashMap<String, HashMap<Integer, Order>> userOrders =
                namedParameterJdbcTemplate.query(getUserOrdersSqlQuery, parameterSource, new OrdersResultSetExtractor());
        return userOrders;
    }

    @Override
    public ArrayList getAllNewOrders(LocalDate from, LocalDate to, Integer[] statuses) {
        LOGGER.debug("getAllNewOrders() >>>");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("p_from", from);
        parameterSource.addValue("p_to", to);
        parameterSource.addValue("p_status", Arrays.asList(statuses));
        return namedParameterJdbcTemplate.query(getAllNewSqlQuery, parameterSource, new NewOrdersResultSetExtractor());
    }

    @Override
    public List<Attribute> getOrderAttributes(int idOrder) {
        LOGGER.debug("getOrderAttributes({}) >>>", idOrder);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_ORDER_ID, idOrder);
        List<Attribute> attributes = namedParameterJdbcTemplate
                .query( getOrderAttributeSqlQuery,
                        parameterSource,
                        new AttributeRowMapper());
        return attributes;
    }

    @Override
    public List<CartProduct> getOrderItems(int idOrder) {
        LOGGER.debug("getOrderItems() >>>");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_ORDER_ID, idOrder);
        List<CartProduct> items = namedParameterJdbcTemplate
                .query(getOrderItemsSQlQuery, parameterSource, new CartProductRowMapper());
        return items;
    }

    @Override
    public Integer addOrder(Order order) {
        LOGGER.debug("addOrder() >>>");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterSource.addValue(P_ORDER_ID_ORDER_TYPE, order.getId_order_type());
        parameterSource.addValue(P_ORDER_TOKEN, order.getToken_order());
        parameterSource.addValue(P_ORDER_NUMBER, order.getOrder_number());
        parameterSource.addValue(P_ORDER_ID_USER, order.getId_user());
        parameterSource.addValue(P_ORDER_TOTAL, order.getTotal());
        parameterSource.addValue(P_ORDER_TOTAL_WITH_VAT, order.getTotal_with_vat());
        parameterSource.addValue(P_ORDER_VAT_TOTAL, order.getVat_total());
        parameterSource.addValue(P_ORDER_TOTAL_DISCOUNT, order.getTotal_discount());
        parameterSource.addValue(P_ORDER_FINAL_TOTAL, order.getFinal_total());
        parameterSource.addValue(P_ORDER_FINAL_TOTAL_WITH_VAT, order.getFinal_total_with_vat());
        parameterSource.addValue(P_ORDER_COMMENT, order.getComments());

        namedParameterJdbcTemplate.update(addOrderSqlQuery, parameterSource, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int[] addOrderAttributes(List<Attribute> attributes) {
        LOGGER.debug("addOrderAttributes() >>>");
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(attributes.toArray());
        return namedParameterJdbcTemplate.batchUpdate(addAttributeSqlQuery, batch);
    }

    @Override
    public int[] addOrderItems(Object[] items) {
        LOGGER.debug("addOrderItems()");
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(items);
        return namedParameterJdbcTemplate.batchUpdate(addOrderItemSqlQuery, batch);
    }

    @Override
    public void updateOrderStatus(String orderToken, Integer status) {
        LOGGER.debug("updateOrderStatus()");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_ORDER_TOKEN, orderToken);
        parameterSource.addValue(P_ORDER_STATUS_ID, status);
        namedParameterJdbcTemplate.update(updateOrderStatusIdSqlQuery, parameterSource);
    }

    @Override
    public void addOrderStatusHistory(String orderToken, Integer status) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_ORDER_TOKEN, orderToken);
        parameterSource.addValue(P_ORDER_STATUS_ID, status);
        namedParameterJdbcTemplate.update(addOrderStatusHistorySqlQuery, parameterSource);
    }

    @Override
    public void updateOrderPapNumber(String orderToken, String papOrderNumber) {
        LOGGER.debug("updateOrderPapNumber()");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_ORDER_TOKEN, orderToken);
        parameterSource.addValue(P_ORDER_PAPNUMBER, papOrderNumber);
        namedParameterJdbcTemplate.update(updateOrderPapNumberSqlQuery, parameterSource);
    }



    private class OrderResultSetExtractor implements ResultSetExtractor<Order> {

        @Override
        public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
            Order order = new Order();
            while(rs.next()) {
                int idOrder =               rs.getInt("id_order");
                int type =                  rs.getInt("id_order_type");
                String token =              rs.getString("token_order");
                String number =             rs.getString("order_number");
                int status =                rs.getInt("id_order_status");
                String orderStatusName =    rs.getString("status_name");
                double totalWithVat =       rs.getDouble("final_total_with_vat");

                Attribute attribute = new Attribute(
                        rs.getString("name"),
                        rs.getString("value")
                );

                if(order.getId() == 0 ) {
                    order.setId(idOrder);
                    order.setToken_order(token);
                    order.setOrder_number(number);
                    order.setId_order_status(status);
                    order.setOrderStatusName(orderStatusName);
                    order.setFinal_total_with_vat(totalWithVat);
                }

                List<Attribute> attributes = order.getAttributes();
                if (attributes == null) {
                    attributes = new ArrayList<>();
                    attributes.add(attribute);
                    order.setAttributes(attributes);
                } else {
                    attributes.add(attribute);
                }

            }
            return order;
        }
    }


    private class OrdersResultSetExtractor implements ResultSetExtractor<HashMap<String, HashMap<Integer, Order>>> {

        @Override
        public HashMap<String, HashMap<Integer, Order>> extractData(ResultSet rs)
                throws SQLException, DataAccessException {
            HashMap<String, HashMap<Integer, Order>> userOrders = new HashMap<>();
            while(rs.next()) {

                Integer idOrder =           rs.getInt("id_order");
                Integer idOrderStatus =     rs.getInt("id_order_status");
                // String statusCode =         rs.getString("status_code");
                String statusName =         rs.getString("status_name");

                // order_status.description

                Integer idOrderType =       rs.getInt("id_order_type");
                String tokenOrder =         rs.getString("token_order");
                String orderNumber =        rs.getString("order_number");
                String papOrderNumber =     rs.getString("pap_order_number");
                Integer idUser =            rs.getInt("id_user");

                double total =              rs.getDouble("total");
                double totalWithVat =       rs.getDouble("total_with_vat");
                double totalVat =           rs.getDouble("vat_total");
                double totalDiscount =      rs.getDouble("total_discount");
                Integer couponId =          rs.getInt("coupon_id");
                double paymentCost =        rs.getDouble("payment_cost");
                double shipmentCost =       rs.getDouble("shipmentcost");
                double finalTotal =         rs.getDouble("final_total");
                double finalTotalWithVat =  rs.getDouble("final_total_with_vat");

                String comment =            rs.getString("comment");

                LocalDateTime createDate = null;

                try {
                    createDate =            LocalDateTime.parse(rs.getString("create_date_formatted"));
                } catch (Exception e) {

                }

                String name =               rs.getString("name");
                String value =              rs.getString("value");
                Integer orderAttribute =    rs.getInt("order_attribute");

                Attribute attribute = new Attribute(
                        name,
                        value,
                        orderAttribute
                );

                HashMap<Integer, Order> orders = userOrders.get(statusName);
                if(orders == null || orders.size() < 1) {
                    orders = new HashMap<>();
                    Order order = new Order(
                            idOrder,
                            idOrderStatus,
                            idOrderType,
                            tokenOrder,
                            orderNumber,
                            papOrderNumber,
                            idUser,
                            total,
                            totalWithVat,
                            totalVat,
                            totalDiscount,
                            couponId,
                            paymentCost,
                            shipmentCost,
                            finalTotal,
                            finalTotalWithVat
                    );
                    order.setCreate_date(createDate);

                    List<Attribute> attributes = new ArrayList<>();
                    attributes.add(attribute);
                    order.setAttributes(attributes);
                    order.setComments(comment);
                    orders.put(idOrder, order);
                    userOrders.put(statusName, orders);
                } else {
                    Order order = orders.get(idOrder);
                    if(order == null) {
                        order = new Order(
                                idOrder,
                                idOrderStatus,
                                idOrderType,
                                tokenOrder,
                                orderNumber,
                                papOrderNumber,
                                idUser,
                                total,
                                totalWithVat,
                                totalVat,
                                totalDiscount,
                                couponId,
                                paymentCost,
                                shipmentCost,
                                finalTotal,
                                finalTotalWithVat
                        );
                        order.setCreate_date(createDate);

                        List<Attribute> attributes = new ArrayList<>();
                        attributes.add(attribute);
                        order.setAttributes(attributes);
                        order.setComments(comment);
                        orders.put(idOrder, order);
                    } else {
                        List<Attribute> attributes = order.getAttributes();
                        if (attributes != null) {
                            attributes.add(attribute);
                        }
                    }
                }
            }
            return userOrders;
        }
    }

    private class NewOrdersResultSetExtractor implements ResultSetExtractor<ArrayList> {

        @Override
        public ArrayList<OrderJSON> extractData(ResultSet rs) throws SQLException, DataAccessException {
            HashMap<Integer, OrderJSON> ordersMap = new LinkedHashMap<Integer, OrderJSON>();

            while (rs.next()) {
                Integer id =                rs.getInt("id_order");
                String orderToken =         rs.getString("token_order");
                String link =               host+"/order/"+orderToken;
                String orderNumber =        rs.getString("order_number");
                String papOrderNumber =     rs.getString("pap_order_number");
                String orderDate =          rs.getString("create_date");
                Integer orderType =         rs.getInt("id_order_type");
                String comment =            rs.getString("comment");

                Integer userId =            rs.getInt("id_user");
                String userLogin =          rs.getString("login_user");

                Integer enterpriseId =      rs.getInt("id_enterprise");

                Integer pnt =               rs.getInt("pnt");
                Integer quantity =          rs.getInt("quantity");
                double basePrice =          rs.getDouble("base_price");
                double basePriceWithVat =   rs.getDouble("item_bpwv");
                double discountAmount =     rs.getDouble("item_da");
                double finalPrice =         rs.getDouble("item_fp");
                double finalPriceWithVat =  rs.getDouble("item_fpwv");
                double total =              rs.getDouble("item_total");
                double totalWithVat =       rs.getDouble("item_total_with_vat");
                double totalDiscount =      rs.getDouble("item_total_discount");

                Item item = new Item(
                        pnt,
                        quantity,
                        basePrice,
                        basePriceWithVat,
                        discountAmount,
                        finalPrice,
                        finalPriceWithVat,
                        total,
                        totalWithVat,
                        totalDiscount
                );

                PaymentInfo payment = new PaymentInfo(
                        rs.getString(AttributeType.PAYMENT_NAME.name())
                );
                ShipmentInfo shipment = new ShipmentInfo(
                        rs.getString(AttributeType.SHIPMENT_NAME.name()),
                        rs.getString(AttributeType.SHIPMENT_ADDRESS.name())
                );
                Object customerInfo;

                String phone = rs.getString(AttributeType.CONTACT_PHONE.name());
                String email = rs.getString(AttributeType.EMAIL.name());

                switch (orderType) {
                    case 1: customerInfo = new CustomerInfo(
                            userId,
                            userLogin,
                            rs.getString(AttributeType.CONTACT_NAME.name()),
                            email,
                            phone
                    ); break;
                    case 2: customerInfo = new EnterpriseInfo(
                            enterpriseId,
                            email,
                            phone,
                            rs.getString(AttributeType.UNP.name()),
                            rs.getString(AttributeType.ENTERPRISE_NAME.name())
                    ); break;
                    default: customerInfo = null; break;
                }

                List<Item> items;

                OrderJSON order = ordersMap.get(id);
                if (order == null) {

                    items = new ArrayList<Item>();

                    items.add(item);

                    ordersMap.put(id, new OrderJSON(
                       id,
                       link,
                       orderToken,
                       orderNumber,
                       papOrderNumber,
                       orderDate,
                       orderType,
                       payment,
                       shipment,
                       customerInfo,
                       comment,
                       items
                    ));
                } else {
                    items = order.getItems();
                    if (items != null) {
                        items.add(item);
                    }
                }
            }

            return new ArrayList(ordersMap.values());
        }
    }


    private class AttributeRowMapper implements RowMapper {

        @Override
        public Attribute mapRow(ResultSet rs, int rowNum) throws SQLException {
            Attribute attribute = new Attribute(
                    rs.getInt("id_order"),
                    rs.getString("name"),
                    rs.getString("value")
            );
            return attribute;
        }
    }

    private class CartProductRowMapper implements RowMapper {

        @Override
        public CartProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
            CartProduct cartProduct = new CartProduct(
                rs.getInt("id_product"),
                rs.getInt("pnt"),
                rs.getString("product_full_name"),
                rs.getInt("VAT"),
                rs.getInt("quantity"),
                rs.getDouble("base_price"),
                rs.getDouble("base_price_with_vat"),
                rs.getDouble("discount_amount"),
                rs.getDouble("final_price"),
                rs.getDouble("final_price_with_vat"),
                rs.getDouble("total"),
                rs.getDouble("total_with_vat"),
                rs.getDouble("total_discount"),
                rs.getDouble("total_vat")
            );
            return cartProduct;
        }
    }
}
