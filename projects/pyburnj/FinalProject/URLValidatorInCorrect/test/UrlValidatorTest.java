/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import junit.framework.TestCase;
import java.util.Random;
/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision$
 */

public class UrlValidatorTest extends TestCase {


ResultPair[] testUrlScheme = {new ResultPair("http://",true),
		new ResultPair("xxx://",false),
		new ResultPair("HTTP://",true),
		new ResultPair("ftp://",true),
		new ResultPair("testy://",false),
		new ResultPair("http:/",false),
		new ResultPair("http//",false),
		new ResultPair("h3t://",true),
		new ResultPair("://",false),
		new ResultPair("",false)};

ResultPair[] testUrlAuthority ={new ResultPair("www.google.com",true),
		new ResultPair("0.0.0.0",true),
		new ResultPair("http://",false),
		new ResultPair("wikipedia.de",true),
		new ResultPair("espn.espn8",false),
		new ResultPair("1.2.3.4.5",true),
		new ResultPair("www.123450.com",true),
		new ResultPair("helloWorld",false),
		new ResultPair("",false),
		new ResultPair("a.b",false)};

ResultPair[] testUrlPort ={new ResultPair(":80",true),
		new ResultPair(":0",true),
		new ResultPair(":65536",false),
		new ResultPair(":65535",true),
		new ResultPair("80",false),
		new ResultPair(":999",true),
		new ResultPair(":abc",false),
		new ResultPair("",true),
		new ResultPair(":12345678",false),
		new ResultPair(":-1",false)};

ResultPair[] testPath ={new ResultPair("/page1",true),
		new ResultPair("//",false),
		new ResultPair("/12#45",false),
		new ResultPair("////",false),
		new ResultPair("/test2/test3/test4",true),
		new ResultPair("/test5/",true),
		new ResultPair("page99",false),
		new ResultPair(":page100",false),
		new ResultPair("/(())(())",false),
		new ResultPair("/goodtest!",true)};

ResultPair[] testUrlQuery ={new ResultPair("",true),
		new ResultPair("?action=view",true),
		new ResultPair("?action=magic",true),
		new ResultPair("XYZ:action=view",false),
		new ResultPair("/myQuery",false),
		new ResultPair("?hello=world",true),
		new ResultPair("hello=world",false),
		new ResultPair("?12345",true),
		new ResultPair("!@#$%",false),
		new ResultPair("myQ",false)};

	public void testNullValidatorConstructor() {				
		UrlValidator validator = new UrlValidator();
		if(validator!=null) {
			System.out.println("Null Validator Constructor Successful");
		}
		else {
			System.out.println("Null Validator Constructor Failed");
		}
	}

	public void testSchemesValidatorConstructor() {
		
		Random rand = new Random();
		int i;
		String testScheme="";
		for(i=0; i<10; i++) {
			int tmpChar = rand.nextInt(100);//+97;
			char randChar = (char) tmpChar;
			testScheme = testScheme+randChar;
		}
		String[] testSchemes = {testScheme};
		System.out.println("Schemes Validator Test Scheme: "+testScheme);
		UrlValidator validator = new UrlValidator(testSchemes);
		if(validator!=null) {
			System.out.println("Schemes Validator Constructor Successful");
		}
		else {
			System.out.println("Schemes Validator Constructor Failed");
		}
	}
	
	public void testRandomURL() {
		
		   UrlValidator validator = new UrlValidator();	   
		   Random rand = new Random();
		   
		   for(int i=0; i<50; i++) {			   
			   int randScheme = rand.nextInt(10);
			   int randAuth  = rand.nextInt(10);
			   int randPort  = rand.nextInt(10);
			   int randPath  = rand.nextInt(10);
			   int randQuery  = rand.nextInt(10);
			   
			   //build url
			   String testURL = testUrlScheme[randScheme].item+
					   testUrlAuthority[randAuth].item+
					   testUrlPort[randPort].item+
					   testPath[randPath].item+
					   testUrlQuery[randQuery].item;
			   
			   System.out.println("Testing Full URL: "+testURL);
			   boolean randTestResult = validator.isValid(testURL);
			   boolean expectedResult = (testUrlScheme[randScheme].valid && testUrlAuthority[randAuth].valid && testUrlPort[randPort].valid && testPath[randPath].valid && testUrlQuery[randQuery].valid);		   
			   System.out.println("Expected: "+expectedResult);
			   System.out.println("Result: "+randTestResult);
			   System.out.println();			   
		   }
	}
	
	public void testRandomScheme() {
		
		   UrlValidator validator = new UrlValidator();	   
		   Random rand = new Random();
		   
		   for(int i=0; i<10; i++) {			   
			   int randScheme = rand.nextInt(10);
			   int randAuth  = 0;
			   int randPort  = 0;
			   int randPath  = 0;
			   int randQuery  = 0;
			   
			   //build url
			   String testURL = testUrlScheme[randScheme].item+
					   testUrlAuthority[randAuth].item+
					   testUrlPort[randPort].item+
					   testPath[randPath].item+
					   testUrlQuery[randQuery].item;
			   
			   System.out.println("Testing Scheme: "+testUrlScheme[randScheme].item);
			   System.out.println("Full URL Built: "+testURL);
			   boolean randTestResult = validator.isValid(testURL);
			   System.out.println("Expected: "+testUrlScheme[randScheme].valid);
			   System.out.println("Result: "+randTestResult);
			   System.out.println();			   
		   }
	}
	
	public void testRandomAuth() {
		
		   UrlValidator validator = new UrlValidator();	   
		   Random rand = new Random();
		   
		   for(int i=0; i<10; i++) {			   
			   int randScheme = 0;
			   int randAuth  = rand.nextInt(10);
			   int randPort  = 0;
			   int randPath  = 0;
			   int randQuery  = 0;
			   
			   //build url
			   String testURL = testUrlScheme[randScheme].item+
					   testUrlAuthority[randAuth].item+
					   testUrlPort[randPort].item+
					   testPath[randPath].item+
					   testUrlQuery[randQuery].item;
			   
			   System.out.println("Testing Authority: "+testUrlAuthority[randAuth].item);
			   boolean randTestResult = validator.isValid(testURL);
			   System.out.println("Full URL Built: "+testURL);
			   System.out.println("Expected: "+testUrlAuthority[randAuth].valid);
			   System.out.println("Result: "+randTestResult);
			   System.out.println();			   
		   }
	}
	
	public void testRandomPort() {
		
		   UrlValidator validator = new UrlValidator();	   
		   Random rand = new Random();
		   
		   for(int i=0; i<10; i++) {			   
			   int randScheme = 0;
			   int randAuth  = 0;
			   int randPort  = rand.nextInt(10);
			   int randPath  = 0;
			   int randQuery  = 0;
			   
			   //build url
			   String testURL = testUrlScheme[randScheme].item+
					   testUrlAuthority[randAuth].item+
					   testUrlPort[randPort].item+
					   testPath[randPath].item+
					   testUrlQuery[randQuery].item;

			   System.out.println("Testing Port: "+testUrlPort[randPort].item);
			   boolean randTestResult = validator.isValid(testURL);
			   System.out.println("Full URL Built: "+testURL);
			   System.out.println("Expected: "+testUrlPort[randPort].valid);
			   System.out.println("Result: "+randTestResult);
			   System.out.println();			   
		   }
	}
	
	public void testRandomPath() {
		
		   UrlValidator validator = new UrlValidator();	   
		   Random rand = new Random();
		   
		   for(int i=0; i<10; i++) {			   
			   int randScheme = 0;
			   int randAuth  = 0;
			   int randPort  = 0;
			   int randPath  = rand.nextInt(10);
			   int randQuery  = 0;
			   
			   //build url
			   String testURL = testUrlScheme[randScheme].item+
					   testUrlAuthority[randAuth].item+
					   testUrlPort[randPort].item+
					   testPath[randPath].item+
					   testUrlQuery[randQuery].item;

			   System.out.println("Testing Path: "+testPath[randPath].item);
			   boolean randTestResult = validator.isValid(testURL);
			   System.out.println("Full URL Built: "+testURL);
			   System.out.println("Expected: "+testPath[randPath].valid);
			   System.out.println("Result: "+randTestResult);
			   System.out.println();			   
		   }
	}
	
	public void testRandomQuery() {
		
		   UrlValidator validator = new UrlValidator();	   
		   Random rand = new Random();
		   
		   for(int i=0; i<10; i++) {			   
			   int randScheme = 0;
			   int randAuth  = 0;
			   int randPort  = 0;
			   int randPath  = 0;
			   int randQuery  = rand.nextInt(10);
			   
			   //build url
			   String testURL = testUrlScheme[randScheme].item+
					   testUrlAuthority[randAuth].item+
					   testUrlPort[randPort].item+
					   testPath[randPath].item+
					   testUrlQuery[randQuery].item;

			   System.out.println("Testing Query: "+testUrlQuery[randQuery].item);
			   boolean randTestResult = validator.isValid(testURL);
			   System.out.println("Full URL Built: "+testURL);
			   System.out.println("Expected: "+testUrlQuery[randQuery].valid);
			   System.out.println("Result: "+randTestResult);
			   System.out.println();			   
		   }
	}
}