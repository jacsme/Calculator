package com.cal.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		String s = "lorem ipsum dolor sit amet";
		String s2 = "jack lord licardo hermoso cruz";
		
		Stack stack = new Stack();
		List<String> myList = new ArrayList<String>(Arrays.asList(s.split(" ")));

		for (String list : myList){
			stack.push(list);
		}
		
		Stack stack2 = new Stack();
		List<String> myList2 = new ArrayList<String>(Arrays.asList(s2.split(" ")));

		for (String list2 : myList2){
			stack2.push(list2);
			
		}
		double result = 0;
		int value1 = 1;
		int value2 = 1;
		
		result = value1 + value2;
		System.out.println("result  " + result);
		
		String strValue = "l";
		System.out.println("Input is digit " + Character.isDigit(strValue.charAt(0)));
		
		System.out.println("Size " + stack.size());
		stack2.push(stack);
		
		System.out.println("removing from stack, using pop method - " + stack.pop());
		    
		//another pop would return HTML5
		System.out.println("removing from stack, using pop method - " + stack.pop());
		System.out.println("get the last entry - " + stack.peek());
		System.out.println("get the first entry - " + stack.firstElement());
		
		System.out.println(myList);  // prints [
		System.out.println(stack);
		System.out.println(stack2);
	}

}
