package com.swtest;

import org.junit.*;
import static org.junit.Assert.*;

import com.sw.ChangeIDview;
import com.sw.ChangePWview;
import com.sw.Add_Schedule;
import com.sw.Add_Phone;
import com.sw.Delete_Schedule;
import com.sw.Delete_Phone;

public class TestCase {

	@Test
	public void testChangeID() {
		String id = "swuser";
		String pw = "user0000";
		String new_id = "seungminleeTestCase";		
		ChangeIDview chidview = new ChangeIDview(id, pw);
		String changed_id = chidview.ChangeID(new_id,id);
		
		assertFalse( changed_id == new_id );
		
	}
	
	@Test
	public void testChangePW() {
		String id = "swuser";
		String pw = "user0000";
		String new_pw = "seungminleeTestCase";				
		ChangePWview chpwview = new ChangePWview(id, pw);
		
		String changed_pw = chpwview.ChangePW(new_pw,pw);
		
		assertTrue( changed_pw == pw );
		
	}	
	
	@Test
	public void testAddSchdule() {
		String id = "swuser";
		String pw = "user0000";
		String date = "161223";
		String description = "과제제출";
		Add_Schedule testAddSchdule;
		try {
			testAddSchdule = new Add_Schedule(id, pw);
			assertTrue(testAddSchdule.checkLength(date, description));  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}	
	
	@Test
	public void testDeleteSchdule() {
		String id = "swuser";
		String pw = "user0000";
		
		String schedulekey = "-1";
		Delete_Schedule testDeleteSchedule;
		try {
			testDeleteSchedule = new Delete_Schedule(id, pw);
			assertFalse(testDeleteSchedule.checkExist(schedulekey));  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testAddPhone(){
		String id = "swuser";
		String pw = "user0000";
		String name = "ABC";
		String phonenumber = "01012345678";
		Add_Phone testAdd;
		try {
			testAdd = new Add_Phone(id, pw);
			assertTrue(testAdd.checkLength(name, phonenumber));  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeletePhone() {
		String id = "swuser";
		String pw = "user0000";
		
		String phonekey = "-1";
		Delete_Phone testDelete;
		try {
			testDelete = new Delete_Phone(id, pw);
			assertFalse(testDelete.checkExist(phonekey));  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}	


}
