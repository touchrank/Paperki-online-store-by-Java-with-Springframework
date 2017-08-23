package com.kushnir.paperki.dao;

import com.kushnir.paperki.model.Brand;
import com.kushnir.paperki.model.CartProduct;
import com.kushnir.paperki.model.Price;
import com.kushnir.paperki.model.Product;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ProductDaoImpl implements ProductDao {

    private static final Logger LOGGER = LogManager.getLogger(ProductDaoImpl.class);

    private static final String P_PNT = "p_pnt";
    private static final String P_PRODUCT_T_NAME = "p_product_t_name";
    private static final String P_CATEGORY_T_NAME = "p_category_t_name";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${product.getByPNT}")
    private String getByPNTSqlQuery;

    @Value("${product.getProductByTName}")
    private String getProductByTNameSqlQuery;

    @Value("${product.getProductsByCategoryTName}")
    private String getProductsByCategoryTNameSqlQuery;

    @Value("${product.getCartProductByPNT}")
    private String getCartProductByPNTSqlQuery;

    @Override
    public HashMap<Integer, Product> getProductListByCategoryTName(String categoryTName) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_CATEGORY_T_NAME, categoryTName);
        HashMap<Integer, Product> products =
                (HashMap<Integer, Product>) namedParameterJdbcTemplate.query(
                        getProductsByCategoryTNameSqlQuery,
                        parameterSource,
                        new ProductsResultSetExtractor());
        LOGGER.debug("getProductListByCategoryTName({}) >>>\nPRODUCTS MAP: {}", categoryTName, products);
        return products;
    }

    @Override
    public Product getProductByPNT(Integer pnt) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_PNT, pnt);
        Product product = (Product) namedParameterJdbcTemplate.query(
                getByPNTSqlQuery,
                parameterSource,
                new ProductResultSetExtractor());
        LOGGER.debug("getProductBuPNT({}) >>>\nPRODUCT: {}", pnt, product);
        return product;
    }

    @Override
    public Product getProductByTName(String TName) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_PRODUCT_T_NAME, TName);
        Product product = (Product) namedParameterJdbcTemplate.query(
                getProductByTNameSqlQuery,
                parameterSource,
                new ProductResultSetExtractor()
        );
        LOGGER.debug("getProductByTName({}) >>>\nPRODUCT: {}", TName, product);
        return product;
    }

    @Override
    public CartProduct getCartProductByPNT(Integer pnt) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource(P_PNT, pnt);
        CartProduct cartProduct = (CartProduct) namedParameterJdbcTemplate.query(
                getCartProductByPNTSqlQuery,
                parameterSource,
                new CartProductResultSetExtractor()
        );
        LOGGER.debug("getCartProductByPNT({}) >>>\nCART_PRODUCT: {}", pnt, cartProduct);
        return cartProduct;
    }


    private class ProductsResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
            HashMap<Integer, Product> products = new HashMap<Integer ,Product>();
            while (rs.next()) {
                int idProduct = rs.getInt("id_product");
                int quantityStart = rs.getInt("quantity_start");

                if(products.get(idProduct) == null) {
                    Price price = new Price(quantityStart,
                            rs.getDouble("value"),
                            rs.getInt("vat")
                    );
                    Product product = new Product(
                            idProduct,
                            rs.getInt("pap_id"),
                            rs.getInt("pnt"),
                            rs.getString("full_name"),
                            rs.getString("short_name"),
                            rs.getString("translit_name"),
                            rs.getString("link"),
                            rs.getString("country_from"),
                            rs.getString("country_made"),
                            rs.getString("measure"),
                            rs.getInt("available_day"),
                            rs.getInt("quantity_available"),
                            rs.getInt("vat"),
                            rs.getBoolean("is_published"),
                            rs.getBoolean("is_visible"),
                            new Brand(rs.getString("bname"), rs.getString("btname")),
                            new HashMap<Integer, Price>()
                    );
                    HashMap<Integer, Price> prices = product.getPrices();
                    prices.put(quantityStart, price);
                    product.setPrices(prices);
                    products.put(product.getId(), product);
                } else {
                    Product p = products.get(idProduct);
                    Price price = new Price(quantityStart,
                            rs.getDouble("value"),
                            rs.getInt("vat")
                    );
                    HashMap<Integer, Price> prices = p.getPrices();
                    prices.put(quantityStart, price);
                    p.setPrices(prices);
                    products.put(idProduct, p);
                }
            }
            return products;
        }
    }

    private class ProductResultSetExtractor implements ResultSetExtractor {

        @Override
        public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
            Product product = null;
            while (rs.next()) {
                int idProduct = rs.getInt("id_product");
                int quantityStart = rs.getInt("quantity_start");

                Price price = new Price(
                        quantityStart,
                        rs.getDouble("value"),
                        rs.getInt("vat")
                );

                if(product == null) {
                    product = new Product(
                            idProduct,
                            rs.getInt("pap_id"),
                            rs.getInt("pnt"),
                            rs.getString("full_name"),
                            rs.getString("short_name"),
                            rs.getString("translit_name"),
                            rs.getString("link"),
                            rs.getString("country_from"),
                            rs.getString("country_made"),
                            rs.getString("measure"),
                            rs.getInt("available_day"),
                            rs.getInt("quantity_available"),
                            rs.getInt("vat"),
                            rs.getBoolean("is_published"),
                            rs.getBoolean("is_visible"),
                            new Brand(rs.getString("bname"), rs.getString("btname")),
                            rs.getString("short_description"),
                            rs.getString("full_description"),
                            new HashMap<Integer, Price>()
                    );
                    HashMap<Integer, Price> prices = product.getPrices();
                    prices.put(quantityStart, price);
                    product.setPrices(prices);
                } else {
                    HashMap<Integer, Price> prices = product.getPrices();
                    prices.put(quantityStart, price);
                    product.setPrices(prices);
                }
            }
            return product;
        }
    }

    private class CartProductResultSetExtractor implements ResultSetExtractor {

        @Override
        public CartProduct extractData(ResultSet rs) throws SQLException, DataAccessException {
            CartProduct cartProduct = null;
            while (rs.next()) {
                int quantityStart = rs.getInt("quantity_start");
                Double priceValue = rs.getDouble("value");
                int vat = rs.getInt("vat");
                Double currentPrice = (quantityStart == 1) ? priceValue:null;

                Price price = new Price(
                        quantityStart,
                        priceValue,
                        vat
                );

                if(cartProduct == null) {
                    cartProduct = new CartProduct(
                            rs.getInt("pnt"),
                            rs.getString("full_name"),
                            rs.getString("short_name"),
                            vat,
                            rs.getInt("quantity_available"),
                            currentPrice
                    );
                    HashMap<Integer, Price> prices = cartProduct.getPrices();
                    prices.put(quantityStart, price);
                    cartProduct.setPrices(prices);
                } else {
                    HashMap<Integer, Price> prices = cartProduct.getPrices();
                    prices.put(quantityStart, price);
                    cartProduct.setPrices(prices);
                }
            }
            return cartProduct;
        }
    }
}
