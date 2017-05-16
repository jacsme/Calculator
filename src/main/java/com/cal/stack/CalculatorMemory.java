package com.cal.stack;

import java.util.Stack;

public class CalculatorMemory {
	
	private static Stack<String> rpnMainStack = null;
	private static Stack<String> rpnUndoStack = null;
	
	private static Stack<String> fxInitStack = null;
	private static Stack<String> fxMainStack = null;
	private static Stack<String> fxCrossStack = null;
	
	public static Stack<String> resetRPNMainStackInstance(){
		rpnMainStack = new Stack<String>();
		return rpnMainStack;
	}
	
	public static Stack<String> getRPNMainStackInstance(){
		if(rpnMainStack == null) {
			rpnMainStack = new Stack<String>();
		}
		return rpnMainStack;
	}
	
	public static Stack<String> resetRPNUndoStackInstance(){
		rpnUndoStack = new Stack<String>();
		return rpnUndoStack;
	}
	
	public static Stack<String> getRPNUndoStackInstance(){
		if(rpnUndoStack == null) {
			rpnUndoStack = new Stack<String>();
		}
		return rpnUndoStack;
	}
	
	public static Stack<String> resetFxInitStackInstance(){
		fxInitStack = new Stack<String>();
		return fxInitStack;
	}
	
	public static Stack<String> getFxInitStackInstance(){
		if(fxInitStack == null) {
			fxInitStack = new Stack<String>();
		}
		return fxInitStack;
	}
	
	public static Stack<String> resetFxMainStackInstance(){
		fxMainStack = new Stack<String>();
		return fxMainStack;
	}
	
	public static Stack<String> getFxMainStackInstance(){
		if(fxMainStack == null) {
			fxMainStack = new Stack<String>();
		}
		return fxMainStack;
	}
	
	public static Stack<String> resetFxCrossStackInstance(){
		fxCrossStack = new Stack<String>();
		return fxCrossStack;
	}
	
	public static Stack<String> getFxCrossStackInstance(){
		if(fxCrossStack == null) {
			fxCrossStack = new Stack<String>();
		}
		return fxCrossStack;
	}
}
 