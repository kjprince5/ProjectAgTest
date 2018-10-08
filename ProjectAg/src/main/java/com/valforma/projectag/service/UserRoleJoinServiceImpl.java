package com.valforma.projectag.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valforma.projectag.dao.UserRoleJoinDAO;
import com.valforma.projectag.model.UserRoleJoin;
import com.valforma.projectag.vo.UserRoleJoinVo;
import com.valforma.valformacommon.dao.AbstractDAO;
import com.valforma.valformacommon.service.AbstractUserRoleJoinServiceImpl;

@Service
@Transactional
public class UserRoleJoinServiceImpl extends AbstractUserRoleJoinServiceImpl<UserRoleJoin, UserRoleJoinVo> implements UserRoleJoinService{

	@Autowired
	UserRoleJoinDAO userRoleJoinDAO;


	@Override
	public AbstractDAO<UserRoleJoin, UserRoleJoinVo> getDAO() {

		return userRoleJoinDAO;
	}


	@SuppressWarnings("null")
	@Override
	public void saveUserRole(UserRoleJoin t) {
		UserRoleJoinVo criteria =new UserRoleJoinVo();
		criteria.setUserId(t.getUserId());
		criteria.setOrgId(t.getOrgId());
		//List<UserRoleJoin> list=userRoleJoinDAO.getListByCriteria(criteria, "SELECT userRole.id, userRole.USER_ID, userRole.ROLE_ID ,role.ROLE_NAME FROM USER_ROLE_JOIN userRole left join ROLE role on(role.ID=userRole.ROLE_ID)  where 1=1  ", "order by userRole.ID DESC ", -1, 0, new UserRoleJoinMapper());
		
		// TODO Implement this default method
		
		List<UserRoleJoin> list = null ;
		for (UserRoleJoin userRoleJoin : list) {
			if(userRoleJoin.getUserId().compareTo(t.getUserId())==0)
			{
				userRoleJoinDAO.delete(userRoleJoin.getId());
			}



		}
		List<BigInteger> obj=t.getList();
		for(int i=0;i<obj.size();i++){



			UserRoleJoin term= new UserRoleJoin();
			term.setUserId(t.getUserId());
			term.setOrgId(t.getOrgId());




			term.setRoleId(obj.get(i));

			super.save(term);

		}

	}



}
