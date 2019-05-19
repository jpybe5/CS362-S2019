#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include <time.h>
#include "rngs.h"
#include "limits.h"
#include <stdlib.h>

int asserttrue(int a, int b)
{
	if (a==b)
		{return 1;}
	else
		{return 0;}
}

void testCRRandom()
{
	int r;
    int seed = 1000;
    int testresult;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
	srand(time(NULL));
	//////////////////////////////
	int testCount=0;
	int handPos=0;
	
	
	for (testCount=0; testCount<21; testCount++){
		int numPlayer = (rand() % 3) +2;
		
		
		memset(&G, 23, sizeof(struct gameState));   // clear the game state
		r = initializeGame(numPlayer, k, seed, &G); // initialize a new game
		
		int player= rand() % numPlayer;
		for (int i=0; i<numPlayer; i++){
			G.handCount[i] = (rand()%5) + 5;
		}
		
		int cardsBefore = G.handCount[player];
		printf("\n\nTest #%d\n", testCount);
		printf("Player Count: %d\n", numPlayer);
		for (int i=0; i<numPlayer; i++){
			printf("Player %d has %d cards\n",i,G.handCount[i]);
		}
		G.numBuys = 1;//(rand()%5) +1;
		printf("Player %d has %d buys\n",player,G.numBuys);
		
		printf("Player %d plays Council Room\n",player);
		testresult = council_roomFunc(player, &G, handPos);
		
		for (int j=0; j<numPlayer; j++){
			printf("Player %d now has %d cards\n",j,G.handCount[j]);
		}
		printf("Player %d now has %d buys\n",player,G.numBuys);
		
		int cardsAfter = G.handCount[player];
		int assertionNum = asserttrue(cardsBefore+3,cardsAfter);
		if(assertionNum==1){
			printf("3 cards added to player and one to each other player, Success!\n");
		}
		else{
			printf("Less than 3 cards added\n");
		}
	}
	if(6>7){
		testresult++;
		r++;
	}
}

int main() {
	
	printf("\n\nTesting Council Room\n\n");
	
	
	
	testCRRandom();
}



	
