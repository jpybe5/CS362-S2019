CFLAGS= -Wall -fpic -coverage -lm -std=c99

rngs.o: rngs.h rngs.c
	gcc -c rngs.c -g  $(CFLAGS)

dominion.o: dominion.h dominion.c rngs.o
	gcc -c dominion.c -g  $(CFLAGS)

randomtestresults: randomtest1 randomtest2 randomtest3
	gcov -f -b dominion.c >> randomtestresults.out
	cat dominion.c.gcov >> randomtestresults.out
	
randomtest1: randomtestadventurer.c  dominion.o rngs.o
	gcc -o randomtestadventurer_1 -g  randomtestadventurer.c dominion.o rngs.o $(CFLAGS)
	./randomtestadventurer_1 &> randomtestresults.out	
	
randomtest2: randomtestcard1.c  dominion.o rngs.o
	gcc -o randomtestcard1_1 -g  randomtestcard1.c dominion.o rngs.o $(CFLAGS)
	./randomtestcard1_1 >> randomtestresults.out

randomtest3: randomtestcard2.c  dominion.o rngs.o
	gcc -o randomtestcard2_1 -g  randomtestcard2.c dominion.o rngs.o $(CFLAGS)
	./randomtestcard2_1 >> randomtestresults.out	

unittestresults: unittest1 unittest2 unittest3 unittest4 cardtest1
	gcov -f -b dominion.c >> unittestresults.out
	cat dominion.c.gcov >> unittestresults.out
	
unittest1: unittest1.c  dominion.o rngs.o
	gcc -o unittest1_1 -g  unittest1.c dominion.o rngs.o $(CFLAGS)
	./unittest1_1 &> unittestresults.out

unittest2: unittest2.c  dominion.o rngs.o
	gcc -o unittest2_1 -g  unittest2.c dominion.o rngs.o $(CFLAGS)
	./unittest2_1 >> unittestresults.out

player: player.c interface.o
	gcc -o player player.c -g  dominion.o rngs.o interface.o $(CFLAGS)

all: playdom player 

clean:
	rm -f *.o playdom.exe playdom player player.exe  *.gcov *.gcda *.gcno *.so *.out testDrawCard testDrawCard.exe
