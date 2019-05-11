CFLAGS= -Wall -fpic -coverage -lm -std=c99

randomquiz: testRandom
	gcov -f -b testme.c >> randomquizresults.out
	cat testme.c.gcov >> randomquizresults.out
	
testRandom: testme.c
	gcc -o testme -g  testme.c $(CFLAGS)
	./testme &> randomquizresults.out
