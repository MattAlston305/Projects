package com.revature.ImpUtil;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revature.Dealership.Cars;

public class CarsPostgresqlDAOImpTest {
	
	CarsPostgresqlDAOImp dao = new CarsPostgresqlDAOImp();
	Cars car;

	@Before
	public void setUp() throws Exception {
		car = new Cars("corolla");
		car.setCar_Id(9999);
		car.setCarType("Coupe");
	}

	@Test
	public void testCreateCar() {
	//	dao.createCar(car);
	}

	@Test
	public void testGetCarbyId() {
		Cars carbyId = dao.getCarbyId(3);
		
		assertEquals(3, carbyId.getCar_Id());
	}

}
