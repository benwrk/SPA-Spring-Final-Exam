package co.bwsc.assignment3.database;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class PatternJDBCTemplate implements PatternDAO {

	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(String name, String group, String implementation) {
		String SQL = "insert into Pattern (`name`, `group`, `implementation`) values (?, ?, ?)";
		jdbcTemplateObject.update(SQL, name, group, implementation);
		System.out.printf("Created Record Name = %s Group = %s Implementation = %s\n", name, group, implementation);
	}

	public Pattern getPattern(Integer id) {
		String SQL = "select * from Pattern where id = ?";
		Pattern pattern = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new PatternMapper());
		return pattern;
	}

	public List<Pattern> listPatterns() {
		String SQL = "select * from Pattern";
		List<Pattern> patterns = jdbcTemplateObject.query(SQL, new PatternMapper());
		return patterns;
	}

	public void delete(Integer id) {
		String SQL = "delete from Pattern where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record ID = " + id);
	}

	public void update(Integer id, String name, String group, String implementation) {
		String SQL = "update Pattern set `name` = ?, `group` = ?, `implementation` = ? where `id` = ?";
		jdbcTemplateObject.update(SQL, name, group, implementation, id);
		System.out.println("Updated Record ID = " + id);
	}
}