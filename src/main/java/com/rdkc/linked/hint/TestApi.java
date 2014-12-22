package com.rdkc.linked.hint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class TestApi {
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody String test() {
		return "hello " + serviceTest();
	}

	@Transactional
	protected String serviceTest() {
		// TODO http://simplespringtutorial.com/springDeclarativeTransactions.html
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Test> results = jdbcTemplate.query(
                "select * from test where val = ?", new Object[] { "abc" },
                new RowMapper<Test>() {
                    @Override
                    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Test(rs.getLong("id"), rs.getString("val"));
                    }
                });

        return results.get(0).getVal();
	}
	
	class Test {
		private long id;
		private String val;
		public Test(long l, String val) {
			super();
			this.id = l;
			this.val = val;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
	}
}
