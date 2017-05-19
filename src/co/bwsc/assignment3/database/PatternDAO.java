package co.bwsc.assignment3.database;

import java.util.List;
import javax.sql.DataSource;

public interface PatternDAO {

	public void setDataSource(DataSource ds);

	public void create(String name, String group, String implementation);

	public Pattern getPattern(Integer id);

	public List<Pattern> listPatterns();

	public void delete(Integer id);

	public void update(Integer id, String name, String group, String implementation);
}