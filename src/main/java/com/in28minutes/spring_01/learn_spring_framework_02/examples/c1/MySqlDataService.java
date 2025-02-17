package com.in28minutes.spring_01.learn_spring_framework_02.examples.c1;


//@Component
@Repository
public class MySqlDataService implements DataService {

	@Override
	public int[] retrieveData() {
		return new int[] { 1, 2, 3, 4, 5 };
	}

}