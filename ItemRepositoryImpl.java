package com.example.demo.repository;

import com.example.demo.domain.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
	private final JdbcTemplate jdbcTemplate;
	
	public ItemRepositoryImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Item save(Item item) {
		String sql = "INSERT INTO item (name, description) VALUES (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			ps.setString(2, item.getDescription());
			return ps;
		}, keyHolder);
		item.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
		
		return item;
	}
	
	@Override
	public Optional<Item> findById(Long id) {
		String sql = "SELECT * FROM item WHERE id = ?";
		List<Item> result = jdbcTemplate.query(sql, itemRowMapper(), id);
		return result.stream().findAny();
	}
	
	@Override
	public Optional<Item> findByName(String name) {
		String sql = "SELECT * FROM item WHERE name = ?";
		List<Item> result = jdbcTemplate.query(sql, itemRowMapper(), name);
		return result.stream().findAny();
	}
	
	@Override
	public List<Item> findAll() {
		String sql = "SELECT * FROM item";
		return jdbcTemplate.query(sql, itemRowMapper());
	}
	
	private RowMapper<Item> itemRowMapper(){
		return (rs, rowNum) -> {
			Item item = new Item();
			item.setId((rs.getLong("id")));
			item.setName(rs.getString("name"));
			item.setDescription(rs.getString("description"));
			return item;
		};
	}
}
