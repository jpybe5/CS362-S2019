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

void testSmithyRandom()
{
	int r;
    int seed = 1000;
    int numPlayer = 3;
    int testresult;
    int k[10] = {adventurer, council_room, feast, gardens, mine
               , remodel, smithy, village, baron, great_hall};
    struct gameState G;
	srand(time(NULL));
	//////////////////////////////
	int testCount=0;
	int handPos=0;
	
	for (testCount=0; testCount<21; testCount++){
		
		memset(&G, 23, sizeof(struct gameState));   // clear the game state
		r = initializeGame(numPlayer, k, seed, &G); // initialize a new game
		
		int player= rand() % numPlayer;
		G.handCount[player] = rand() % 10;
		G.deckCount[player] = (rand() % 20)+1;
		
		printf("\n\nTest #%d\nPlayer %d has %d cards, Deck size of %d\n",testCount, player, G.handCount[player],G.deckCount[player]);
		printf("Player %d plays Smithy\n",player);
		int cardsBefore = G.handCount[player];
		testresult = smithyFunc(player, &G, handPos);
		printf("Player %d now has %d cards\n", player, G.handCount[player]);
		int cardsAfter = G.handCount[player];
		int assertionNum = asserttrue(cardsBefore+2,cardsAfter);
		if(assertionNum==1){
			printf("2 cards added, Success!\n");
		}
		else{
			printf("Less than 2 cards added\n");
		}
		
		
	}
	if(6>7){
		testresult++;
		r++;
	}

}

int main() {
	
	printf("\n\nTesting Smithy\n\n");
	
	
	
	testSmithyRandom();
}



	
