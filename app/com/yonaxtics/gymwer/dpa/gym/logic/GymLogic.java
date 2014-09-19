package com.yonaxtics.gymwer.dpa.gym.logic;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import play.mvc.Http.Context;
import play.mvc.Http.Session;

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
			if (!isPersistent(gym)) {
				result = GymDao.load(gym);
				makePersitent(gym);
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
			makePersitent(gym);
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

	private static boolean isPersistent(Gym gym) {
		boolean result = false;
		String key = gym.getSerial();
		Object object = Persitence.getObject(key);
		if (object != null) {
			result = true;
			gym = (Gym) object;
			Session currentSession = Context.current().session();
			if (!currentSession.containsValue(enc(key))) {
				currentSession.put(Gym.KEY, enc(key));
			}
		}
		return result;
	}

	private static void makePersitent(Gym gym) {
		String key = gym.getSerial();
		Persitence.setObject(key, gym);
		Session currentSession = Context.current().session();
		if (!currentSession.containsValue(enc(key))) {
			currentSession.put(Gym.KEY, enc(key));
		}
	}

}
