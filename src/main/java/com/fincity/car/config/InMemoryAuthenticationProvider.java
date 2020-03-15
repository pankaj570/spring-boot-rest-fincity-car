package com.fincity.car.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.fincity.car.model.LoginUser;

@Component
public class InMemoryAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		LoginUser loginUser = getUser(userName, password);

		if (loginUser != null) {
			return new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getUserName(),
					Collections.EMPTY_LIST);
		}

		throw new BadCredentialsException("UserName or password invalid");
	}

	public LoginUser getUser(String userName, String password) {
		
		String sql = "select userName, uaserPassword from user where userName = :userName and uaserPassword = :uaserPassword";
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("userName", userName);
		mapSqlParameterSource.addValue("uaserPassword", password);

		List<LoginUser> loginUsers = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource,
				(rs, rowNum) -> new LoginUser(rs.getString("userName"), rs.getString("uaserPassword")));

		if (!loginUsers.isEmpty() && loginUsers.size() == 1) {
			return loginUsers.get(0);
		} else {
			throw new BadCredentialsException("UserName or password invalid");
		}
	}
}