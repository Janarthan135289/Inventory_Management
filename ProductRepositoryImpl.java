package com.example.InventoryManagement.repository;

import com.example.InventoryManagement.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private JdbcTemplate template;

    private static RowMapper<Product> rowMapper = new RowMapper<Product>() {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product=new Product();
            product.setProductId(rs.getLong("PRODUCT_ID"));
            product.setProductName(rs.getString("PRODUCT_NAME"));
            product.setProductDescription(rs.getString("PRODUCT_DESCRIPTION"));
            product.setProductPrice(rs.getString("PRODUCT_PRICE"));
            product.setProductBuyDate(rs.getDate("PRODUCT_BUYDATE").toLocalDate());
            product.setProductExpiryDate(rs.getDate("PRODUCT_EXPIRYDATE").toLocalDate());
            return product;
        }
    };
    @Autowired
    private Product product;

    @Override
    public Product save(Product product) {
        String query="insert into products (product_name,product_description,product_price,product_buydate,product_expirydate) values (?,?,?,?,?)";
        template.update(query,product.getProductName(),product.getProductDescription(),product.getProductPrice(),product.getProductBuyDate(),product.getProductExpiryDate());
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String query="select * from products where PRODUCT_ID=?";
        List<Product> products=template.query(query,rowMapper,id);
        return template.query(query,rowMapper,id).stream().findFirst();
    }

    @Override
    public List<Product> findAll() {
        String query="select * from products";
        return template.query(query,rowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String query="delete from products where PRODUCT_ID=?";
        template.update(query,id);
    }

    @Override
    public boolean existsById(Long id) {
        String query="select count(*) from products where PRODUCT_ID=?";
        Integer count=template.queryForObject(query,Integer.class,id);
        return count!=null && count>0;
    }

    @Override
    public long count() {
        String query="select count(*) from products";
        Long count=template.queryForObject(query,Long.class);
        return count!=null?count:0;
    }

    @Override
    public Product update(Product product) {
        String query = "UPDATE products SET product_name=?, product_description=?, product_price=?, product_buydate=?, product_expirydate=? WHERE product_id=?";
        template.update(query,
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getProductBuyDate(),
                product.getProductExpiryDate(),
                product.getProductId());
        return product;
    }

}
