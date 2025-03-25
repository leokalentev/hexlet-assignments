package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";

        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Не удалось получить ID для нового товара");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<Product> find(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";

        try (var connection = dataSource.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");

                var product = new Product(title, price);
                product.setId(resultSet.getLong("id"));

                return Optional.of(product);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> getEntities() {
        String sql = "SELECT * FROM products";

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(sql);
            var products = new ArrayList<Product>();

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");

                var product = new Product(title, price);
                product.setId(id);

                products.add(product);
            }

            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // END
}
