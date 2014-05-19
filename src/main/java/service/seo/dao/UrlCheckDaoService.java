package service.seo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import domaine.seo.url.Turltocheck;

public class UrlCheckDaoService {
	
	@Autowired
	private DataSource dataSource;
	
	
	public void setDataSource(DataSource source) {
		this.dataSource = source;
	}

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		
		if(jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(this.dataSource);
		}
		return jdbcTemplate;
	}
	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Turltocheck urltocheck = new Turltocheck(rs.getString("url"),rs.getInt("uid"));
		urltocheck.setAddDate(rs.getDate("addDate"));
		return urltocheck;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Turltocheck> getCurrentUrlToCheck(Integer urlListUid) {
		List<Map> rows = getJdbcTemplate().queryForList("Select url,uid from turltocheck where not deleted");
		List<Turltocheck> urlsToChek = new ArrayList<Turltocheck>();
		for (Map row : rows) {
			Turltocheck urltocheck = new Turltocheck((String)row.get("url"),(Integer)row.get("uid"));
			urltocheck.setAddDate((Date)row.get("addDate"));
			urlsToChek.add(urltocheck);
		}
		return urlsToChek;
	}

	public Integer addUrlToCheck(final Turltocheck url, Integer urlListUid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement("insert into turltocheck(url) values(?)", new String[] {"uid"});
			            ps.setString(1, url.getUrl());
			            return ps;
			        }
			    },
			    keyHolder);
		
		return Integer.valueOf((Integer) keyHolder.getKey());
	}

	public void flagUrlIsDelete(Turltocheck url, Integer urlListUid) {
		getJdbcTemplate().execute("update turltocheck set delDate = current_timestamp , deleted = true where uid = "+url.getUid());
	}
	
	public void saveUrlCheckResult(Turltocheck urlToCheck, HashMap<String, String> responseDetails) {
		getJdbcTemplate().execute("INSERT INTO turlcheckedresults(DESTINATION_URL,RESPONSECODE,URL_UID) VALUES('"+responseDetails.get("DESTINATION_URL")+"','"+responseDetails.get("RESPONSE_CODE")+"',"+urlToCheck.getUid()+")");
	}

}
