package com.kushnir.paperki.dao;

import com.kushnir.paperki.model.product.*;

import org.springframework.dao.DataAccessException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface ProductDao {

    HashMap<Integer, ProductSimple> getAll();
    HashMap<Integer, Product> getProductListByCategoryTName(String categoryTName) throws DataAccessException;
    Product getProductByPNT(Integer pnt) throws DataAccessException;
    Product getProductByTName(String TName) throws DataAccessException;
    AvailableProduct getAvailableProductByPNT(Integer pnt) throws DataAccessException;
    ArrayList<Attribute> getAttributesByPNT(Integer pnt) throws DataAccessException;
    HashMap<Integer, CSVProduct> getProductsFromCSV(StringBuilder sb) throws IOException;

    void unpublishAllProducts();
    int addProduct(CSVProduct product);
    int addProductCatalogRef(CSVProduct product);
}
