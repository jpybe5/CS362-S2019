CFLAGS= -Wall -fpic -coverage -lm -std=c99

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

bdominion.o: dominion.h bdominion.c rngs.o
	gcc -c bdominion.c -g  $(CFLAGS)
	
alltests: randomtest1 randomtest2 
	gcov -f -b bdominion.c >> alltestresults.out
	cat bdominion.c.gcov >> alltestresults.out
	
randomtestresults: randomtest1 randomtest2 randomtest3
	gcov -f -b bdominion.c >> randomtestresults.out
	cat bdominion.c.gcov >> randomtestresults.out
	
randomtest1: randomtestadventurer.c  bdominion.o rngs.o
	gcc -o randomtestadventurer_1 -g  randomtestadventurer.c bdominion.o rngs.o $(CFLAGS)
	./randomtestadventurer_1 &> alltestresults.out	
	
randomtest2: randomtestcard1.c  bdominion.o rngs.o
	gcc -o randomtestcard1_1 -g  randomtestcard1.c bdominion.o rngs.o $(CFLAGS)
	./randomtestcard1_1 >> alltestresults.out

randomtest3: randomtestcard2.c  bdominion.o rngs.o
	gcc -o randomtestcard2_1 -g  randomtestcard2.c bdominion.o rngs.o $(CFLAGS)
	./randomtestcard2_1 >> randomtestresults.out	

unittestresults: unittest1 unittest2 unittest3 unittest4 cardtest1
	gcov -f -b bdominion.c >> unittestresults.out
	cat bdominion.c.gcov >> unittestresults.out
	
unittest1: unittest1.c  bdominion.o rngs.o
	gcc -o unittest1_1 -g  unittest1.c bdominion.o rngs.o $(CFLAGS)
	./unittest1_1 &> unittestresults.out

unittest2: unittest2.c  bdominion.o rngs.o
	gcc -o unittest2_1 -g  unittest2.c bdominion.o rngs.o $(CFLAGS)
	./unittest2_1 >> unittestresults.out

player: player.c interface.o
	gcc -o player player.c -g  bdominion.o rngs.o interface.o $(CFLAGS)

all: playdom player 

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe
