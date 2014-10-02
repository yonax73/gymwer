package com.yonaxtics.gymwer.dpa.gym.logic;

import com.yonaxtics.gymwer.dpa.gym.dao.GymDao;
import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.sec.Persitence;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/26/2014)
 *
 */
public class GymLogic {

	public static boolean create(Gym gym) {
		boolean result = false;
		if (gym != null && !gym.isEmpty()) {
			result = GymDao.create(gym);
		}
		return result;
	}

	/**
	 * @param gym
	 * @return
	 */
	public static boolean load(Gym gym) {
		boolean result = false;
		if (gym != null && gym.exists()) {
			if(gym.isLoaded()){
				result =  Persitence.find(gym);	
			}			
			if (!result) {
				result = GymDao.load(gym);
				if (result) {
					Persitence.setObject(gym.getSerial(), gym);
				}
			}
		}
		return result;
	}

	/**
	 * @param gym
	 * @return
	 */
	public static boolean update(Gym gym) {
		boolean result = false;
		if (gym != null && gym.exists()) {
			result = GymDao.update(gym);
			Persitence.setObject(gym.getSerial(), gym);
		}
		return result;
	}

	/**
	 * @param gym
	 * @return
	 */
	public static boolean exists(Gym gym) {
		boolean result = false;
		if (gym != null && !gym.isEmpty()) {
			result = GymDao.exists(gym);
		}
		return result;
	}



}
