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

private final boolean printStatus = false;
private final boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   /*Tests the UrlValidator constructors by attempting to create a new UrlValidator object*/
   public boolean UnitTest1() {

	   UrlValidator urlVal = new UrlValidator();
	   return urlVal.isValid("https://www.youtube.com/");

   }


   //Tests good urls only. Changes path, authority and query to test each
   public void UnitTest2() {

	   UrlValidator urlVal = new UrlValidator();
	   assertTrue(urlVal.isValid("http://www.goodUrl.com/"));
	   assertTrue(urlVal.isValid("http://www.example.gov/a/..foo"));
       assertTrue(urlVal.isValid("http://www.example.org/a/..."));
       assertTrue(urlVal.isValid("http://www.example.org/foo.goo/"));
       assertTrue(urlVal.isValid("http://www.example.org/foo..goo/"));
       assertTrue(urlVal.isValid("http://www.example.org/..foo..goo/"));
       assertFalse(urlVal.isValid("http://www.example.org/.."));
       assertFalse(urlVal.isValid("http://www.example.org/../"));
       assertFalse(urlVal.isValid("http://www.example.org/./.."));
       assertFalse(urlVal.isValid("http://www.example.org/././.."));
       assertTrue(urlVal.isValid("http://www.example.org/..."));
       assertTrue(urlVal.isValid("http://www.example.org/.../"));
       assertTrue(urlVal.isValid("http://www.example.org/.../.."));
       assertTrue(urlVal.isValid("http://www.example.org/a/b/hello..world"));
       assertTrue(urlVal.isValid("http://www.example.org/a/hello..world"));
       assertTrue(urlVal.isValid("http://www.example.org/hello.world/"));
       assertTrue(urlVal.isValid("http://www.foo.gov/hello..world/"));
       assertTrue(urlVal.isValid("http://www.foo.gov/hello.world"));
       assertTrue(urlVal.isValid("http://www.foo.gov/hello..world"));
       assertTrue(urlVal.isValid("http://www.foo.gov/..world"));
       assertTrue(urlVal.isValid("http://www.foo.gov/.../world"));
       assertFalse(urlVal.isValid("http://www.foo.gov/../world"));
       assertFalse(urlVal.isValid("http://www.foo.gov/.."));
       assertFalse(urlVal.isValid("http://www.foo.gov/../"));
       assertFalse(urlVal.isValid("http://www.foo.gov/./.."));
       assertFalse(urlVal.isValid("http://www.foo.gov/././.."));
       assertTrue(urlVal.isValid("http://www.foo.gov/..."));
       assertTrue(urlVal.isValid("http://www.foo.gov/.../"));
       assertTrue(urlVal.isValid("http://www.foo.gov/.../.."));
       assertTrue(urlVal.isValid("http://www.apache.gov:80/path"));
       assertTrue(urlVal.isValid("http://user:pass@www.apache.org:8080/path"));
       assertTrue(urlVal.isValid("http://user:@www.apache.gov:8080/path"));
       assertTrue(urlVal.isValid("http://us%00er:-._~!$&'()*+,;=@www.apache.gov:8090/path"));
       assertFalse(urlVal.isValid("http://:pass@www.apache.gov:80/path"));
      assertFalse(urlVal.isValid("http://:@www.apache.gov:80/path"));
       assertFalse(urlVal.isValid("http://user:pa:ss@www.apache.gov/path"));
       assertFalse(urlVal.isValid("http://user:pa@ss@www.apache.gov/path"));
       System.out.println("test");
       assertTrue(urlVal.isValid("http://www.foo.gov:8080/path"));
       assertTrue(urlVal.isValid("http://www.foo.org:8808/path"));
       assertTrue(urlVal.isValid("http://www.foo.org:/path"));
       assertTrue(urlVal.isValid("http://foo.gov/abcdefghijklmnopqrstuvwxyz12345678910"));
   }


   /*Tests urls with invalid authority and valid other parts*/
   public void UnitTest3() {
	   UrlValidator urlVal = new UrlValidator();
	   assertTrue(!urlVal.isValid("http:/.www.foo.bar./"));
	   assertTrue(!urlVal.isValid("https://1234"));
	   assertTrue(!urlVal.isValid("http://128.193.7-6.187/research.teachengineering.org/htdocs/BrianGraph/test/graph.php"));
	   assertTrue(!urlVal.isValid("http://128.1b3.7.187/foo/goo.php"));
	   assertTrue(!urlVal.isValid("http://128.1b3.7.187/foo/goo.php"));
	   assertTrue(!urlVal.isValid("httq://128.113.7.187/foo/goo.php"));
	   assertTrue(!urlVal.isValid("http;//128.153.7.187/foo/goo.php"));
	   assertTrue(!urlVal.isValid("http;//128.153.7.187/foo/goo.php"));
	   assertTrue(!urlVal.isValid("http://123789282/foo/foo.php"));
	   assertTrue(!urlVal.isValid("http://128.1&3.7.187/foo/foo.php"));
	   assertTrue(!urlVal.isValid("http://999999999999999999/foo/goo.php"));
	   assertTrue(!urlVal.isValid("user:pa:ss@www.apache.org/path"));
   }


   /*Tests urls with invalid path and valid other parts*/
   public void UnitTest4() {
	   UrlValidator urlVal = new UrlValidator();
	   assertTrue(!urlVal.isValid("http://www.test.org/foo\t.php"));
	   assertTrue(!urlVal.isValid("http://www.test.org///t.php"));
	   assertTrue(!urlVal.isValid("http://www.test.org/foo/t_^.php"));
	   assertTrue(!urlVal.isValid("http://www.test.org/foo/t{}.php"));
	   assertTrue(!urlVal.isValid("http://www.test.org/foo/t`.php"));
	   assertTrue(!urlVal.isValid("http://www.test.org/foo/t`.php"));
	   assertTrue(!urlVal.isValid("http://128.113.7.187/foo[]foo/goo.php"));
   }


   /*Test urls with invalid query but valid other parts*/
   public void UnitTest5() {
	   UrlValidator urlVal = new UrlValidator();
	   assertTrue(!urlVal.isValid("http://foo.bar?q=Spaces should be encoded"));
	   assertTrue(!urlVal.isValid("http://foo.bar q=goo"));
	   assertTrue(!urlVal.isValid("http://foo.bar?q=goo&test= \"foo\""));
   }


   /*Tests URLs with invalid  host and valid other parts */
   public void UnitTest6() {
	   UrlValidator urlVal = new UrlValidator();
	   assertTrue(!urlVal.isValid("http://#"));
	   assertTrue(!urlVal.isValid("http://../"));
	   assertTrue(!urlVal.isValid("http:///foo"));
	   assertTrue(!urlVal.isValid("http://.www.foo.bar./"));
	   assertTrue(!urlVal.isValid("http://-error-.invalid/"));
	   assertTrue(!urlVal.isValid("://www.foo.org/foo\\goo.php"));
	   assertTrue(!urlVal.isValid("www.test.org/foo"));
	   assertTrue(!urlVal.isValid("http://www.test.org/ foo"));
   }


   /*Creates UrlValidator object with options. Then runs the tests checking for slashes and fragments*/
   public void UnitTest7() {
		 long options =
		                  UrlValidator.ALLOW_2_SLASHES+
		                 UrlValidator.ALLOW_ALL_SCHEMES
		                + UrlValidator.NO_FRAGMENTS;
		 UrlValidator urlVal = new UrlValidator(options);

		//Check with and without fragments
		assertTrue(!urlVal.isValid("http://www.foo#goo"));
		assertTrue(urlVal.isValid("http://www.foo"));

		//check that two slashes are allowed
		assertTrue(urlVal.isValid("http://www.foo/goo//foogoo"));

   }


   /*Creates UrlValidator object with all options set. Calls constructor that takes one argument */
   public void UnitTest8() {
	   long options =    UrlValidator.ALLOW_2_SLASHES
               + UrlValidator.ALLOW_ALL_SCHEMES
               + UrlValidator.NO_FRAGMENTS
	   + UrlValidator.ALLOW_LOCAL_URLS;
	   UrlValidator urlVal = new UrlValidator(options);
	   assertTrue(!urlVal.isValid("http://#"));
	   assertTrue(!urlVal.isValid("http://../"));
	   assertTrue(!urlVal.isValid("http:///a"));
	   assertTrue(!urlVal.isValid("http://-error-.invalid/"));
   }


   /*Sets all options and creats UrlValidator object with null, null, options parameters*/
   public void UnitTest9() {
	   long options =    UrlValidator.ALLOW_2_SLASHES
               + UrlValidator.ALLOW_ALL_SCHEMES
               + UrlValidator.NO_FRAGMENTS
	   + UrlValidator.ALLOW_LOCAL_URLS;
	   UrlValidator urlVal = new UrlValidator(null, null, options);
	      assertTrue(urlVal.isValid("http://www.google.com"));
	      assertTrue(urlVal.isValid("http://www.google.com/"));;
   }


   /*Create the urlValidator object using the constuctor that takes schemes and options.
    * Then runs a few simple test cases for varous schemes with ALLOW_ALL_SCHEMES set*/
   public void UnitTest10() {
	   String[] schemes = {"http","https, foo"};
	   long options =    UrlValidator.ALLOW_2_SLASHES + UrlValidator.ALLOW_ALL_SCHEMES;
       UrlValidator urlValidator = new UrlValidator(schemes, options);
       assertTrue(urlValidator.isValid("http://www.google.com/"));
       assertTrue(urlValidator.isValid("https://www.google.com/"));
       assertTrue(urlValidator.isValid("foo://www.google.com/"));
       assertTrue(urlValidator.isValid("goo://www.google.com/"));
       assertTrue(urlValidator.isValid("foobar://www.google.com/"));
   }

   /*Test that only the schemes specified give valid urls when ALLOW_ALL_SCHEMES is not set*/
   public void UnitTest12() {
	   String[] schemes = {"http","https, foo"};
	   long options = 0;
	   UrlValidator urlValidator = new UrlValidator(schemes, options);

	   //Test some urls that are in the schemes array
	   assertTrue(urlValidator.isValid("http://www.google.com/"));
       assertTrue(urlValidator.isValid("https://www.google.com/"));
       assertTrue(urlValidator.isValid("foo://www.google.com/"));

       //Test some urls with schemes not in schemes array
       assertTrue(!urlValidator.isValid("goo://www.google.com/"));
       assertTrue(!urlValidator.isValid("foobar://www.google.com/"));
   }

   /*Creates UrlValidator object using the cunstructor that takes schemes and options.
    * Schemes is null and options are 0.*/
   public void UnitTest11() {
	   String[] schemes = null;
	   long options =   0;
       UrlValidator urlValidator = new UrlValidator(schemes, options);
       assertTrue(urlValidator.isValid("http://www.google.com/"));
       assertTrue(urlValidator.isValid("https://www.google.com/"));
       assertFalse(urlValidator.isValid("http://example.com/serach?address=Main Avenue"));
       assertTrue(urlValidator.isValid("http://example.com/serach?address=Main%20Avenue"));
       assertTrue(urlValidator.isValid("http://example.com/serach?address=Main+Avenue"));
       assertTrue(!urlValidator.isValid("http://1.2.3.4.5/serach?address=Main+Avenue"));
       assertTrue(!urlValidator.isValid("http://1.2.3.4.5:-1/serach?address=Main+Avenue"));
   }

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