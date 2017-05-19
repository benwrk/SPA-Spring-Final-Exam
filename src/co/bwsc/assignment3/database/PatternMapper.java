package co.bwsc.assignment3.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PatternMapper implements RowMapper<Pattern> {
	public Pattern mapRow(ResultSet rs, int rowNum) throws SQLException {
		Pattern pattern = new Pattern();
		pattern.setId(rs.getInt("id"));
		pattern.setName(rs.getString("name"));
		pattern.setGroup(rs.getString("group"));
		pattern.setImplementation(rs.getString("implementation"));
		return pattern;
	}
}
