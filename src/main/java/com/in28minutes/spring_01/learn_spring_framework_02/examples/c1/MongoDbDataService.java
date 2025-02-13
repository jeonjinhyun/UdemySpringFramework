package com.in28minutes.spring_01.learn_spring_framework_02.examples.c1;

//@Component
@Repository
@Primary
public class MongoDbDataService implements DataService {

	@Override
	public int[] retrieveData() {
		return new int[] { 11, 22, 33, 44, 55 };
	}

}