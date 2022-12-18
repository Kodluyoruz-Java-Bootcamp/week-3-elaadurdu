package emlakcepte.service;
import emlakcepte.model.*;
import emlakcepte.dao.RealtyDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakcepte.dao.RealtyDao;
import emlakcepte.model.Realty;
import emlakcepte.model.RealtyType;
import emlakcepte.model.Types;
import emlakcepte.model.User;
import emlakcepte.model.UserType;




@Service

public class RealtyService {
	
	private RealtyDao realtyDao = new RealtyDao();
	
	@Autowired //injection
	private UserService userService;
	
	public void createRealty(Realty realty) {	
		
		//userService.printAllUser();
		
		if (UserType.INDIVIDUAL.equals(realty.getUser().getType()) && Types.KONUT.equals(realty.getTypes()) ) {
			// en fazla 5 ilan girebilir.
		if(realty.getUser().getRealtyList().size() <3  ) {
			//System.out.println(realty.getUser().getRealtyList().size()+"sizeee");
			
			realtyDao.saveRealty(realty);
			System.out.println("createRealty :: " + realty.getTitle() + realty.getTypes()+realty.getUser().getName());
		}
			
			
		}
		else {
			
		
		System.out.println("Bireysel kullanıclar KONUT tipinde ve en fazla 3 ilan girebilir.");

		}
			
		
	}
	
	public List<Realty> getAll(){
		return realtyDao.findAll();
	}
	
	public void printAll(List<Realty> realtList) {
		realtList.forEach(realty -> System.out.println(realty));
	}

	public void getAllByProvince(String province) {
		
		getAll().stream()
		.filter(realty -> realty.getProvince().equals(province))
		//.count();
		.forEach(realty -> System.out.println(realty));
		
	}
	public void getAllByProvinceDistrict(String province,String district) {
		
		getAll().stream()
		.filter(realty -> realty.getProvince().equals(province))
		.filter(realty->realty.getDistrict().equals(district))
		//.count();
		.forEach(realty -> System.out.println(realty));
		
	}
	
	public List<Realty> getAllByUserName(User user){	
		return getAll().stream()
		.filter(realty -> realty.getUser().getMail().equals(user.getMail()))
		.toList();		
	}

	public List<Realty> getActiveRealtyByUserName(User user) {
		
		return getAll().stream()
		.filter(realty -> realty.getUser().getName().equals(user.getName()))
		.filter(realty -> RealtyType.ACTIVE.equals(realty.getStatus()))
		.toList();

	}

}
